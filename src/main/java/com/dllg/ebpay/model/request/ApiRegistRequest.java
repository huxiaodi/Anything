package com.dllg.ebpay.model.request;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;


public class ApiRegistRequest {

    /**
     * 用户类型
     */
    @NotBlank(message = "用户类型不能为空")
    private Integer userType;
    /**
     * 手机验证码
     */
    private String userVerificationCode;
    /**
     * 身份证正面/统一信用代码照片
     */
    private MultipartFile font;
    /**
     * 身份证背面
     */
    private MultipartFile back;
    /**
     * 营业执照
     */
    private MultipartFile companyLicenseNoImg;
    /**
     * 税务登记证
     */
    private MultipartFile companyTaxNoImg;
    /**
     * 组织机构代码照片
     */
    private MultipartFile companyOrganizationCodeImg;
    /**
     * 关联id
     */
    private String accountSubBindRelationId;
    /**
     * 注册来源
     */
    private String accountSubBindFrom;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 子账户号
     */
    private String SubAcctNo;
    /**
     * 用户ID
     * user.user_id
     *
     * @mbg.generated
     */
    private String userId;

    /**
     * 企业id
     * user.user_company_id
     *
     * @mbg.generated
     */
    private String userCompanyId;

    /**
     * 用户名
     * user.user_name
     *
     * @mbg.generated
     */
    private String userName;

    /**
     * 密码
     * user.password
     *
     * @mbg.generated
     */
    private String password;

    /**
     * 昵称
     * user.user_nickname
     *
     * @mbg.generated
     */
    private String userNickname;

    /**
     * 姓名
     * user.user_cn_name
     *
     * @mbg.generated
     */
    private String userCnName;

    /**
     * 证件类型
     * user.user_certificate_type
     *
     * @mbg.generated
     */
    private String userCertificateType;

    /**
     * 证件号码
     * user.user_certificate_number
     *
     * @mbg.generated
     */
    private String userCertificateNumber;

    /**
     * 身份证号码
     * user.user_id_card
     *
     * @mbg.generated
     */
    private String userIdCard;

    /**
     * 身份证正面图片
     * user.user_id_card_font_img
     *
     * @mbg.generated
     */
    private String userIdCardFontImg;

    /**
     * 身份证反面图片
     * user.user_id_card_back_img
     *
     * @mbg.generated
     */
    private String userIdCardBackImg;

    /**
     * 头像图片
     * user.user_head_img
     *
     * @mbg.generated
     */
    private String userHeadImg;

    /**
     * 手机号码
     * user.user_mobile
     *
     * @mbg.generated
     */
    private String userMobile;

    /**
     * 邮箱
     * user.user_email
     *
     * @mbg.generated
     */
    private String userEmail;

    /**
     * 注册来源
     * user.user_register_from
     *
     * @mbg.generated
     */
    private String userRegisterFrom;

    /**
     * 支付密码
     * user.user_pay_password
     *
     * @mbg.generated
     */
    private String userPayPassword;

    /**
     * 0-禁用,1启用
     * user.user_status
     *
     * @mbg.generated
     */
    private Integer userStatus;

    /**
     * 禁用原因
     * user.user_status_explain
     *
     * @mbg.generated
     */
    private String userStatusExplain;

    /**
     * 创建时间
     * user.user_create_time
     *
     * @mbg.generated
     */
    private Date userCreateTime;

    /**
     * 创建人
     * user.user_create_user_id
     *
     * @mbg.generated
     */
    private String userCreateUserId;

    /**
     * 修改时间
     * user.user_update_time
     *
     * @mbg.generated
     */
    private Date userUpdateTime;

    /**
     * 修改人
     * user.user_update_user_id
     *
     * @mbg.generated
     */
    private String userUpdateUserId;

    /**
     * 是否删除 0-否,1-是
     * user.user_is_delete
     *
     * @mbg.generated
     */
    private Boolean userIsDelete;
    /**
     * 主键
     * company.company_id
     *
     * @mbg.generated
     */
    private String companyId;

    /**
     * 企业名称
     * company.company_name
     *
     * @mbg.generated
     */
    private String companyName;

