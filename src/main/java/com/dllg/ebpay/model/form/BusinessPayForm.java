package com.dllg.ebpay.model.form;

import javax.validation.constraints.NotNull;

public class BusinessPayForm {
    @NotNull(message = "订单号不能为空")
    private String orderId; //订单号
    @NotNull(message = "支付金额不能为空")
    private String money;  //支付金额
    @NotNull(message = "充值子账户不能为空")
    private String accountSubNo; //充值子账户
    @NotNull(message = "业务类型不能为空")
    private String businessTradeBusinessType; //业务类型
    @NotNull(message = "交易来源不能为空")
    private String businessTradeBusinessFrom; //交易来源
    @NotNull(message = "回调地址不能为空")
    private String url;    //回调地址
    @NotNull(message = "交易对象不能为空")
    private String businessTradeObject; //交易对象
    @NotNull(message = "备注不能为空")
    private String remark; // 备注
    @NotNull(message = "手续费不能为空")
    private String fee;//手续费
    @NotNull(message = "清分类型不能为空")
    private String businessTradeClearingType;//清分类型
    @NotNull(message = "品名不能为空")
    private String businessTradeGoodsName;//品名
    @NotNull(message = "交易单号不能为空")
    private String businessTradeTrasactionNo;//交易单号


    public String getBusinessTradeTrasactionNo() {
        return businessTradeTrasactionNo;
    }

    public void setBusinessTradeTrasactionNo(String businessTradeTrasactionNo) {
        this.businessTradeTrasactionNo = businessTradeTrasactionNo;
    }

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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getFee() {
        return fee;
    }

    public void setFee(String fee) {
        this.fee = fee;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getAccountSubNo() {
        return accountSubNo;
    }

    public void setAccountSubNo(String accountSubNo) {
        this.accountSubNo = accountSubNo;
    }


    public String getBusinessTradeBusinessType() {
        return businessTradeBusinessType;
    }

    public void setBusinessTradeBusinessType(String businessTradeBusinessType) {
        this.businessTradeBusinessType = businessTradeBusinessType;
    }

    public String getBusinessTradeBusinessFrom() {
        return businessTradeBusinessFrom;
    }

    public void setBusinessTradeBusinessFrom(String businessTradeBusinessFrom) {
        this.businessTradeBusinessFrom = businessTradeBusinessFrom;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getBusinessTradeObject() {
        return businessTradeObject;
    }

    public void setBusinessTradeObject(String businessTradeObject) {
        this.businessTradeObject = businessTradeObject;
    }
}
