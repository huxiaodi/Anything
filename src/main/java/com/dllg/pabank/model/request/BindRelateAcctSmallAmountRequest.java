package com.dllg.pabank.model.request;

/**
 * 会员绑定提现账户-小额鉴权
 */
public class BindRelateAcctSmallAmountRequest extends PABankRequest {
    /**
     * 资金汇总账号
     */
    private String FundSummaryAcctNo;
    /**
     * 子账户账号
     */
    private String SubAcctNo;
    /**
     * 交易网会员代码
     */
    private String TranNetMemberCode;
    /**
     * 会员名称
     */
    private String MemberName;
    /**
     * 会员证件类型
     */
    private String MemberGlobalType;
    /**
     * 会员证件号码
     */
    private String MemberGlobalId;
    /**
     * 会员账号
     * 提现的银行卡
     */
    private String MemberAcctNo;
    /**
     * 银行类型
     * 1:本行
     * 2:他行
     */
    private String BankType;
    /**
     * 开户行名称
     * 若大小额行号不填则送超级网银行号对应的银行名称
     * 若填大小额行号则送大小额行号对应的银行名称
     */
    private String AcctOpenBranchName;
    /**
     * 大小额行号
     * 可选送
     */
    private String CnapsBranchId;
    /**
     * 超级网银行号
     */
    private String EiconBankBranchId;
    /**
     * 手机号码
     */
    private String Mobile;
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

    public String getTranNetMemberCode() {
        return TranNetMemberCode;
    }

    public void setTranNetMemberCode(String tranNetMemberCode) {
        TranNetMemberCode = tranNetMemberCode;
    }

    public String getMemberName() {
        return MemberName;
    }

    public void setMemberName(String memberName) {
        MemberName = memberName;
    }

    public String getMemberGlobalType() {
        return MemberGlobalType;
    }

    public void setMemberGlobalType(String memberGlobalType) {
        MemberGlobalType = memberGlobalType;
    }

    public String getMemberGlobalId() {
        return MemberGlobalId;
    }

    public void setMemberGlobalId(String memberGlobalId) {
        MemberGlobalId = memberGlobalId;
    }

    public String getMemberAcctNo() {
        return MemberAcctNo;
    }

    public void setMemberAcctNo(String memberAcctNo) {
        MemberAcctNo = memberAcctNo;
    }

    public String getBankType() {
        return BankType;
    }

    public void setBankType(String bankType) {
        BankType = bankType;
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

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getReservedMsg() {
        return ReservedMsg;
    }

    public void setReservedMsg(String reservedMsg) {
        ReservedMsg = reservedMsg;
    }
}
