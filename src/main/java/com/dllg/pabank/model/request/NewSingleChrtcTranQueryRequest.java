package com.dllg.pabank.model.request;

/**
 * 单笔理财交易查询-支持多产品
 */
public class NewSingleChrtcTranQueryRequest extends PABankRequest {
    /**
     * 资金汇总账号
     */
    private String FundSummaryAcctNo;
    /**
     * 市场流水号
     */
    private String MarketSeqNo;
    /**
     * 子账号
     */
    private String SubAcctNo;
    /**
     * 交易日期
     */
    private String TranDate;
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

    public String getMarketSeqNo() {
        return MarketSeqNo;
    }

    public void setMarketSeqNo(String marketSeqNo) {
        MarketSeqNo = marketSeqNo;
    }

    public String getSubAcctNo() {
        return SubAcctNo;
    }

    public void setSubAcctNo(String subAcctNo) {
        SubAcctNo = subAcctNo;
    }

    public String getTranDate() {
        return TranDate;
    }

    public void setTranDate(String tranDate) {
        TranDate = tranDate;
    }

    public String getReservedMsg() {
        return ReservedMsg;
    }

    public void setReservedMsg(String reservedMsg) {
        ReservedMsg = reservedMsg;
    }
}
