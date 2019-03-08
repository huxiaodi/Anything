package com.test.manage.model.generator;

import java.util.Date;

/**
 *   用户表
 *   user
 * @mbg.generated do_not_delete_during_merge
 */
public class User {
    /**
     *   用户ID
     *   user.user_id
     * @mbg.generated
     */
    private String userId;

    /**
     *   企业id
     *   user.user_company_id
     * @mbg.generated
     */
    private String userCompanyId;

    /**
     *   用户名
     *   user.user_name
     * @mbg.generated
     */
    private String userName;

    /**
     *   密码
     *   user.password
     * @mbg.generated
     */
    private String password;

    /**
     *   昵称
     *   user.user_nickname
     * @mbg.generated
     */
    private String userNickname;

    /**
     *   类型,0-后台用户,1-个人,2-企业
     *   user.user_type
     * @mbg.generated
     */
    private Integer userType;

    /**
     *   姓名
     *   user.user_cn_name
     * @mbg.generated
     */
    private String userCnName;

    /**
     *   证件类型,1-身份证
     *   user.user_certificate_type
     * @mbg.generated
     */
    private String userCertificateType;

    /**
     *   证件号码
     *   user.user_certificate_number
     * @mbg.generated
     */
    private String userCertificateNumber;

    /**
     *   身份证号码
     *   user.user_id_card
     * @mbg.generated
     */
    private String userIdCard;

    /**
     *   身份证正面图片
     *   user.user_id_card_font_img
     * @mbg.generated
     */
    private String userIdCardFontImg;

    /**
     *   身份证反面图片
     *   user.user_id_card_back_img
     * @mbg.generated
     */
    private String userIdCardBackImg;

    /**
     *   头像图片
     *   user.user_head_img
     * @mbg.generated
     */
    private String userHeadImg;

    /**
     *   手机号码
     *   user.user_mobile
     * @mbg.generated
     */
    private String userMobile;

    /**
     *   邮箱
     *   user.user_email
     * @mbg.generated
     */
    private String userEmail;

    /**
     *   注册来源
     *   user.user_register_from
     * @mbg.generated
     */
    private String userRegisterFrom;

    /**
     *   调账密码
     *   user.user_pay_password
     * @mbg.generated
     */
    private String userPayPassword;

    /**
     *   0-禁用,1启用
     *   user.user_status
     * @mbg.generated
     */
    private Integer userStatus;

    /**
     *   禁用原因
     *   user.user_status_explain
     * @mbg.generated
     */
    private String userStatusExplain;

    /**
     *   创建时间
     *   user.user_create_time
     * @mbg.generated
     */
    private Date userCreateTime;

    /**
     *   创建人
     *   user.user_create_user_id
     * @mbg.generated
     */
    private String userCreateUserId;

    /**
     *   修改时间
     *   user.user_update_time
     * @mbg.generated
     */
    private Date userUpdateTime;

    /**
     *   修改人
     *   user.user_update_user_id
     * @mbg.generated
     */
    private String userUpdateUserId;

    /**
     *   是否删除 0-否,1-是
     *   user.user_is_delete
     * @mbg.generated
     */
    private Boolean userIsDelete;

    /**
     * 用户ID
     * @return the value of user.user_id
     * @mbg.generated
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 用户ID
     * @param userId the value for user.user_id
     * @mbg.generated
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * 企业id
     * @return the value of user.user_company_id
     * @mbg.generated
     */
    public String getUserCompanyId() {
        return userCompanyId;
    }

    /**
     * 企业id
     * @param userCompanyId the value for user.user_company_id
     * @mbg.generated
     */
    public void setUserCompanyId(String userCompanyId) {
        this.userCompanyId = userCompanyId == null ? null : userCompanyId.trim();
    }

    /**
     * 用户名
     * @return the value of user.user_name
     * @mbg.generated
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 用户名
     * @param userName the value for user.user_name
     * @mbg.generated
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * 密码
     * @return the value of user.password
     * @mbg.generated
     */
    public String getPassword() {
        return password;
    }

