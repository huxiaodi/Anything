package com.dllg.pabank.model.response;

/**
 * 查询会员子账号余额
 */
public class QueryCustAcctIdBalanceResponse extends PABankResponse {
    /**
     * 交易网会员代码
     */
    private String TranNetMemberCode;
    /**
     * 子账户可用余额
     */
    private String SubAcctAvailBal;
    /**
     * 子账户担保金额
     * 指在担保子账户里待支付或冻结的金额
     */
    private String SubAcctAssureAmt;
    /**
     * 保留域
     */
    private String ReservedMsg;

    public String getTranNetMemberCode() {
        return TranNetMemberCode;
    }

    public void setTranNetMemberCode(String tranNetMemberCode) {
        TranNetMemberCode = tranNetMemberCode;
    }

    public String getSubAcctAvailBal() {
        return SubAcctAvailBal;
    }

    public void setSubAcctAvailBal(String subAcctAvailBal) {
        SubAcctAvailBal = subAcctAvailBal;
    }

    public String getSubAcctAssureAmt() {
        return SubAcctAssureAmt;
    }

    public void setSubAcctAssureAmt(String subAcctAssureAmt) {
        SubAcctAssureAmt = subAcctAssureAmt;
    }

    public String getReservedMsg() {
        return ReservedMsg;
    }

    public void setReservedMsg(String reservedMsg) {
        ReservedMsg = reservedMsg;
    }
}
