package com.dllg.ebpay.model.dto;

import com.dllg.ebpay.model.generator.AccountSub;

import java.math.BigDecimal;
import java.util.Date;

/**
 *   子账户
 *   account_sub
 * @mbg.generated do_not_delete_during_merge
 */
public class AccountSubDto extends AccountSub{
    private String accountType;
    private String createUser;
    private String userType;
    private String userCnName;
    private String userCertificateNumber;
    private String userMobile;
    private String companyName;
    private String companyLicenseNo;
    private String companyContactTel;
    private boolean companyIsThreeInOne;
    private String companyUnifiedSocialCreditLdentifier;

    public boolean isCompanyIsThreeInOne() { return companyIsThreeInOne; }

    public void setCompanyIsThreeInOne(boolean companyIsThreeInOne) { this.companyIsThreeInOne = companyIsThreeInOne; }

    public String getCompanyUnifiedSocialCreditLdentifier() { return companyUnifiedSocialCreditLdentifier; }

    public void setCompanyUnifiedSocialCreditLdentifier(String companyUnifiedSocialCreditLdentifier) { this.companyUnifiedSocialCreditLdentifier = companyUnifiedSocialCreditLdentifier; }

    public String getUserMobile() { return userMobile; }

    public void setUserMobile(String userMobile) { this.userMobile = userMobile; }

    public String getUserType() { return userType; }

    public void setUserType(String userType) { this.userType = userType; }

    public String getUserCnName() { return userCnName; }

    public void setUserCnName(String userCnName) { this.userCnName = userCnName; }

    public String getUserCertificateNumber() { return userCertificateNumber; }

    public void setUserCertificateNumber(String userCertificateNumber) { this.userCertificateNumber = userCertificateNumber; }

    public String getCompanyName() { return companyName; }

    public void setCompanyName(String companyName) { this.companyName = companyName; }

    public String getCompanyLicenseNo() { return companyLicenseNo; }

    public void setCompanyLicenseNo(String companyLicenseNo) { this.companyLicenseNo = companyLicenseNo; }

    public String getCompanyContactTel() { return companyContactTel; }

    public void setCompanyContactTel(String companyContactTel) { this.companyContactTel = companyContactTel; }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
}