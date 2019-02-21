package com.dllg.pabank.model.request;

/**
 * 会员解绑提现账户
 */
public class UnbindRelateAcctRequest extends PABankRequest {
    /**
     * 功能标志
     * 1:解绑
     */
    private String FunctionFlag;
    /**
     * 资金汇总账号
     */
    private String FundSummaryAcctNo;
    /**
     * 交易网会员代码
     */
    private String TranNetMemberCode;
    /**
     * 子账户账号
     */
    private String SubAcctNo;
    /**
     * 会员账号
     * 提现账号
     */
    private String MemberAcctNo;
    /**
     * 保留域
     */
    private String ReservedMsg;

    public String getFunctionFlag() {
        return FunctionFlag;
    }

    public void setFunctionFlag(String functionFlag) {
        FunctionFlag = functionFlag;
    }

    public String getFundSummaryAcctNo() {
        return FundSummaryAcctNo;
    }

    public void setFundSummaryAcctNo(String fundSummaryAcctNo) {
        FundSummaryAcctNo = fundSummaryAcctNo;
    }

    public String getTranNetMemberCode() {
        return TranNetMemberCode;
    }

    public void setTranNetMemberCode(String tranNetMemberCode) {
        TranNetMemberCode = tranNetMemberCode;
    }

    public String getSubAcctNo() {
        return SubAcctNo;
    }

    public void setSubAcctNo(String subAcctNo) {
        SubAcctNo = subAcctNo;
    }

    public String getMemberAcctNo() {
        return MemberAcctNo;
    }

    public void setMemberAcctNo(String memberAcctNo) {
        MemberAcctNo = memberAcctNo;
    }

    public String getReservedMsg() {
        return ReservedMsg;
    }

    public void setReservedMsg(String reservedMsg) {
        ReservedMsg = reservedMsg;
    }
}