    /**
     * 公司简称
     * company.company_short_name
     *
     * @mbg.generated
     */
    private String companyShortName;

    /**
     * 省
     * company.company_province
     *
     * @mbg.generated
     */
    private String companyProvince;

    /**
     * city
     * company.company_city
     *
     * @mbg.generated
     */
    private String companyCity;

    /**
     * 县区
     * company.company_district
     *
     * @mbg.generated
     */
    private String companyDistrict;

    /**
     * 公司详细地址
     * company.company_address
     *
     * @mbg.generated
     */
    private String companyAddress;

    /**
     * 联系人
     * company.company_contact
     *
     * @mbg.generated
     */
    private String companyContact;

    /**
     * 联系人电话
     * company.company_contact_tel
     *
     * @mbg.generated
     */
    private String companyContactTel;

    /**
     * 联系人身份证号码
     * company.company_contact_id_card
     *
     * @mbg.generated
     */
    private String companyContactIdCard;

    /**
     * 注册资金（单位万元）
     * company.company_registed_capital
     *
     * @mbg.generated
     */
    private String companyRegistedCapital;

    /**
     * 是否三证合一,0否,1是
     * company.company_is_three_in_one
     *
     * @mbg.generated
     */
    private Boolean companyIsThreeInOne;

    /**
     * 统一社会信用代码
     * company.company_unified_social_credit_ldentifier
     *
     * @mbg.generated
     */
    private String companyUnifiedSocialCreditLdentifier;

    /**
     * 统一社会信用照片
     * company.company_unified_social_img
     *
     * @mbg.generated
     */
    private String companyUnifiedSocialImg;

    /**
     * 统一社会信用代码注册时间
     * company.company_unified_social_datetime
     *
     * @mbg.generated
     */
    private String companyUnifiedSocialDatetime;

    /**
     * 统一社会信用代码证有效期至
     * company.company_unified_social_limit_datetime
     *
     * @mbg.generated
     */
    private String companyUnifiedSocialLimitDatetime;

    /**
     * 营业执照号码
     * company.company_license_no
     *
     * @mbg.generated
     */
    private String companyLicenseNo;

    /**
     * 税务登记证号码
     * company.company_tax_no
     *
     * @mbg.generated
     */
    private String companyTaxNo;

    /**
     * 组织结构代码号码
     * company.company_organization_code
     *
     * @mbg.generated
     */
    private String companyOrganizationCode;

    /**
     * 企业法人名称
     * company.company_principal_name
     *
     * @mbg.generated
     */
    private String companyPrincipalName;

    /**
     * 企业法人手机号码
     * company.company_principal_tel
     *
     * @mbg.generated
     */
    private String companyPrincipalTel;

    /**
     * 企业法人证件号码
     * company.company_principal_id_code
     *
     * @mbg.generated
     */
    private String companyPrincipalIdCode;

    /**
     * 对公账户开户行
     * company.company_to_public_bank
     *
     * @mbg.generated
     */
    private String companyToPublicBank;

    /**
     * 对公账户账号
     * company.company_to_public_account_bank
     *
     * @mbg.generated
     */
    private String companyToPublicAccountBank;

    /**
     * 纳税人识别号
     * company.company_taxpayer_registration_number
     *
     * @mbg.generated
     */
    private String companyTaxpayerRegistrationNumber;

    /**
     * 企业电话
     * company.company_tel
     *
     * @mbg.generated
     */
    private String companyTel;

    /**
     * 公司传真
     * company.company_fax
     *
     * @mbg.generated
     */
    private String companyFax;

    /**
     * 公司邮箱
     * company.company_email
     *
     * @mbg.generated
     */
    private String companyEmail;

    /**
     * 开户许可证
     * company.company_accounts_permits
     *
     * @mbg.generated
     */
    private String companyAccountsPermits;

    /**
     * 状态
     * company.company_status
     *
     * @mbg.generated
     */
    private Integer companyStatus;

    /**
     * 创建时间
     * company.company_create_time
     *
     * @mbg.generated
     */
    private Date companyCreateTime;

