package com.dllg.ebpay.model.request;

import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @Description：
 * @Author:hudi
 * @Date: Created in 15:10 2018/4/2
 */
public class ApiPaymentDisposeRequest {

    @ApiModelProperty(value = "关联id，即业务平台在E宝保定子账户返回的关联id",required = true)
    @NotBlank(message = "关联Id必填")
    private String relationId;

    @ApiModelProperty(value = "交易主账户号码",required = true)
    @NotBlank(message = "主账户号必填")
    private String accountSubNo;

    @ApiModelProperty(value = "转入账户号码",required = true)
    @NotBlank(message = "交易账户号必填")
    private String inAccountSubNo;

    @ApiModelProperty(value = "交易的金额",required = true)
    @NotNull(message = "交易金额不能为空")
    private BigDecimal tranAmt;

    @ApiModelProperty(value = "业务单号",required = true)
    @NotBlank(message = "业务单号必填")
    private String businessNo;

    @ApiModelProperty(value = "业务类型",required = true)
    @NotBlank(message = "业务类型必填")
    private String businessType;

    @ApiModelProperty(value = "交易来源",required = true)
    @NotBlank(message = "交易来源必填")
    private String businessFrom;

    @ApiModelProperty(value = "业务内容")
    private String businessContent;

    @ApiModelProperty(value = "平台回调地址，接收E宝返回的交易信息",required = true)
    @NotBlank(message = "回调地址必填")
    private String backUrl;

    @ApiModelProperty(value = "创建人用户id",required = true)
    @NotBlank(message = "创建人用户信息必填")
    private String createrUserId;

    @ApiModelProperty(value = "保留域")
    private String reservedMsg;

    @ApiModelProperty(value = "备注信息")
    private String remark;

    @NotBlank(message = "清分类型必填")
    private String businessTradeClearingType; //清分类型
    @NotBlank(message = "品名必填")
    private String businessTradeGoodsName; //品名;


    public String getBusinessTradeClearingType() {
        return businessTradeClearingType;
    }

    public void setBusinessTradeClearingType(String businessTradeClearingType) {
        this.businessTradeClearingType = businessTradeClearingType;
    }

    public String getBusinessTradeGoodsName() {
        return businessTradeGoodsName;
    }

    public void setBusinessTradeGoodsName(String businessTradeGoodsName) {
        this.businessTradeGoodsName = businessTradeGoodsName;
    }

    public String getRelationId() {
        return relationId;
    }

    public void setRelationId(String relationId) {
        this.relationId = relationId;
    }

    public String getAccountSubNo() {
        return accountSubNo;
    }

    public void setAccountSubNo(String accountSubNo) {
        this.accountSubNo = accountSubNo;
    }

    public String getInAccountSubNo() {
        return inAccountSubNo;
    }

    public void setInAccountSubNo(String inAccountSubNo) {
        this.inAccountSubNo = inAccountSubNo;
    }

    public BigDecimal getTranAmt() {
        return tranAmt;
    }

    public void setTranAmt(BigDecimal tranAmt) {
        this.tranAmt = tranAmt;
    }

    public String getBusinessNo() {
        return businessNo;
    }

    public void setBusinessNo(String businessNo) {
        this.businessNo = businessNo;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getBusinessFrom() {
        return businessFrom;
    }

    public void setBusinessFrom(String businessFrom) {
        this.businessFrom = businessFrom;
    }

    public String getBusinessContent() {
        return businessContent;
    }

    public void setBusinessContent(String businessContent) {
        this.businessContent = businessContent;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getBackUrl() { return backUrl; }

    public void setBackUrl(String backUrl) { this.backUrl = backUrl; }

    public String getReservedMsg() { return reservedMsg; }

    public void setReservedMsg(String reservedMsg) { this.reservedMsg = reservedMsg; }

    public String getCreaterUserId() { return createrUserId; }

    public void setCreaterUserId(String createrUserId) { this.createrUserId = createrUserId; }
}
