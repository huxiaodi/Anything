package com.dllg.pabank.model.request;

public class Params extends PABankRequest {
    private String newPayPassword;
    private String oldPayPassword;
    private String userMobile;
    private String userId;
    private String checkNum;

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getCheckNum() {
        return checkNum;
    }

    public void setCheckNum(String checkNum) {
        this.checkNum = checkNum;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNewPayPassword() {
        return newPayPassword;
    }

    public void setNewPayPassword(String newPayPassword) {
        this.newPayPassword = newPayPassword;
    }

    public String getOldPayPassword() {
        return oldPayPassword;
    }

    public void setOldPayPassword(String oldPayPassword) {
        this.oldPayPassword = oldPayPassword;
    }
}
