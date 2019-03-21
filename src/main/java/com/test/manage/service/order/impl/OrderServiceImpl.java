package com.test.manage.service.order.impl;

import com.test.common.CommonUtil;
import com.test.framework.exception.AnyException;
import com.test.framework.model.AuthUser;
import com.test.framework.security.Auth;
import com.test.manage.dao.custom.OrderCuMapper;
import com.test.manage.dao.generator.OrderMapper;
import com.test.manage.dao.generator.OrderTraceMapper;
import com.test.manage.dao.generator.UserMapper;
import com.test.manage.model.dto.OrderDto;
import com.test.manage.model.form.OrderForm;
import com.test.manage.model.generator.Order;
import com.test.manage.model.generator.OrderTrace;
import com.test.manage.model.generator.User;
import com.test.manage.model.request.QueryParams;
import com.test.manage.service.order.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Description：
 * @Author:hudi
 * @Date: Created in 13:47 2019/3/12
 */
@Transactional
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderTraceMapper orderTraceMapper;
    @Autowired
    private OrderCuMapper orderCuMapper;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private Auth auth;

    @Override
    public void assignOrder(OrderForm orderForm) {
        Order order = new Order();
        AuthUser currentUser = auth.getCurrentUser();
        BeanUtils.copyProperties(orderForm,order);
        order.setOrderId(CommonUtil.uuid());
        order.setOrderNo("OD"+new Date().getTime());
        order.setOrderCreateUserId(currentUser.getUserId());
        order.setOrderCompanyId(currentUser.getUserCompanyId());
        String [] startingPlaceName= orderForm.getStartingPlaceName();
        if( startingPlaceName!= null && startingPlaceName.length == 3){
            order.setOrderPickupProvince(startingPlaceName[0]);
            order.setOrderPickupCity(startingPlaceName[1]);
            order.setOrderPickupDistrict(startingPlaceName[2]);
        } else {
            throw new AnyException("起始地省市区有误");
        }
        String [] endingPlaceName = orderForm.getEndingPlaceName();
        if(endingPlaceName != null && endingPlaceName.length == 3){
            order.setOrderReceiveProvince(endingPlaceName[0]);
            order.setOrderReceiveCity(endingPlaceName[1]);
            order.setOrderReceiveDistrict(endingPlaceName[2]);
        } else {
            throw new AnyException("目的地省市区有误");
        }
        order.setOrderStatus(1);
        orderMapper.insertSelective(order);
    }


    @Override
    public List<OrderDto> getOrderList(QueryParams queryParams) {
        return orderCuMapper.getOrderList(queryParams);
    }

    @Override
    public List<OrderDto> getIssueOrderList(QueryParams queryParams) {
        queryParams.setIssueCompanyId(auth.getCurrentUser().getUserCompanyId());
        return orderCuMapper.getOrderList(queryParams);
    }


    @Override
    public List<OrderDto> getReceiveOrderList(QueryParams queryParams) {
        queryParams.setRecevieCompanyId(auth.getCurrentUser().getUserCompanyId());
        return orderCuMapper.getReceiveOrderList(queryParams);
    }

    @Override
    public void deleteIssueOrder(String orderId) {
        if(2 != auth.getCurrentUser().getUserType() || 0 != auth.getCurrentUser().getUserType()){
            throw new AnyException("当前用户类型不可删除订单");
        }
        Order order = orderMapper.selectByPrimaryKey(orderId);
        if(order != null){
            if(0 != auth.getCurrentUser().getUserType()){
                if( !auth.getCurrentUser().getUserCompanyId().equals(order.getOrderCompanyId()) ){
                    throw new AnyException("非当前用户单据不可删除");
                }
            }
            if( 1 != order.getOrderStatus()){
                throw new AnyException("当前单据状态不可删除，请刷新后查看");
            }
            orderMapper.deleteByPrimaryKey(orderId);
        }else{
            throw new AnyException("订单已被删除，请刷新后尝试");
        }
    }

    @Override
    public OrderDto getOrderInfo(String orderId) {

        OrderDto orderDto= orderCuMapper.getOrderInfo(orderId);
        if(orderDto == null){
            throw new AnyException("未找到单据");
        }
        String currentComId = auth.getCurrentUser().getUserCompanyId();
        if(orderDto.getOrderStatus() > 1 && orderDto.getOrderReceiveCompanyId() != null ){
            if( !orderDto.getOrderCompanyId().equals(currentComId) && !orderDto.getOrderReceiveCompanyId().equals(currentComId) ){
                throw new AnyException("单据与用户不匹配，查询失败");
            }
        }

        return orderDto;
    }

    @Override
    public void modifyOrderStatus(OrderDto orderDto ,Integer status) {
        Order order = orderMapper.selectByPrimaryKey(orderDto.getOrderId());
        if(order == null){
            throw new AnyException("订单已被删除");
        }
        if( !order.getOrderStatus().equals(orderDto.getOrderStatus()) ){
            throw new AnyException("订单状态已发生改变，请刷新后重试");
        }

        AuthUser currentUser = auth.getCurrentUser();
        User user = userMapper.selectByPrimaryKey(currentUser.getUserId());
        if(user.getUserType() != 3){
            throw new AnyException("当前用户类型不可进行此操作");
        }

        if(status == 2 || status == 6){ // 接单
            if(order.getOrderStatus() != 1){
                throw new AnyException("操作失败，请稍后尝试");
            }
            order.setOrderReceiveCompanyId(user.getUserCompanyId());
        } else{
            if( (order.getOrderStatus()+1) != status){
                throw new AnyException("状态操作失败，请稍后尝试");
            }
            if (status == 3){ // 已提货
                order.setOrderFinalPickupTime(new Date());
            }else if (status == 4){
                order.setOrderLogisticsNo(orderDto.getOrderLogisticsNo());
            }
        }
        order.setOrderStatus(status);
        order.setOrderReceiveUserId(user.getUserId());
        insertOrderTrace(order , status ,user);
        orderMapper.updateByPrimaryKeySelective(order);

    }

    void insertOrderTrace(Order order ,Integer status ,User user){
        String conent = "";
        String contentDetail = "";
        if(status == 2 ){ // 接单
            if(order.getOrderAssign()){
                conent = "接单";
                contentDetail= "接单成功";
            }else{
                conent = "抢单";
                contentDetail= "抢单成功";
            }
        } else if (status == 3){ // 已提货
            conent = "提货";
            contentDetail= "提货成功";
        } else if (status == 4){ // 已发货
            conent = "发货";
            contentDetail= "发货成功";
        } else if (status == 5){ //已签收
            conent = "签收";
            contentDetail= "签收成功";
        } else if(status == 6){
            if(order.getOrderStatus() != 1){
                throw new AnyException("操作失败，请稍后尝试");
            }
            conent = "拒单";
            contentDetail= user.getUserName()+ "拒单成功";
        } else {
            throw new AnyException("状态操作失败，请稍后尝试");
        }
        OrderTrace orderTrace =  new OrderTrace();
        orderTrace.setCreateTime(new Date());
        orderTrace.setOrderTraceContent(conent);
        orderTrace.setOrderTraceCreateUserId(user.getUserId());
        orderTrace.setOrderTraceDetail(user.getUserCnName()+contentDetail);
        orderTrace.setOrderTraceId(CommonUtil.uuid());
        orderTrace.setOrderTraceOrderId(order.getOrderId());
        orderTraceMapper.insertSelective(orderTrace);

    }
}
