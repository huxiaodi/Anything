package com.test.manage.service.order;

import com.test.manage.model.dto.OrderDto;
import com.test.manage.model.form.OrderForm;
import com.test.manage.model.generator.Order;
import com.test.manage.model.request.QueryParams;

import java.util.List;

public interface OrderService {
    void assignOrder(OrderForm orderForm);

    List<OrderDto> getOrderList(QueryParams queryParams);

    List<OrderDto> getIssueOrderList(QueryParams queryParams);

    List<OrderDto> getReceiveOrderList(QueryParams queryParams);

    void deleteIssueOrder(String orderId);

    OrderDto getOrderInfo(String orderId);

   void modifyOrderStatus(OrderDto orderDto ,Integer status);

}
