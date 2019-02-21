package com.dllg.pabank.model.response;

/**
 * 资金汇总账号
 */
public class RedemptionModeQueryResponse extends PABankResponse {
    /**
     * 赎回方式
     */
    private String RedeemMode;
    /**
     * 剩余份额
     */
    private String RemainLot;

    public String getRedeemMode() {
        return RedeemMode;
    }

    public void setRedeemMode(String redeemMode) {
        RedeemMode = redeemMode;
    }

    public String getRemainLot() {
        return RemainLot;
    }

    public void setRemainLot(String remainLot) {
        RemainLot = remainLot;
    }
}
