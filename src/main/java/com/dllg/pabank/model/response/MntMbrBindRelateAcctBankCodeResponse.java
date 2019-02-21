package com.dllg.pabank.model.response;

/**
 * 维护会员绑定提现账户联行号
 */
public class MntMbrBindRelateAcctBankCodeResponse extends PABankResponse {
    /**
     * 保留域
     */
    private String ReservedMsg;

    public String getReservedMsg() {
        return ReservedMsg;
    }

    public void setReservedMsg(String reservedMsg) {
        ReservedMsg = reservedMsg;
    }
}