    /**
     * 创建人
     * company.company_create_user_id
     *
     * @mbg.generated
     */
    private String companyCreateUserId;

    /**
     * 修改时间
     * company.company_update_time
     *
     * @mbg.generated
     */
    private Date companyUpdateTime;

    /**
     * 修改人
     * company.company_update_user_id
     *
     * @mbg.generated
     */
    private String companyUpdateUserId;

    /**
     * 是否删除 0-否,1-是
     * company.company_is_delete
     *
     * @mbg.generated
     */
    private Boolean companyIsDelete;

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

    public String getUserVerificationCode() {
        return userVerificationCode;
    }

    public void setUserVerificationCode(String userVerificationCode) {
        this.userVerificationCode = userVerificationCode;
    }

    public MultipartFile getFont() {
        return font;
    }

    public void setFont(MultipartFile font) {
        this.font = font;
    }

    public MultipartFile getBack() {
        return back;
    }

    public void setBack(MultipartFile back) {
        this.back = back;
    }

    public MultipartFile getCompanyLicenseNoImg() {
        return companyLicenseNoImg;
    }

    public void setCompanyLicenseNoImg(MultipartFile companyLicenseNoImg) {
        this.companyLicenseNoImg = companyLicenseNoImg;
    }

    public MultipartFile getCompanyTaxNoImg() {
        return companyTaxNoImg;
    }

    public void setCompanyTaxNoImg(MultipartFile companyTaxNoImg) {
        this.companyTaxNoImg = companyTaxNoImg;
    }

    public MultipartFile getCompanyOrganizationCodeImg() {
        return companyOrganizationCodeImg;
    }

    public void setCompanyOrganizationCodeImg(MultipartFile companyOrganizationCodeImg) {
        this.companyOrganizationCodeImg = companyOrganizationCodeImg;
    }

    public String getAccountSubBindRelationId() {
        return accountSubBindRelationId;
    }

    public void setAccountSubBindRelationId(String accountSubBindRelationId) {
        this.accountSubBindRelationId = accountSubBindRelationId;
    }

    public String getAccountSubBindFrom() {
        return accountSubBindFrom;
    }

