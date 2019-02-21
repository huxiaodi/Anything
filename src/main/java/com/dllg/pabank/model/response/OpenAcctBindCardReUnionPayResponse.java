package com.dllg.pabank.model.response;

/**
 * 开户并绑卡-回填短信验证码
 */
public class OpenAcctBindCardReUnionPayResponse extends PABankResponse {
    /**
     * 子账户账号
     */
    private String SubAcctNo;
    /**
     * 交易网会员代码
     */
    private String TranNetMemberCode;
    /**
     * 保留域
     * 若需短信认证且平台开通了智能收款，则该栏返回智能收款子账号
     */
    private String ReservedMsg;

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

    public String getReservedMsg() {
        return ReservedMsg;
    }

    public void setReservedMsg(String reservedMsg) {
        ReservedMsg = reservedMsg;
    }
}
