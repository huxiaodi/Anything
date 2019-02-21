package com.dllg.pabank.model.response;

/**
 * 会员绑定提现账户-银联鉴权
 */
public class BindRelateAcctUnionPayResponse extends PABankResponse {
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
