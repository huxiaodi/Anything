package com.test.manage.service.order.impl;

import com.test.common.CommonUtil;
import com.test.framework.exception.AnyException;
import com.test.framework.model.AuthUser;
import com.test.framework.security.Auth;
import com.test.manage.dao.custom.OrderCuMapper;
import com.test.manage.dao.generator.OrderMapper;
import com.test.manage.model.dto.OrderDto;
import com.test.manage.model.form.OrderForm;
import com.test.manage.model.generator.Order;
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
    private OrderMapper orderMapper;
    @Autowired
    private OrderCuMapper orderCuMapper;
    @Autowired
    private Auth auth;

    @Override
    public void assignOrder(OrderForm orderForm) {
        Order order = new Order();
        AuthUser currentUser = auth.getCurrentUser();
        BeanUtils.copyProperties(orderForm,order);
        order.setOrderId(CommonUtil.uuid());
        order.setOrderNo("OD"+new Date().getTime());
        order.setOrderCreateId(currentUser.getUserId());
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
        queryParams.setUserId(auth.getCurrentUser().getUserId());
        return orderCuMapper.getOrderList(queryParams);
    }
}
