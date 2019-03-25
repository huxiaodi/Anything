package com.test.manage.dao.custom;

import com.test.manage.model.dto.OrderDto;
import com.test.manage.model.request.QueryParams;

import java.util.List;

public interface OrderCuMapper {
    List<OrderDto> getOrderList(QueryParams queryParams);
    List<OrderDto> getReceiveOrderList(QueryParams queryParams);
    OrderDto getOrderInfo(String orderId);
}
