package com.test.manage.model.request;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class QueryParams extends Page {
    private String id;
    private String no;
    private Date startTime;
    private Date endTime;
    private BigDecimal startAmt; //起始金额
    private BigDecimal endAmt; // 结束金额
    private Integer tradeType; //交易类型
    private String type;

    private String userId;

    private String status; //状态
    private String keyword;
    private Boolean orderAssign;
    private Integer orderStatus;

}
