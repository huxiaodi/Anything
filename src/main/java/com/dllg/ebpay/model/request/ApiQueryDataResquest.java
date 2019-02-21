package com.dllg.ebpay.model.request;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @Description：
 * @Author:hudi
 * @Date: Created in 10:27 2018/4/3
 */
public class ApiQueryDataResquest {

    @ApiModelProperty(value = "查询的交易id",required = true)
    @NotBlank(message ="查询的交易id必填")
    private String tradeId;

    @ApiModelProperty(value = "关联id，即业务平台在E宝保定子账户返回的关联id",required = true)
    @NotBlank(message = "关联Id必填")
    private String relationId;


    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }

    public String getRelationId() {
        return relationId;
    }

    public void setRelationId(String relationId) {
        this.relationId = relationId;
    }
}
