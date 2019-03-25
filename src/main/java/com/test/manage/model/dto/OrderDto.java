package com.test.manage.model.dto;

import com.test.manage.model.generator.Order;
import lombok.Data;

@Data
public class OrderDto extends Order {
    private String userName;
    private String issueCompanyId;
    private String issueCompanyName;
    private String receiveCompanyId;
    private String receiveCompanyName;
}
