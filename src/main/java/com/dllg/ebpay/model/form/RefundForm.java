package com.dllg.ebpay.model.form;

import javax.validation.constraints.NotNull;
import com.dllg.framework.model.BaseForm;

import java.math.BigDecimal;
import java.util.Date;

public class RefundForm extends BaseForm {

    private String accountSubId;

    private Date startTime; // 开始时间

    private Date endTime;   // 结束时间

    private Integer businessFrom;   // 交易平台

    private String refundStatus; // 申请状态

    @NotNull(message = "流水单号不能为空")
    private String refundTradeBankNo;

    @NotNull(message = "退款单号不能为空")
    private String refundOrderNo;

    @NotNull(message = "申请人不能为空")
    private String refundProposer;

    private String refundCompany; // 申请企业
    @NotNull(message = "金额不能为空")
    private BigDecimal refundAmount;
    @NotNull(message = "手续费不能为空")
    private BigDecimal refundFee;

    private String refundRemark;
    @NotNull(message = "回调地址不能为空")
    private String refundBackUrl;


    public String getRefundOrderNo() {
        return refundOrderNo;
    }

    public void setRefundOrderNo(String refundOrderNo) {
        this.refundOrderNo = refundOrderNo;
    }

    public String getAccountSubId() {
        return accountSubId;
    }

    public void setAccountSubId(String accountSubId) {
        this.accountSubId = accountSubId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getBusinessFrom() {
        return businessFrom;
    }

    public void setBusinessFrom(Integer businessFrom) {
        this.businessFrom = businessFrom;
    }

    public String getRefundStatus() {
        return refundStatus;
    }

    public void setRefundStatus(String refundStatus) {
        this.refundStatus = refundStatus;
    }

    public String getRefundTradeBankNo() {
        return refundTradeBankNo;
    }

    public void setRefundTradeBankNo(String refundTradeBankNo) {
        this.refundTradeBankNo = refundTradeBankNo;
    }

    public String getRefundProposer() {
        return refundProposer;
    }

    public void setRefundProposer(String refundProposer) {
        this.refundProposer = refundProposer;
    }

    public String getRefundCompany() {
        return refundCompany;
    }

    public void setRefundCompany(String refundCompany) {
        this.refundCompany = refundCompany;
    }

    public BigDecimal getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(BigDecimal refundAmount) {
        this.refundAmount = refundAmount;
    }

    public BigDecimal getRefundFee() {
        return refundFee;
    }

    public void setRefundFee(BigDecimal refundFee) {
        this.refundFee = refundFee;
    }

    public String getRefundRemark() {
        return refundRemark;
    }

    public void setRefundRemark(String refundRemark) {
        this.refundRemark = refundRemark;
    }

    public String getRefundBackUrl() {
        return refundBackUrl;
    }

    public void setRefundBackUrl(String refundBackUrl) {
        this.refundBackUrl = refundBackUrl;
    }
}
