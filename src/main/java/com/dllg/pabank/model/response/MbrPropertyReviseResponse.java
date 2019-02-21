package com.dllg.pabank.model.response;

/**
 * 修改会员属性-普通商户子账户
 */
public class MbrPropertyReviseResponse extends PABankResponse {
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
