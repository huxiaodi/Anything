package com.dllg.ebpay.model.request;

import org.hibernate.validator.constraints.NotBlank;

import java.util.List;


public class UserRequest {

    @NotBlank(message = "用户名不能为空")
    private String userName;

    private String userPassword;

    @NotBlank(message = "新密码不能为空")
    private String newPassword;

    @NotBlank(message = "手机号不能为空")
    private String mobile;

    @NotBlank(message = "验证码不能为空")
    private String verification;

    // 顶象验证码
    private String token;

    private String newMobile;

    private String newVerification;

    private String userCnName;

    // 关联角色id
    private List<String> roleIds;

    // 关联角色id
    private List<String> teamIds;

    // 用户id
    private String userId;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getVerification() {
        return verification;
    }

    public void setVerification(String verification) {
        this.verification = verification;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNewMobile() {
        return newMobile;
    }

    public void setNewMobile(String newMobile) {
        this.newMobile = newMobile;
    }

    public String getNewVerification() {
        return newVerification;
    }

    public void setNewVerification(String newVerification) {
        this.newVerification = newVerification;
    }

    public List<String> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<String> roleIds) {
        this.roleIds = roleIds;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<String> getTeamIds() {
        return teamIds;
    }

    public void setTeamIds(List<String> teamIds) {
        this.teamIds = teamIds;
    }

    public String getUserCnName() {
        return userCnName;
    }

    public void setUserCnName(String userCnName) {
        this.userCnName = userCnName;
    }
}
