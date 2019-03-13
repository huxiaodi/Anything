package com.test.manage.service.order;

import com.test.manage.model.dto.OrderDto;
import com.test.manage.model.form.OrderForm;
import com.test.manage.model.generator.Order;
import com.test.manage.model.request.QueryParams;

import java.util.List;

/**
 * @Description：
 * @Author:hudi
 * @Date: Created in 13:47 2019/3/12
 */
public interface OrderService {
    void assignOrder(OrderForm orderForm);

    List<OrderDto> getOrderList(QueryParams queryParams);
}
