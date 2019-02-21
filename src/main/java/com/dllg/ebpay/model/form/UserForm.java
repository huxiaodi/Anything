package com.dllg.ebpay.model.form;

import com.dllg.ebpay.model.generator.User;
import com.dllg.framework.model.BaseForm;

import java.util.Date;

public class UserForm extends BaseForm {
    User user;
    String Verification;
    private String userId;
    private String userCompanyId;
    private Integer userType;
    private Date startTime;
    private Date endTime;
    private String keyword;
    private String status; //状态

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getVerification() {
        return Verification;
    }

    public void setVerification(String verification) {
        Verification = verification;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserCompanyId() {
        return userCompanyId;
    }

    public void setUserCompanyId(String userCompanyId) {
        this.userCompanyId = userCompanyId;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
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

    @Override
    public String getKeyword() {
        return keyword;
    }

    @Override
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
