package com.dllg.pabank.model.request;

/**
 * 验证鉴权金额
 */
public class CheckAmountRequest extends PABankRequest {
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
     * 提现账号
     */
    private String TakeCashAcctNo;
    /**
     * 鉴权金额
     */
    private String AuthAmt;
    /**
     * 币种
     * 默认为RMB
     */
    private String Ccy;
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

    public String getTakeCashAcctNo() {
        return TakeCashAcctNo;
    }

    public void setTakeCashAcctNo(String takeCashAcctNo) {
        TakeCashAcctNo = takeCashAcctNo;
    }

    public String getAuthAmt() {
        return AuthAmt;
    }

    public void setAuthAmt(String authAmt) {
        AuthAmt = authAmt;
    }

    public String getCcy() {
        return Ccy;
    }

    public void setCcy(String ccy) {
        Ccy = ccy;
    }

    public String getReservedMsg() {
        return ReservedMsg;
    }

    public void setReservedMsg(String reservedMsg) {
        ReservedMsg = reservedMsg;
    }
}
