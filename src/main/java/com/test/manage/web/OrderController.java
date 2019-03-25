package com.test.manage.web;

import com.github.pagehelper.PageInfo;
import com.test.common.CommonUtil;
import com.test.framework.controller.BaseController;
import com.test.framework.exception.AnyException;
import com.test.framework.model.ApiResult;
import com.test.framework.model.AuthUser;
import com.test.framework.security.Auth;
import com.test.manage.dao.generator.CompanyMapper;
import com.test.manage.dao.generator.OrderMapper;
import com.test.manage.dao.generator.OrderTraceMapper;
import com.test.manage.dao.generator.UserMapper;
import com.test.manage.model.dto.OrderDto;
import com.test.manage.model.form.OrderForm;
import com.test.manage.model.generator.CompanyExample;
import com.test.manage.model.generator.Order;
import com.test.manage.model.generator.OrderTrace;
import com.test.manage.model.generator.UserExample;
import com.test.manage.model.request.QueryParams;
import com.test.manage.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/order")
@RestController
public class OrderController extends BaseController {

    @Autowired
    private OrderTraceMapper orderTraceMapper;
    @Autowired
    private CompanyMapper companyMapper;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private Auth auth;


    /**
     * 发布单据
     * @param orderForm
     * @return
     */
    @PostMapping("/assignOrder")
    public ApiResult assignOrder(@RequestBody OrderForm orderForm){
        orderService.assignOrder(orderForm);
        return success();
    }

    /**
     * 获取单据列表
     * @param queryParams
     * @return
     */
    @PostMapping("/getOrderList")
    public ApiResult getOrderList(@RequestBody QueryParams queryParams){
        return success(new PageInfo<>(orderService.getOrderList(queryParams)));
    }

    /**
     * 获取接单列表
     * @param queryParams
     * @return
     */
    @PostMapping("/getReceiveOrderList")
    public ApiResult getReceiveOrderList(@RequestBody QueryParams queryParams){
        return success(new PageInfo<>(orderService.getReceiveOrderList(queryParams)));
    }

    /**
     * 获取发布方单据列表
     * @param queryParams
     * @return
     */
    @PostMapping("/getIssueOrderList")
    public ApiResult getIssueOrderList(@RequestBody QueryParams queryParams){
        return success(new PageInfo<>(orderService.getIssueOrderList(queryParams)));
    }

    @PostMapping("/deleteIssueOrder")
    public ApiResult deleteIssueOrder(@RequestParam String orderId){
        orderService.deleteIssueOrder(orderId);
        return success();
    }


    @GetMapping("/getOrderInfo")
    public ApiResult getOrderInfo(@RequestParam String orderId){
        return success(orderService.getOrderInfo(orderId));
    }


    @PostMapping("/modifyOrder")
    public ApiResult modifyOrder(@RequestBody OrderDto orderDto,@RequestParam Integer status){
        orderService.modifyOrderStatus(orderDto, status);
        return success();
    }

    @GetMapping("/getReceiveUserList")
    public ApiResult getReceiveUserList(){
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserTypeEqualTo(3).andUserIsDeleteEqualTo(false);
        List<String> companyIds = userMapper.selectByExample(userExample).stream().map(item -> item.getUserCompanyId()).collect(Collectors.toList());
        CompanyExample companyExample = new CompanyExample();
        companyExample.createCriteria().andCompanyIdIn(companyIds);
        return success(companyMapper.selectByExample(companyExample));
    }




}
