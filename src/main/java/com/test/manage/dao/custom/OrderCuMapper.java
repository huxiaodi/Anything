package com.test.manage.dao.custom;

import com.test.manage.model.dto.OrderDto;
import com.test.manage.model.request.QueryParams;

import java.util.List;

/**
 * @Description：
 * @Author:hudi
 * @Date: Created in 17:30 2019/3/12
 */
public interface OrderCuMapper {
    List<OrderDto> getOrderList(QueryParams queryParams);
}
