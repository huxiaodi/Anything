package com.dllg.pabank.model.request;

/**
 * 维护会员绑定提现账户联行号
 */
public class MntMbrBindRelateAcctBankCodeRequest extends PABankRequest {
    /**
     * 资金汇总账号
     */
    private String FundSummaryAcctNo;
    /**
     * 子账户账号
     */
    private String SubAcctNo;
    /**
     * 会员绑定账号
     */
    private String MemberBindAcctNo;
    /**
     * 开户行名称
     * 若大小额行号不填则送超级网银号对应的银行名称，若填大小额行号则送大小额行号对应的银行名称
     */
    private String AcctOpenBranchName;
    /**
     * 大小额行号
     * BankCode和SBankCode两者二选一必填。
     */
    private String CnapsBranchId;
    /**
     * 超级网银行号
     */
    private String EiconBankBranchId;
    /**
     * 保留域
     */
    private String ReservedMsg;

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

    public String getMemberBindAcctNo() {
        return MemberBindAcctNo;
    }

    public void setMemberBindAcctNo(String memberBindAcctNo) {
        MemberBindAcctNo = memberBindAcctNo;
    }

    public String getAcctOpenBranchName() {
        return AcctOpenBranchName;
    }

    public void setAcctOpenBranchName(String acctOpenBranchName) {
        AcctOpenBranchName = acctOpenBranchName;
    }

    public String getCnapsBranchId() {
        return CnapsBranchId;
    }

    public void setCnapsBranchId(String cnapsBranchId) {
        CnapsBranchId = cnapsBranchId;
    }

    public String getEiconBankBranchId() {
        return EiconBankBranchId;
    }

    public void setEiconBankBranchId(String eiconBankBranchId) {
        EiconBankBranchId = eiconBankBranchId;
    }

    public String getReservedMsg() {
        return ReservedMsg;
    }

    public void setReservedMsg(String reservedMsg) {
        ReservedMsg = reservedMsg;
    }
}