    public void setAccountSubBindFrom(String accountSubBindFrom) {
        this.accountSubBindFrom = accountSubBindFrom;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSubAcctNo() {
        return SubAcctNo;
    }

    public void setSubAcctNo(String subAcctNo) {
        SubAcctNo = subAcctNo;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserNickname() {
        return userNickname;
    }

    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }

    public String getUserCnName() {
        return userCnName;
    }

    public void setUserCnName(String userCnName) {
        this.userCnName = userCnName;
    }

    public String getUserCertificateType() {
        return userCertificateType;
    }

    public void setUserCertificateType(String userCertificateType) {
        this.userCertificateType = userCertificateType;
    }

    public String getUserCertificateNumber() {
        return userCertificateNumber;
    }

    public void setUserCertificateNumber(String userCertificateNumber) {
        this.userCertificateNumber = userCertificateNumber;
    }

    public String getUserIdCard() {
        return userIdCard;
    }

    public void setUserIdCard(String userIdCard) {
        this.userIdCard = userIdCard;
    }

    public String getUserIdCardFontImg() {
        return userIdCardFontImg;
    }

    public void setUserIdCardFontImg(String userIdCardFontImg) {
        this.userIdCardFontImg = userIdCardFontImg;
    }

    public String getUserIdCardBackImg() {
        return userIdCardBackImg;
    }

    public void setUserIdCardBackImg(String userIdCardBackImg) {
        this.userIdCardBackImg = userIdCardBackImg;
    }

    public String getUserHeadImg() {
        return userHeadImg;
    }

    public void setUserHeadImg(String userHeadImg) {
        this.userHeadImg = userHeadImg;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserRegisterFrom() {
        return userRegisterFrom;
    }

    public void setUserRegisterFrom(String userRegisterFrom) {
        this.userRegisterFrom = userRegisterFrom;
    }

    public String getUserPayPassword() {
        return userPayPassword;
    }

    public void setUserPayPassword(String userPayPassword) {
        this.userPayPassword = userPayPassword;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public String getUserStatusExplain() {
        return userStatusExplain;
    }

    public void setUserStatusExplain(String userStatusExplain) {
        this.userStatusExplain = userStatusExplain;
    }

    public Date getUserCreateTime() {
        return userCreateTime;
    }

    public void setUserCreateTime(Date userCreateTime) {
        this.userCreateTime = userCreateTime;
    }

    public String getUserCreateUserId() {
        return userCreateUserId;
    }

    public void setUserCreateUserId(String userCreateUserId) {
        this.userCreateUserId = userCreateUserId;
    }

    public Date getUserUpdateTime() {
        return userUpdateTime;
    }

    public void setUserUpdateTime(Date userUpdateTime) {
        this.userUpdateTime = userUpdateTime;
    }

    public String getUserUpdateUserId() {
        return userUpdateUserId;
    }

    public void setUserUpdateUserId(String userUpdateUserId) {
        this.userUpdateUserId = userUpdateUserId;
    }

    public Boolean getUserIsDelete() {
        return userIsDelete;
    }

    public void setUserIsDelete(Boolean userIsDelete) {
        this.userIsDelete = userIsDelete;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyShortName() {
        return companyShortName;
    }

    public void setCompanyShortName(String companyShortName) {
        this.companyShortName = companyShortName;
    }

    public String getCompanyProvince() {
        return companyProvince;
    }

    public void setCompanyProvince(String companyProvince) {
        this.companyProvince = companyProvince;
    }

    public String getCompanyCity() {
        return companyCity;
    }

    public void setCompanyCity(String companyCity) {
        this.companyCity = companyCity;
    }

    public String getCompanyDistrict() {
        return companyDistrict;
    }

    public void setCompanyDistrict(String companyDistrict) {
        this.companyDistrict = companyDistrict;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyContact() {
        return companyContact;
    }

    public void setCompanyContact(String companyContact) {
        this.companyContact = companyContact;
    }

    public String getCompanyContactTel() {
        return companyContactTel;
    }

    public void setCompanyContactTel(String companyContactTel) {
        this.companyContactTel = companyContactTel;
    }

    public String getCompanyContactIdCard() {
        return companyContactIdCard;
    }

    public void setCompanyContactIdCard(String companyContactIdCard) {
        this.companyContactIdCard = companyContactIdCard;
    }

    public String getCompanyRegistedCapital() {
        return companyRegistedCapital;
    }

    public void setCompanyRegistedCapital(String companyRegistedCapital) {
        this.companyRegistedCapital = companyRegistedCapital;
    }

    public Boolean getCompanyIsThreeInOne() {
        return companyIsThreeInOne;
    }

    public void setCompanyIsThreeInOne(Boolean companyIsThreeInOne) {
        this.companyIsThreeInOne = companyIsThreeInOne;
    }

    public String getCompanyUnifiedSocialCreditLdentifier() {
        return companyUnifiedSocialCreditLdentifier;
    }

    public void setCompanyUnifiedSocialCreditLdentifier(String companyUnifiedSocialCreditLdentifier) {
        this.companyUnifiedSocialCreditLdentifier = companyUnifiedSocialCreditLdentifier;
    }

    public String getCompanyUnifiedSocialImg() {
        return companyUnifiedSocialImg;
    }

    public void setCompanyUnifiedSocialImg(String companyUnifiedSocialImg) {
        this.companyUnifiedSocialImg = companyUnifiedSocialImg;
    }

    public String getCompanyUnifiedSocialDatetime() {
        return companyUnifiedSocialDatetime;
    }

    public void setCompanyUnifiedSocialDatetime(String companyUnifiedSocialDatetime) {
        this.companyUnifiedSocialDatetime = companyUnifiedSocialDatetime;
    }

    public String getCompanyUnifiedSocialLimitDatetime() {
        return companyUnifiedSocialLimitDatetime;
    }

    public void setCompanyUnifiedSocialLimitDatetime(String companyUnifiedSocialLimitDatetime) {
        this.companyUnifiedSocialLimitDatetime = companyUnifiedSocialLimitDatetime;
    }

    public String getCompanyLicenseNo() {
        return companyLicenseNo;
    }

    public void setCompanyLicenseNo(String companyLicenseNo) {
        this.companyLicenseNo = companyLicenseNo;
    }

    public String getCompanyTaxNo() {
        return companyTaxNo;
    }

    public void setCompanyTaxNo(String companyTaxNo) {
        this.companyTaxNo = companyTaxNo;
    }

    public String getCompanyOrganizationCode() {
        return companyOrganizationCode;
    }

    public void setCompanyOrganizationCode(String companyOrganizationCode) {
        this.companyOrganizationCode = companyOrganizationCode;
    }

    public String getCompanyPrincipalName() {
        return companyPrincipalName;
    }

    public void setCompanyPrincipalName(String companyPrincipalName) {
        this.companyPrincipalName = companyPrincipalName;
    }

    public String getCompanyPrincipalTel() {
        return companyPrincipalTel;
    }

    public void setCompanyPrincipalTel(String companyPrincipalTel) {
        this.companyPrincipalTel = companyPrincipalTel;
    }

    public String getCompanyPrincipalIdCode() {
        return companyPrincipalIdCode;
    }

    public void setCompanyPrincipalIdCode(String companyPrincipalIdCode) {
        this.companyPrincipalIdCode = companyPrincipalIdCode;
    }

    public String getCompanyToPublicBank() {
        return companyToPublicBank;
    }

    public void setCompanyToPublicBank(String companyToPublicBank) {
        this.companyToPublicBank = companyToPublicBank;
    }

    public String getCompanyToPublicAccountBank() {
        return companyToPublicAccountBank;
    }

    public void setCompanyToPublicAccountBank(String companyToPublicAccountBank) {
        this.companyToPublicAccountBank = companyToPublicAccountBank;
    }

    public String getCompanyTaxpayerRegistrationNumber() {
        return companyTaxpayerRegistrationNumber;
    }

    public void setCompanyTaxpayerRegistrationNumber(String companyTaxpayerRegistrationNumber) {
        this.companyTaxpayerRegistrationNumber = companyTaxpayerRegistrationNumber;
    }

    public String getCompanyTel() {
        return companyTel;
    }

    public void setCompanyTel(String companyTel) {
        this.companyTel = companyTel;
    }

    public String getCompanyFax() {
        return companyFax;
    }

    public void setCompanyFax(String companyFax) {
        this.companyFax = companyFax;
    }

    public String getCompanyEmail() {
        return companyEmail;
    }

    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail;
    }

    public String getCompanyAccountsPermits() {
        return companyAccountsPermits;
    }

    public void setCompanyAccountsPermits(String companyAccountsPermits) {
        this.companyAccountsPermits = companyAccountsPermits;
    }

    public Integer getCompanyStatus() {
        return companyStatus;
    }

    public void setCompanyStatus(Integer companyStatus) {
        this.companyStatus = companyStatus;
    }

    public Date getCompanyCreateTime() {
        return companyCreateTime;
    }

    public void setCompanyCreateTime(Date companyCreateTime) {
        this.companyCreateTime = companyCreateTime;
    }

    public String getCompanyCreateUserId() {
        return companyCreateUserId;
    }

    public void setCompanyCreateUserId(String companyCreateUserId) {
        this.companyCreateUserId = companyCreateUserId;
    }

    public Date getCompanyUpdateTime() {
        return companyUpdateTime;
    }

    public void setCompanyUpdateTime(Date companyUpdateTime) {
        this.companyUpdateTime = companyUpdateTime;
    }

    public String getCompanyUpdateUserId() {
        return companyUpdateUserId;
    }

    public void setCompanyUpdateUserId(String companyUpdateUserId) {
        this.companyUpdateUserId = companyUpdateUserId;
    }

    public Boolean getCompanyIsDelete() {
        return companyIsDelete;
    }

    public void setCompanyIsDelete(Boolean companyIsDelete) {
        this.companyIsDelete = companyIsDelete;
    }
}
