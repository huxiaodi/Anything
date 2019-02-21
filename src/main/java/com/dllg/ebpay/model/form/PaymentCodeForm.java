package com.dllg.ebpay.model.form;

public class PaymentCodeForm {
    private String orderId; //本次交易流水号
    private String type;  //交易类型
    private String P2PCode;//第三方平台ID
    private String P2PType; //第三方平台类型
    private String thirdCustId; //会员代码
    private String custAccId; //会员子账号
    private String name; //会员名称
    private String idType; //会员证件类型
    private String idNo; //会员证件号码
    private String accNo; // 充值提现账户账号/卡号
    private String accName;//充值提现账户名称
    private String mobile; // 会员手机号
    private String orig;//签名源数据串
    private String returnurl;//回调第三方url
    private String notifyUrl; //交易结果通知第三方url
    private String channelType; //交易渠道类型
    private String frontUrl;

    public String getFrontUrl() {
        return frontUrl;
    }

    public void setFrontUrl(String frontUrl) {
        this.frontUrl = frontUrl;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getP2PCode() {
        return P2PCode;
    }

    public void setP2PCode(String p2PCode) {
        P2PCode = p2PCode;
    }

    public String getP2PType() {
        return P2PType;
    }

    public void setP2PType(String p2PType) {
        P2PType = p2PType;
    }

    public String getThirdCustId() {
        return thirdCustId;
    }

    public void setThirdCustId(String thirdCustId) {
        this.thirdCustId = thirdCustId;
    }

    public String getCustAccId() {
        return custAccId;
    }

    public void setCustAccId(String custAccId) {
        this.custAccId = custAccId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getAccNo() {
        return accNo;
    }

    public void setAccNo(String accNo) {
        this.accNo = accNo;
    }

    public String getAccName() {
        return accName;
    }

    public void setAccName(String accName) {
        this.accName = accName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getOrig() {
        return orig;
    }

    public void setOrig(String orig) {
        this.orig = orig;
    }

    public String getReturnurl() {
        return returnurl;
    }

    public void setReturnurl(String returnurl) {
        this.returnurl = returnurl;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }


}
