package com.dllg.pabank.model.request;

/**
 * 会员绑定提现账户-回填银联鉴权短信码
 */
public class BindRelateAccReUnionPayRequest extends PABankRequest {
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
     */
    private String MemberAcctNo;
    /**
     * 短信验证码
     */
    private String MessageCheckCode;
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

    public String getMessageCheckCode() {
        return MessageCheckCode;
    }

    public void setMessageCheckCode(String messageCheckCode) {
        MessageCheckCode = messageCheckCode;
    }

    public String getReservedMsg() {
        return ReservedMsg;
    }

    public void setReservedMsg(String reservedMsg) {
        ReservedMsg = reservedMsg;
    }
}
