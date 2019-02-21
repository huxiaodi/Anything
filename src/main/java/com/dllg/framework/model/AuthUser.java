package com.dllg.framework.model;

/**
 * Created by hugo on 2017/11/19.
 */
public class AuthUser {
    private String userToken;//会员号
    private String userId;//用户ID
    private String userName;//用户名
    private String userCnName;//用户名
    private Integer userType;//用户类型
    private String userCompanyId;//企业ID
    private String userCompanyName;//企业名称
    private String accountSubNo;//子账户账号
    private String userHeadImg; // 用户头像

    public String getUserCnName() { return userCnName; }

    public void setUserCnName(String userCnName) { this.userCnName = userCnName; }

    public String getUserHeadImg() { return userHeadImg; }

    public void setUserHeadImg(String userHeadImg) { this.userHeadImg = userHeadImg; }

    public String getAccountSubNo() {
        return accountSubNo;
    }

    public void setAccountSubNo(String accountSubNo) {
        this.accountSubNo = accountSubNo;
    }

    public String getUserCompanyName() { return userCompanyName; }

    public void setUserCompanyName(String userCompanyName) { this.userCompanyName = userCompanyName; }

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

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