    /**
     * 密码
     * @param password the value for user.password
     * @mbg.generated
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 昵称
     * @return the value of user.user_nickname
     * @mbg.generated
     */
    public String getUserNickname() {
        return userNickname;
    }

    /**
     * 昵称
     * @param userNickname the value for user.user_nickname
     * @mbg.generated
     */
    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname == null ? null : userNickname.trim();
    }

    /**
     * 类型,0-后台用户,1-个人,2-企业
     * @return the value of user.user_type
     * @mbg.generated
     */
    public Integer getUserType() {
        return userType;
    }

    /**
     * 类型,0-后台用户,1-个人,2-企业
     * @param userType the value for user.user_type
     * @mbg.generated
     */
    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    /**
     * 姓名
     * @return the value of user.user_cn_name
     * @mbg.generated
     */
    public String getUserCnName() {
        return userCnName;
    }

    /**
     * 姓名
     * @param userCnName the value for user.user_cn_name
     * @mbg.generated
     */
    public void setUserCnName(String userCnName) {
        this.userCnName = userCnName == null ? null : userCnName.trim();
    }

    /**
     * 证件类型,1-身份证
     * @return the value of user.user_certificate_type
     * @mbg.generated
     */
    public String getUserCertificateType() {
        return userCertificateType;
    }

    /**
     * 证件类型,1-身份证
     * @param userCertificateType the value for user.user_certificate_type
     * @mbg.generated
     */
    public void setUserCertificateType(String userCertificateType) {
        this.userCertificateType = userCertificateType == null ? null : userCertificateType.trim();
    }

    /**
     * 证件号码
     * @return the value of user.user_certificate_number
     * @mbg.generated
     */
    public String getUserCertificateNumber() {
        return userCertificateNumber;
    }

    /**
     * 证件号码
     * @param userCertificateNumber the value for user.user_certificate_number
     * @mbg.generated
     */
    public void setUserCertificateNumber(String userCertificateNumber) {
        this.userCertificateNumber = userCertificateNumber == null ? null : userCertificateNumber.trim();
    }

    /**
     * 身份证号码
     * @return the value of user.user_id_card
     * @mbg.generated
     */
    public String getUserIdCard() {
        return userIdCard;
    }

    /**
     * 身份证号码
     * @param userIdCard the value for user.user_id_card
     * @mbg.generated
     */
    public void setUserIdCard(String userIdCard) {
        this.userIdCard = userIdCard == null ? null : userIdCard.trim();
    }

    /**
     * 身份证正面图片
     * @return the value of user.user_id_card_font_img
     * @mbg.generated
     */
    public String getUserIdCardFontImg() {
        return userIdCardFontImg;
    }

    /**
     * 身份证正面图片
     * @param userIdCardFontImg the value for user.user_id_card_font_img
     * @mbg.generated
     */
    public void setUserIdCardFontImg(String userIdCardFontImg) {
        this.userIdCardFontImg = userIdCardFontImg == null ? null : userIdCardFontImg.trim();
    }

    /**
     * 身份证反面图片
     * @return the value of user.user_id_card_back_img
     * @mbg.generated
     */
    public String getUserIdCardBackImg() {
        return userIdCardBackImg;
    }

    /**
     * 身份证反面图片
     * @param userIdCardBackImg the value for user.user_id_card_back_img
     * @mbg.generated
     */
    public void setUserIdCardBackImg(String userIdCardBackImg) {
        this.userIdCardBackImg = userIdCardBackImg == null ? null : userIdCardBackImg.trim();
    }

    /**
     * 头像图片
     * @return the value of user.user_head_img
     * @mbg.generated
     */
    public String getUserHeadImg() {
        return userHeadImg;
    }

    /**
     * 头像图片
     * @param userHeadImg the value for user.user_head_img
     * @mbg.generated
     */
    public void setUserHeadImg(String userHeadImg) {
        this.userHeadImg = userHeadImg == null ? null : userHeadImg.trim();
    }

    /**
     * 手机号码
     * @return the value of user.user_mobile
     * @mbg.generated
     */
    public String getUserMobile() {
        return userMobile;
    }

    /**
     * 手机号码
     * @param userMobile the value for user.user_mobile
     * @mbg.generated
     */
    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile == null ? null : userMobile.trim();
    }

    /**
     * 邮箱
     * @return the value of user.user_email
     * @mbg.generated
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * 邮箱
     * @param userEmail the value for user.user_email
     * @mbg.generated
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail == null ? null : userEmail.trim();
    }

    /**
     * 注册来源
     * @return the value of user.user_register_from
     * @mbg.generated
     */
    public String getUserRegisterFrom() {
        return userRegisterFrom;
    }

    /**
     * 注册来源
     * @param userRegisterFrom the value for user.user_register_from
     * @mbg.generated
     */
    public void setUserRegisterFrom(String userRegisterFrom) {
        this.userRegisterFrom = userRegisterFrom == null ? null : userRegisterFrom.trim();
    }

    /**
     * 调账密码
     * @return the value of user.user_pay_password
     * @mbg.generated
     */
    public String getUserPayPassword() {
        return userPayPassword;
    }

    /**
     * 调账密码
     * @param userPayPassword the value for user.user_pay_password
     * @mbg.generated
     */
    public void setUserPayPassword(String userPayPassword) {
        this.userPayPassword = userPayPassword == null ? null : userPayPassword.trim();
    }

    /**
     * 0-禁用,1启用
     * @return the value of user.user_status
     * @mbg.generated
     */
    public Integer getUserStatus() {
        return userStatus;
    }

    /**
     * 0-禁用,1启用
     * @param userStatus the value for user.user_status
     * @mbg.generated
     */
    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    /**
     * 禁用原因
     * @return the value of user.user_status_explain
     * @mbg.generated
     */
    public String getUserStatusExplain() {
        return userStatusExplain;
    }

    /**
     * 禁用原因
     * @param userStatusExplain the value for user.user_status_explain
     * @mbg.generated
     */
    public void setUserStatusExplain(String userStatusExplain) {
        this.userStatusExplain = userStatusExplain == null ? null : userStatusExplain.trim();
    }

    /**
     * 创建时间
     * @return the value of user.user_create_time
     * @mbg.generated
     */
    public Date getUserCreateTime() {
        return userCreateTime;
    }

    /**
     * 创建时间
     * @param userCreateTime the value for user.user_create_time
     * @mbg.generated
     */
    public void setUserCreateTime(Date userCreateTime) {
        this.userCreateTime = userCreateTime;
    }

    /**
     * 创建人
     * @return the value of user.user_create_user_id
     * @mbg.generated
     */
    public String getUserCreateUserId() {
        return userCreateUserId;
    }

    /**
     * 创建人
     * @param userCreateUserId the value for user.user_create_user_id
     * @mbg.generated
     */
    public void setUserCreateUserId(String userCreateUserId) {
        this.userCreateUserId = userCreateUserId == null ? null : userCreateUserId.trim();
    }

    /**
     * 修改时间
     * @return the value of user.user_update_time
     * @mbg.generated
     */
    public Date getUserUpdateTime() {
        return userUpdateTime;
    }

    /**
     * 修改时间
     * @param userUpdateTime the value for user.user_update_time
     * @mbg.generated
     */
    public void setUserUpdateTime(Date userUpdateTime) {
        this.userUpdateTime = userUpdateTime;
    }

    /**
     * 修改人
     * @return the value of user.user_update_user_id
     * @mbg.generated
     */
    public String getUserUpdateUserId() {
        return userUpdateUserId;
    }

    /**
     * 修改人
     * @param userUpdateUserId the value for user.user_update_user_id
     * @mbg.generated
     */
    public void setUserUpdateUserId(String userUpdateUserId) {
        this.userUpdateUserId = userUpdateUserId == null ? null : userUpdateUserId.trim();
    }

    /**
     * 是否删除 0-否,1-是
     * @return the value of user.user_is_delete
     * @mbg.generated
     */
    public Boolean getUserIsDelete() {
        return userIsDelete;
    }

    /**
     * 是否删除 0-否,1-是
     * @param userIsDelete the value for user.user_is_delete
     * @mbg.generated
     */
    public void setUserIsDelete(Boolean userIsDelete) {
        this.userIsDelete = userIsDelete;
    }
}