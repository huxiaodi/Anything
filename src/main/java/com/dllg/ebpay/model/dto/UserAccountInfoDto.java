package com.dllg.ebpay.model.dto;

public class UserAccountInfoDto {

    // 资金汇总账号
    private String FundSummaryAcctNo;
    // 子账号
    private String SubAcctNo;
    // 子账号Id
    private String subAcctId;
    // 总账户类型，1-平安银行
    private Integer accountType;
    // 账户拥有者名称
    private String userAccountName;
    // 账户拥有者Id
    private String userId;

    public String getSubAcctId() {
        return subAcctId;
    }

    public void setSubAcctId(String subAcctId) {
        this.subAcctId = subAcctId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserAccountName() {
        return userAccountName;
    }

    public void setUserAccountName(String userAccountName) {
        this.userAccountName = userAccountName;
    }

    public String getFundSummaryAcctNo() {
        return FundSummaryAcctNo;
    }

    public void setFundSummaryAcctNo(String fundSummaryAcctNo) {
        FundSummaryAcctNo = fundSummaryAcctNo;
    }

    public String getSubAcctNo() {
        return SubAcctNo;
    }

    public void setSubAcctNo(String subAcctNo) {
        SubAcctNo = subAcctNo;
    }

    public Integer getAccountType() {
        return accountType;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }
}
