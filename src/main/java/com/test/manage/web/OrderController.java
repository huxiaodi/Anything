package com.test.manage.web;

import com.github.pagehelper.PageInfo;
import com.test.framework.controller.BaseController;
import com.test.framework.model.ApiResult;
import com.test.manage.model.form.OrderForm;
import com.test.manage.model.request.QueryParams;
import com.test.manage.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description：
 * @Author:hudi
 * @Date: Created in 13:46 2019/3/12
 */
@RequestMapping("/order")
@RestController
public class OrderController extends BaseController {

    @Autowired
    private OrderService orderService;


    @PostMapping("/assignOrder")
    public ApiResult assignOrder(@RequestBody OrderForm orderForm){
        orderService.assignOrder(orderForm);
        return success();
    }

    @PostMapping("/getOrderList")
    public ApiResult getOrderList(@RequestBody QueryParams queryParams){
        return success(new PageInfo<>(orderService.getOrderList(queryParams)));
    }



}
