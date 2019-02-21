package com.dllg.pabank.model.response;

/**
 * 查询会员子账号
 */
public class QueryCustAcctIdResponse extends PABankResponse {
    /**
     * 子账户账号
     */
    private String SubAcctNo;
    /**
     * 子账户可提现余额
     */
    private String SubAcctCashBal;
    /**
     * 子账户可用余额
     */
    private String SubAcctAvailBal;
    /**
     * 子账户冻结金额
     * 指在担保子账户里冻结的金额
     */
    private String SubAcctFreezeAmt;
    /**
     * 保留域
     * 若平台开通智能收款，保留域返回智能收款子帐号
     */
    private String ReservedMsg;

    public String getSubAcctNo() {
        return SubAcctNo;
    }

    public void setSubAcctNo(String subAcctNo) {
        SubAcctNo = subAcctNo;
    }

    public String getSubAcctCashBal() {
        return SubAcctCashBal;
    }

    public void setSubAcctCashBal(String subAcctCashBal) {
        SubAcctCashBal = subAcctCashBal;
    }

    public String getSubAcctAvailBal() {
        return SubAcctAvailBal;
    }

    public void setSubAcctAvailBal(String subAcctAvailBal) {
        SubAcctAvailBal = subAcctAvailBal;
    }

    public String getSubAcctFreezeAmt() {
        return SubAcctFreezeAmt;
    }

    public void setSubAcctFreezeAmt(String subAcctFreezeAmt) {
        SubAcctFreezeAmt = subAcctFreezeAmt;
    }

    public String getReservedMsg() {
        return ReservedMsg;
    }

    public void setReservedMsg(String reservedMsg) {
        ReservedMsg = reservedMsg;
    }
}
