package com.dllg.pabank.model.request;

/**
 * 补款流水查询
 */
public class BatchNoQueryRequest extends PABankRequest {
    /**
     * 资金汇总账号
     */
    private String FundSummaryAcctNo;
    /**
     * 交易日期
     * 自然日
     */
    private String TranDate;
    /**
     * 商户的客户号
     */
    private String TraderClientNo;
    /**
     * 子商户的客户号
     * 支付机构商户客户号，为空时查询支付机构所有商户的创建结果（包含支付机构）
     */
    private String SubTraderClientNo;
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
