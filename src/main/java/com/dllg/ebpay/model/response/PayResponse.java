package com.dllg.ebpay.model.response;

public class PayResponse {
    private String orig;
    private String sign;
    private String returnurl;
    private String notifyurl;
    private String action;
    private String orderId;
    private String errorMsg;

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getOrig() {
        return orig;
    }

    public void setOrig(String orig) {
        this.orig = orig;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getReturnurl() {
        return returnurl;
    }

    public void setReturnurl(String returnurl) {
        this.returnurl = returnurl;
    }

    public String getNotifyurl() {
        return notifyurl;
    }

    public void setNotifyurl(String notifyurl) {
        this.notifyurl = notifyurl;
    }
}
