package com.test.manage.model.dto;

import com.test.manage.model.generator.Order;
import lombok.Data;

/**
 * @Description：
 * @Author:hudi
 * @Date: Created in 17:29 2019/3/12
 */
@Data
public class OrderDto extends Order {
    private String userName;
    private String issueCompanyName;
    private String receiveCompanyName;
}
