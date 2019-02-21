package com.dllg.ebpay.model.form;

import com.dllg.framework.model.BaseForm;

public class AccountForm extends BaseForm {

    private String state;
    private String userId;
    private String accountSubNo;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAccountSubNo() {
        return accountSubNo;
    }

    public void setAccountSubNo(String accountSubNo) {
        this.accountSubNo = accountSubNo;
    }
}
