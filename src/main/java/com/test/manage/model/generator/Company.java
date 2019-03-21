package com.test.manage.model.generator;

import java.util.Date;

/**
 *   会员表
 *   company
 * @mbg.generated do_not_delete_during_merge
 */
public class Company {
    /**
     *   主键
     *   company.company_id
     * @mbg.generated
     */
    private String companyId;

    /**
     *   企业名称
     *   company.company_name
     * @mbg.generated
     */
    private String companyName;

    /**
     *   企业的注册登录用户名称
     *   company.company_user_name
     * @mbg.generated
     */
    private String companyUserName;

    /**
     *   公司简称
     *   company.company_short_name
     * @mbg.generated
     */
    private String companyShortName;

    /**
     *   省
     *   company.company_province
     * @mbg.generated
     */
    private String companyProvince;

    /**
     *   city
     *   company.company_city
     * @mbg.generated
     */
    private String companyCity;

    /**
     *   县区
     *   company.company_district
     * @mbg.generated
     */
    private String companyDistrict;

    /**
     *   公司详细地址
     *   company.company_address
     * @mbg.generated
     */
    private String companyAddress;

    /**
     *   联系人
     *   company.company_contact
     * @mbg.generated
     */
    private String companyContact;

    /**
     *   联系人电话
     *   company.company_contact_tel
     * @mbg.generated
     */
    private String companyContactTel;

    /**
     *   联系人身份证号码
     *   company.company_contact_id_card
     * @mbg.generated
     */
    private String companyContactIdCard;

    /**
     *   注册资金（单位万元）
     *   company.company_registed_capital
     * @mbg.generated
     */
    private String companyRegistedCapital;

    /**
     *   是否三证合一,0否,1是
     *   company.company_is_three_in_one
     * @mbg.generated
     */
    private Boolean companyIsThreeInOne;

    /**
     *   统一社会信用代码
     *   company.company_unified_social_credit_ldentifier
     * @mbg.generated
     */
    private String companyUnifiedSocialCreditLdentifier;

    /**
     *   统一社会信用照片
     *   company.company_unified_social_img
     * @mbg.generated
     */
    private String companyUnifiedSocialImg;

    /**
     *   统一社会信用代码注册时间
     *   company.company_unified_social_datetime
     * @mbg.generated
     */
    private Date companyUnifiedSocialDatetime;

    /**
     *   统一社会信用代码证有效期至
     *   company.company_unified_social_limit_datetime
     * @mbg.generated
     */
    private Date companyUnifiedSocialLimitDatetime;

    /**
     *   营业执照号码
     *   company.company_license_no
     * @mbg.generated
     */
    private String companyLicenseNo;

    /**
     *   营业执照照片
     *   company.company_license_no_img
     * @mbg.generated
     */
    private String companyLicenseNoImg;

    /**
     *   税务登记证号码
     *   company.company_tax_no
     * @mbg.generated
     */
    private String companyTaxNo;

    /**
     *   税务登记证照片
     *   company.company_tax_no_img
     * @mbg.generated
     */
    private String companyTaxNoImg;

    /**
     *   组织结构代码号码
     *   company.company_organization_code
     * @mbg.generated
     */
    private String companyOrganizationCode;

    /**
     *   组织结构代码照片
     *   company.company_organization_code_img
     * @mbg.generated
     */
    private String companyOrganizationCodeImg;

    /**
     *   企业法人名称
     *   company.company_principal_name
     * @mbg.generated
     */
    private String companyPrincipalName;

    /**
     *   企业法人手机号码
     *   company.company_principal_tel
     * @mbg.generated
     */
    private String companyPrincipalTel;

    /**
     *   企业法人证件号码
     *   company.company_principal_id_code
     * @mbg.generated
     */
    private String companyPrincipalIdCode;

    /**
     *   对公账户开户行
     *   company.company_to_public_bank
     * @mbg.generated
     */
    private String companyToPublicBank;

    /**
     *   对公账户账号
     *   company.company_to_public_account_bank
     * @mbg.generated
     */
    private String companyToPublicAccountBank;

