package com.dllg.ebpay.model.response;

import com.dllg.ebpay.model.request.ApiPaymentDisposeRequest;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description：
 * @Author:hudi
 * @Date: Created in 15:18 2018/4/2
 */
public class ApiPaymentCallBackJsonResponse {

    private String tradeNo;
    private String tradeBankNo;
    private String tradeType;
    private String tradeFeatureIdentifier;
    private BigDecimal tradeTranAmt;
    private BigDecimal tradeTranFee;
    private Date tradeTranDate;
    private String tradeTranType;
    private String tradeTranStatus;
    private String tradeTranPaymentType;
    private String tradeBankName;
    private String tradeInAccountSubId;
    private String createUserId;

    private String relationId;
    private String accountSubNo;
    private String inAccountSubNo;
    private String businessTradeId;
    private String businessNo;
    private String businessType;
    private String businessFrom;
    private String businessContent;
    private String remark;
    private String status;
    private String reservedMsg;

    private String applicationNo; // 支付申请单号
    private String proposer; // 支付申请人
    private String applicationStatus; // 支付申请状态
    private String payUserName; // 支付人
    private String rejectReason; // 支付申请审核原因
    private String payType; // 付款方式  === 固定值：在线交易


    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getReservedMsg() {
        return reservedMsg;
    }

    public void setReservedMsg(String reservedMsg) {
        this.reservedMsg = reservedMsg;
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

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getTradeBankNo() {
        return tradeBankNo;
    }

    public void setTradeBankNo(String tradeBankNo) {
        this.tradeBankNo = tradeBankNo;
    }

    public String getTradeType() {
        return tradeType;
    }

    public void setTradeType(String tradeType) {
        this.tradeType = tradeType;
    }

    public String getTradeFeatureIdentifier() {
        return tradeFeatureIdentifier;
    }

    public void setTradeFeatureIdentifier(String tradeFeatureIdentifier) {
        this.tradeFeatureIdentifier = tradeFeatureIdentifier;
    }

    public BigDecimal getTradeTranAmt() {
        return tradeTranAmt;
    }

    public void setTradeTranAmt(BigDecimal tradeTranAmt) {
        this.tradeTranAmt = tradeTranAmt;
    }

    public BigDecimal getTradeTranFee() {
        return tradeTranFee;
    }

    public void setTradeTranFee(BigDecimal tradeTranFee) {
        this.tradeTranFee = tradeTranFee;
    }

    public Date getTradeTranDate() {
        return tradeTranDate;
    }

    public void setTradeTranDate(Date tradeTranDate) {
        this.tradeTranDate = tradeTranDate;
    }

    public String getTradeTranType() {
        return tradeTranType;
    }

    public void setTradeTranType(String tradeTranType) {
        this.tradeTranType = tradeTranType;
    }

    public String getTradeTranStatus() {
        return tradeTranStatus;
    }

    public void setTradeTranStatus(String tradeTranStatus) {
        this.tradeTranStatus = tradeTranStatus;
    }

    public String getTradeTranPaymentType() {
        return tradeTranPaymentType;
    }

    public void setTradeTranPaymentType(String tradeTranPaymentType) {
        this.tradeTranPaymentType = tradeTranPaymentType;
    }


    public String getTradeBankName() {
        return tradeBankName;
    }

    public void setTradeBankName(String tradeBankName) {
        this.tradeBankName = tradeBankName;
    }

    public String getTradeInAccountSubId() {
        return tradeInAccountSubId;
    }

    public void setTradeInAccountSubId(String tradeInAccountSubId) {
        this.tradeInAccountSubId = tradeInAccountSubId;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public String getBusinessTradeId() {
        return businessTradeId;
    }

    public void setBusinessTradeId(String businessTradeId) {
        this.businessTradeId = businessTradeId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getApplicationNo() {
        return applicationNo;
    }

    public void setApplicationNo(String applicationNo) {
        this.applicationNo = applicationNo;
    }

    public String getProposer() {
        return proposer;
    }

    public void setProposer(String proposer) {
        this.proposer = proposer;
    }

    public String getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(String applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    public String getPayUserName() {
        return payUserName;
    }

    public void setPayUserName(String payUserName) {
        this.payUserName = payUserName;
    }

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }
}
