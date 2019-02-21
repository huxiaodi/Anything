package com.dllg.pabank.model.request;

/**
 * 根据补款流水查询交易
 */
public class QueryTransactionByBatchNoRequest extends PABankRequest {
    /**
     * 资金汇总账号
     */
    private String FundSummaryAcctNo;
    /**
     * 交易日期
     */
    private String TranDate;
    /**
     * 商户的客户号
     */
    private String TraderClientNo;
    /**
     * 子商户的客户号
     */
    private String SubTraderClientNo;
    /**
     * 补款流水号
     */
    private String ExtraSeqNo;
    /**
     * 页码
     */
    private String PageNum;
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

    public String getTranDate() {
        return TranDate;
    }

    public void setTranDate(String tranDate) {
        TranDate = tranDate;
    }

    public String getTraderClientNo() {
        return TraderClientNo;
    }

    public void setTraderClientNo(String traderClientNo) {
        TraderClientNo = traderClientNo;
    }

    public String getSubTraderClientNo() {
        return SubTraderClientNo;
    }

    public void setSubTraderClientNo(String subTraderClientNo) {
        SubTraderClientNo = subTraderClientNo;
    }

    public String getExtraSeqNo() {
        return ExtraSeqNo;
    }

    public void setExtraSeqNo(String extraSeqNo) {
        ExtraSeqNo = extraSeqNo;
    }

    public String getPageNum() {
        return PageNum;
    }

    public void setPageNum(String pageNum) {
        PageNum = pageNum;
    }

    public String getReservedMsg() {
        return ReservedMsg;
    }

    public void setReservedMsg(String reservedMsg) {
        ReservedMsg = reservedMsg;
    }
}
