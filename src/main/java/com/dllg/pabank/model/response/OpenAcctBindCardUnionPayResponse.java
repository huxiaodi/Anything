package com.dllg.pabank.model.response;

/**
 * 开户并绑卡-银联鉴权
 */
public class OpenAcctBindCardUnionPayResponse extends PABankResponse {
    /**
     * 子账户账号
     * 若需短信认证，则该栏位返回空。 实际子帐号需要调用6097接口短信认证通过后返回开立的子帐号
     */
    private String SubAcctNo;
    /**
     * 交易网会员代码
     */
    private String TranNetMemberCode;
    /**
     * 保留域
     * 若无需短信认证且平台开通了智能收款，则该栏返回智能收款子账号
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