    /**
     *   纳税人识别号
     *   company.company_taxpayer_registration_number
     * @mbg.generated
     */
    private String companyTaxpayerRegistrationNumber;

    /**
     *   企业电话
     *   company.company_tel
     * @mbg.generated
     */
    private String companyTel;

    /**
     *   公司传真
     *   company.company_fax
     * @mbg.generated
     */
    private String companyFax;

    /**
     *   公司邮箱
     *   company.company_email
     * @mbg.generated
     */
    private String companyEmail;

    /**
     *   开户许可证
     *   company.company_accounts_permits
     * @mbg.generated
     */
    private String companyAccountsPermits;

    /**
     *   状态
     *   company.company_status
     * @mbg.generated
     */
    private Integer companyStatus;

    /**
     *   创建时间
     *   company.company_create_time
     * @mbg.generated
     */
    private Date companyCreateTime;

    /**
     *   创建人
     *   company.company_create_user_id
     * @mbg.generated
     */
    private String companyCreateUserId;

    /**
     *   修改时间
     *   company.company_update_time
     * @mbg.generated
     */
    private Date companyUpdateTime;

    /**
     *   修改人
     *   company.company_update_user_id
     * @mbg.generated
     */
    private String companyUpdateUserId;

    /**
     *   是否删除 0-否,1-是
     *   company.company_is_delete
     * @mbg.generated
     */
    private Boolean companyIsDelete;

    /**
     * 主键
     * @return the value of company.company_id
     * @mbg.generated
     */
    public String getCompanyId() {
        return companyId;
    }

    /**
     * 主键
     * @param companyId the value for company.company_id
     * @mbg.generated
     */
    public void setCompanyId(String companyId) {
        this.companyId = companyId == null ? null : companyId.trim();
    }

    /**
     * 企业名称
     * @return the value of company.company_name
     * @mbg.generated
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * 企业名称
     * @param companyName the value for company.company_name
     * @mbg.generated
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    /**
     * 企业的注册登录用户名称
     * @return the value of company.company_user_name
     * @mbg.generated
     */
    public String getCompanyUserName() {
        return companyUserName;
    }

    /**
     * 企业的注册登录用户名称
     * @param companyUserName the value for company.company_user_name
     * @mbg.generated
     */
    public void setCompanyUserName(String companyUserName) {
        this.companyUserName = companyUserName == null ? null : companyUserName.trim();
    }

    /**
     * 公司简称
     * @return the value of company.company_short_name
     * @mbg.generated
     */
    public String getCompanyShortName() {
        return companyShortName;
    }

    /**
     * 公司简称
     * @param companyShortName the value for company.company_short_name
     * @mbg.generated
     */
    public void setCompanyShortName(String companyShortName) {
        this.companyShortName = companyShortName == null ? null : companyShortName.trim();
    }

    /**
     * 省
     * @return the value of company.company_province
     * @mbg.generated
     */
    public String getCompanyProvince() {
        return companyProvince;
    }

    /**
     * 省
     * @param companyProvince the value for company.company_province
     * @mbg.generated
     */
    public void setCompanyProvince(String companyProvince) {
        this.companyProvince = companyProvince == null ? null : companyProvince.trim();
    }

    /**
     * city
     * @return the value of company.company_city
     * @mbg.generated
     */
    public String getCompanyCity() {
        return companyCity;
    }

    /**
     * city
     * @param companyCity the value for company.company_city
     * @mbg.generated
     */
    public void setCompanyCity(String companyCity) {
        this.companyCity = companyCity == null ? null : companyCity.trim();
    }

    /**
     * 县区
     * @return the value of company.company_district
     * @mbg.generated
     */
    public String getCompanyDistrict() {
        return companyDistrict;
    }

    /**
     * 县区
     * @param companyDistrict the value for company.company_district
     * @mbg.generated
     */
    public void setCompanyDistrict(String companyDistrict) {
        this.companyDistrict = companyDistrict == null ? null : companyDistrict.trim();
    }

