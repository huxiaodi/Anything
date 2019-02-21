package com.dllg.pabank.model.response;

/**
 * 查询资金汇总账户余额
 */
public class SupAcctIdBalanceQueryResponse extends PABankResponse {
    /**
     * 上日余额
     * 前一天的余额
     */
    private String LastBalance;
    /**
     * 当前余额
     */
    private String CurBalabce;
    /**
     * 保留域
     */
    private String ReservedMsg;

    public String getLastBalance() {
        return LastBalance;
    }

    public void setLastBalance(String lastBalance) {
        LastBalance = lastBalance;
    }

    public String getCurBalabce() {
        return CurBalabce;
    }

    public void setCurBalabce(String curBalabce) {
        CurBalabce = curBalabce;
    }

    public String getReservedMsg() {
        return ReservedMsg;
    }

    public void setReservedMsg(String reservedMsg) {
        ReservedMsg = reservedMsg;
    }
}
