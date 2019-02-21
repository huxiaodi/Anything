package com.dllg.pabank.model.response;

/**
 * 会员子账户开立
 */
public class OpenCustAcctIdResponse extends PABankResponse {
    /**
     * 子账户账号
     */
    private String SubAcctNo;
    /**
     * 保留域
     */
    private String ReservedMsg;

    public String getSubAcctNo() {
        return SubAcctNo;
    }

    public void setSubAcctNo(String subAcctNo) {
        SubAcctNo = subAcctNo;
    }

    public String getReservedMsg() {
        return ReservedMsg;
    }

    public void setReservedMsg(String reservedMsg) {
        ReservedMsg = reservedMsg;
    }
}
