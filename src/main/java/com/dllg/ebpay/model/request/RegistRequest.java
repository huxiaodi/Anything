package com.dllg.ebpay.model.request;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;


public class RegistRequest {

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
     * 是否三证合一
     */
    private Boolean companyIsThreeInOne;
    /**
     * 统一信用代码
     */
    private String companyUnifiedSocialCreditLdentifier;
    /**
     * 营业执照号码
     */
    private String companyLicenseNo;
    /**
     * 组织机构代码
     */
    private String companyOrganizationCode;
    /**
     * 税务登记号
     */
    private String companyTaxNo;
    /**
     * 身份证号
     */
    private String userIdCard;

    public String getCompanyUnifiedSocialCreditLdentifier() {
        return companyUnifiedSocialCreditLdentifier;
    }

    public void setCompanyUnifiedSocialCreditLdentifier(String companyUnifiedSocialCreditLdentifier) {
        this.companyUnifiedSocialCreditLdentifier = companyUnifiedSocialCreditLdentifier;
    }

    public String getCompanyLicenseNo() {
        return companyLicenseNo;
    }

    public void setCompanyLicenseNo(String companyLicenseNo) {
        this.companyLicenseNo = companyLicenseNo;
    }

    public String getCompanyOrganizationCode() {
        return companyOrganizationCode;
    }

    public void setCompanyOrganizationCode(String companyOrganizationCode) {
        this.companyOrganizationCode = companyOrganizationCode;
    }

    public String getCompanyTaxNo() {
        return companyTaxNo;
    }

    public void setCompanyTaxNo(String companyTaxNo) {
        this.companyTaxNo = companyTaxNo;
    }

    public String getUserIdCard() {
        return userIdCard;
    }

    public void setUserIdCard(String userIdCard) {
        this.userIdCard = userIdCard;
    }

    public Boolean getCompanyIsThreeInOne() {
        return companyIsThreeInOne;
    }

    public void setCompanyIsThreeInOne(Boolean companyIsThreeInOne) {
        this.companyIsThreeInOne = companyIsThreeInOne;
    }

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
}