    /**
     * 公司详细地址
     * @return the value of company.company_address
     * @mbg.generated
     */
    public String getCompanyAddress() {
        return companyAddress;
    }

    /**
     * 公司详细地址
     * @param companyAddress the value for company.company_address
     * @mbg.generated
     */
    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress == null ? null : companyAddress.trim();
    }

    /**
     * 联系人
     * @return the value of company.company_contact
     * @mbg.generated
     */
    public String getCompanyContact() {
        return companyContact;
    }

    /**
     * 联系人
     * @param companyContact the value for company.company_contact
     * @mbg.generated
     */
    public void setCompanyContact(String companyContact) {
        this.companyContact = companyContact == null ? null : companyContact.trim();
    }

    /**
     * 联系人电话
     * @return the value of company.company_contact_tel
     * @mbg.generated
     */
    public String getCompanyContactTel() {
        return companyContactTel;
    }

    /**
     * 联系人电话
     * @param companyContactTel the value for company.company_contact_tel
     * @mbg.generated
     */
    public void setCompanyContactTel(String companyContactTel) {
        this.companyContactTel = companyContactTel == null ? null : companyContactTel.trim();
    }

    /**
     * 联系人身份证号码
     * @return the value of company.company_contact_id_card
     * @mbg.generated
     */
    public String getCompanyContactIdCard() {
        return companyContactIdCard;
    }

    /**
     * 联系人身份证号码
     * @param companyContactIdCard the value for company.company_contact_id_card
     * @mbg.generated
     */
    public void setCompanyContactIdCard(String companyContactIdCard) {
        this.companyContactIdCard = companyContactIdCard == null ? null : companyContactIdCard.trim();
    }

    /**
     * 注册资金（单位万元）
     * @return the value of company.company_registed_capital
     * @mbg.generated
     */
    public String getCompanyRegistedCapital() {
        return companyRegistedCapital;
    }

    /**
     * 注册资金（单位万元）
     * @param companyRegistedCapital the value for company.company_registed_capital
     * @mbg.generated
     */
    public void setCompanyRegistedCapital(String companyRegistedCapital) {
        this.companyRegistedCapital = companyRegistedCapital == null ? null : companyRegistedCapital.trim();
    }

    /**
     * 是否三证合一,0否,1是
     * @return the value of company.company_is_three_in_one
     * @mbg.generated
     */
    public Boolean getCompanyIsThreeInOne() {
        return companyIsThreeInOne;
    }

    /**
     * 是否三证合一,0否,1是
     * @param companyIsThreeInOne the value for company.company_is_three_in_one
     * @mbg.generated
     */
    public void setCompanyIsThreeInOne(Boolean companyIsThreeInOne) {
        this.companyIsThreeInOne = companyIsThreeInOne;
    }

    /**
     * 统一社会信用代码
     * @return the value of company.company_unified_social_credit_ldentifier
     * @mbg.generated
     */
    public String getCompanyUnifiedSocialCreditLdentifier() {
        return companyUnifiedSocialCreditLdentifier;
    }

    /**
     * 统一社会信用代码
     * @param companyUnifiedSocialCreditLdentifier the value for company.company_unified_social_credit_ldentifier
     * @mbg.generated
     */
    public void setCompanyUnifiedSocialCreditLdentifier(String companyUnifiedSocialCreditLdentifier) {
        this.companyUnifiedSocialCreditLdentifier = companyUnifiedSocialCreditLdentifier == null ? null : companyUnifiedSocialCreditLdentifier.trim();
    }

    /**
     * 统一社会信用照片
     * @return the value of company.company_unified_social_img
     * @mbg.generated
     */
    public String getCompanyUnifiedSocialImg() {
        return companyUnifiedSocialImg;
    }

    /**
     * 统一社会信用照片
     * @param companyUnifiedSocialImg the value for company.company_unified_social_img
     * @mbg.generated
     */
    public void setCompanyUnifiedSocialImg(String companyUnifiedSocialImg) {
        this.companyUnifiedSocialImg = companyUnifiedSocialImg == null ? null : companyUnifiedSocialImg.trim();
    }

    /**
     * 统一社会信用代码注册时间
     * @return the value of company.company_unified_social_datetime
     * @mbg.generated
     */
    public Date getCompanyUnifiedSocialDatetime() {
        return companyUnifiedSocialDatetime;
    }

    /**
     * 统一社会信用代码注册时间
     * @param companyUnifiedSocialDatetime the value for company.company_unified_social_datetime
     * @mbg.generated
     */
    public void setCompanyUnifiedSocialDatetime(Date companyUnifiedSocialDatetime) {
        this.companyUnifiedSocialDatetime = companyUnifiedSocialDatetime;
    }

    /**
     * 统一社会信用代码证有效期至
     * @return the value of company.company_unified_social_limit_datetime
     * @mbg.generated
     */
    public Date getCompanyUnifiedSocialLimitDatetime() {
        return companyUnifiedSocialLimitDatetime;
    }

    /**
     * 统一社会信用代码证有效期至
     * @param companyUnifiedSocialLimitDatetime the value for company.company_unified_social_limit_datetime
     * @mbg.generated
     */
    public void setCompanyUnifiedSocialLimitDatetime(Date companyUnifiedSocialLimitDatetime) {
        this.companyUnifiedSocialLimitDatetime = companyUnifiedSocialLimitDatetime;
    }

    /**
     * 营业执照号码
     * @return the value of company.company_license_no
     * @mbg.generated
     */
    public String getCompanyLicenseNo() {
        return companyLicenseNo;
    }

    /**
     * 营业执照号码
     * @param companyLicenseNo the value for company.company_license_no
     * @mbg.generated
     */
    public void setCompanyLicenseNo(String companyLicenseNo) {
        this.companyLicenseNo = companyLicenseNo == null ? null : companyLicenseNo.trim();
    }

    /**
     * 营业执照照片
     * @return the value of company.company_license_no_img
     * @mbg.generated
     */
    public String getCompanyLicenseNoImg() {
        return companyLicenseNoImg;
    }

    /**
     * 营业执照照片
     * @param companyLicenseNoImg the value for company.company_license_no_img
     * @mbg.generated
     */
    public void setCompanyLicenseNoImg(String companyLicenseNoImg) {
        this.companyLicenseNoImg = companyLicenseNoImg == null ? null : companyLicenseNoImg.trim();
    }

    /**
     * 税务登记证号码
     * @return the value of company.company_tax_no
     * @mbg.generated
     */
    public String getCompanyTaxNo() {
        return companyTaxNo;
    }

    /**
     * 税务登记证号码
     * @param companyTaxNo the value for company.company_tax_no
     * @mbg.generated
     */
    public void setCompanyTaxNo(String companyTaxNo) {
        this.companyTaxNo = companyTaxNo == null ? null : companyTaxNo.trim();
    }

    /**
     * 税务登记证照片
     * @return the value of company.company_tax_no_img
     * @mbg.generated
     */
    public String getCompanyTaxNoImg() {
        return companyTaxNoImg;
    }

    /**
     * 税务登记证照片
     * @param companyTaxNoImg the value for company.company_tax_no_img
     * @mbg.generated
     */
    public void setCompanyTaxNoImg(String companyTaxNoImg) {
        this.companyTaxNoImg = companyTaxNoImg == null ? null : companyTaxNoImg.trim();
    }

    /**
     * 组织结构代码号码
     * @return the value of company.company_organization_code
     * @mbg.generated
     */
    public String getCompanyOrganizationCode() {
        return companyOrganizationCode;
    }

    /**
     * 组织结构代码号码
     * @param companyOrganizationCode the value for company.company_organization_code
     * @mbg.generated
     */
    public void setCompanyOrganizationCode(String companyOrganizationCode) {
        this.companyOrganizationCode = companyOrganizationCode == null ? null : companyOrganizationCode.trim();
    }

    /**
     * 组织结构代码照片
     * @return the value of company.company_organization_code_img
     * @mbg.generated
     */
    public String getCompanyOrganizationCodeImg() {
        return companyOrganizationCodeImg;
    }

    /**
     * 组织结构代码照片
     * @param companyOrganizationCodeImg the value for company.company_organization_code_img
     * @mbg.generated
     */
    public void setCompanyOrganizationCodeImg(String companyOrganizationCodeImg) {
        this.companyOrganizationCodeImg = companyOrganizationCodeImg == null ? null : companyOrganizationCodeImg.trim();
    }

    /**
     * 企业法人名称
     * @return the value of company.company_principal_name
     * @mbg.generated
     */
    public String getCompanyPrincipalName() {
        return companyPrincipalName;
    }

    /**
     * 企业法人名称
     * @param companyPrincipalName the value for company.company_principal_name
     * @mbg.generated
     */
    public void setCompanyPrincipalName(String companyPrincipalName) {
        this.companyPrincipalName = companyPrincipalName == null ? null : companyPrincipalName.trim();
    }

    /**
     * 企业法人手机号码
     * @return the value of company.company_principal_tel
     * @mbg.generated
     */
    public String getCompanyPrincipalTel() {
        return companyPrincipalTel;
    }

    /**
     * 企业法人手机号码
     * @param companyPrincipalTel the value for company.company_principal_tel
     * @mbg.generated
     */
    public void setCompanyPrincipalTel(String companyPrincipalTel) {
        this.companyPrincipalTel = companyPrincipalTel == null ? null : companyPrincipalTel.trim();
    }

    /**
     * 企业法人证件号码
     * @return the value of company.company_principal_id_code
     * @mbg.generated
     */
    public String getCompanyPrincipalIdCode() {
        return companyPrincipalIdCode;
    }

    /**
     * 企业法人证件号码
     * @param companyPrincipalIdCode the value for company.company_principal_id_code
     * @mbg.generated
     */
    public void setCompanyPrincipalIdCode(String companyPrincipalIdCode) {
        this.companyPrincipalIdCode = companyPrincipalIdCode == null ? null : companyPrincipalIdCode.trim();
    }

    /**
     * 对公账户开户行
     * @return the value of company.company_to_public_bank
     * @mbg.generated
     */
    public String getCompanyToPublicBank() {
        return companyToPublicBank;
    }

    /**
     * 对公账户开户行
     * @param companyToPublicBank the value for company.company_to_public_bank
     * @mbg.generated
     */
    public void setCompanyToPublicBank(String companyToPublicBank) {
        this.companyToPublicBank = companyToPublicBank == null ? null : companyToPublicBank.trim();
    }

    /**
     * 对公账户账号
     * @return the value of company.company_to_public_account_bank
     * @mbg.generated
     */
    public String getCompanyToPublicAccountBank() {
        return companyToPublicAccountBank;
    }

    /**
     * 对公账户账号
     * @param companyToPublicAccountBank the value for company.company_to_public_account_bank
     * @mbg.generated
     */
    public void setCompanyToPublicAccountBank(String companyToPublicAccountBank) {
        this.companyToPublicAccountBank = companyToPublicAccountBank == null ? null : companyToPublicAccountBank.trim();
    }

    /**
     * 纳税人识别号
     * @return the value of company.company_taxpayer_registration_number
     * @mbg.generated
     */
    public String getCompanyTaxpayerRegistrationNumber() {
        return companyTaxpayerRegistrationNumber;
    }

    /**
     * 纳税人识别号
     * @param companyTaxpayerRegistrationNumber the value for company.company_taxpayer_registration_number
     * @mbg.generated
     */
    public void setCompanyTaxpayerRegistrationNumber(String companyTaxpayerRegistrationNumber) {
        this.companyTaxpayerRegistrationNumber = companyTaxpayerRegistrationNumber == null ? null : companyTaxpayerRegistrationNumber.trim();
    }

    /**
     * 企业电话
     * @return the value of company.company_tel
     * @mbg.generated
     */
    public String getCompanyTel() {
        return companyTel;
    }

    /**
     * 企业电话
     * @param companyTel the value for company.company_tel
     * @mbg.generated
     */
    public void setCompanyTel(String companyTel) {
        this.companyTel = companyTel == null ? null : companyTel.trim();
    }

    /**
     * 公司传真
     * @return the value of company.company_fax
     * @mbg.generated
     */
    public String getCompanyFax() {
        return companyFax;
    }

    /**
     * 公司传真
     * @param companyFax the value for company.company_fax
     * @mbg.generated
     */
    public void setCompanyFax(String companyFax) {
        this.companyFax = companyFax == null ? null : companyFax.trim();
    }

    /**
     * 公司邮箱
     * @return the value of company.company_email
     * @mbg.generated
     */
    public String getCompanyEmail() {
        return companyEmail;
    }

    /**
     * 公司邮箱
     * @param companyEmail the value for company.company_email
     * @mbg.generated
     */
    public void setCompanyEmail(String companyEmail) {
        this.companyEmail = companyEmail == null ? null : companyEmail.trim();
    }

    /**
     * 开户许可证
     * @return the value of company.company_accounts_permits
     * @mbg.generated
     */
    public String getCompanyAccountsPermits() {
        return companyAccountsPermits;
    }

    /**
     * 开户许可证
     * @param companyAccountsPermits the value for company.company_accounts_permits
     * @mbg.generated
     */
    public void setCompanyAccountsPermits(String companyAccountsPermits) {
        this.companyAccountsPermits = companyAccountsPermits == null ? null : companyAccountsPermits.trim();
    }

    /**
     * 状态
     * @return the value of company.company_status
     * @mbg.generated
     */
    public Integer getCompanyStatus() {
        return companyStatus;
    }

    /**
     * 状态
     * @param companyStatus the value for company.company_status
     * @mbg.generated
     */
    public void setCompanyStatus(Integer companyStatus) {
        this.companyStatus = companyStatus;
    }

    /**
     * 创建时间
     * @return the value of company.company_create_time
     * @mbg.generated
     */
    public Date getCompanyCreateTime() {
        return companyCreateTime;
    }

    /**
     * 创建时间
     * @param companyCreateTime the value for company.company_create_time
     * @mbg.generated
     */
    public void setCompanyCreateTime(Date companyCreateTime) {
        this.companyCreateTime = companyCreateTime;
    }

    /**
     * 创建人
     * @return the value of company.company_create_user_id
     * @mbg.generated
     */
    public String getCompanyCreateUserId() {
        return companyCreateUserId;
    }

    /**
     * 创建人
     * @param companyCreateUserId the value for company.company_create_user_id
     * @mbg.generated
     */
    public void setCompanyCreateUserId(String companyCreateUserId) {
        this.companyCreateUserId = companyCreateUserId == null ? null : companyCreateUserId.trim();
    }

    /**
     * 修改时间
     * @return the value of company.company_update_time
     * @mbg.generated
     */
    public Date getCompanyUpdateTime() {
        return companyUpdateTime;
    }

    /**
     * 修改时间
     * @param companyUpdateTime the value for company.company_update_time
     * @mbg.generated
     */
    public void setCompanyUpdateTime(Date companyUpdateTime) {
        this.companyUpdateTime = companyUpdateTime;
    }

    /**
     * 修改人
     * @return the value of company.company_update_user_id
     * @mbg.generated
     */
    public String getCompanyUpdateUserId() {
        return companyUpdateUserId;
    }

    /**
     * 修改人
     * @param companyUpdateUserId the value for company.company_update_user_id
     * @mbg.generated
     */
    public void setCompanyUpdateUserId(String companyUpdateUserId) {
        this.companyUpdateUserId = companyUpdateUserId == null ? null : companyUpdateUserId.trim();
    }

    /**
     * 是否删除 0-否,1-是
     * @return the value of company.company_is_delete
     * @mbg.generated
     */
    public Boolean getCompanyIsDelete() {
        return companyIsDelete;
    }

    /**
     * 是否删除 0-否,1-是
     * @param companyIsDelete the value for company.company_is_delete
     * @mbg.generated
     */
    public void setCompanyIsDelete(Boolean companyIsDelete) {
        this.companyIsDelete = companyIsDelete;
    }
}