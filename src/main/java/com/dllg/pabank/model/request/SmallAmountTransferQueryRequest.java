package com.dllg.pabank.model.request;

/**
 * 查询小额鉴权转账结果
 */
public class SmallAmountTransferQueryRequest extends PABankRequest {
    /**
     * 资金汇总账号
     */
    private String FundSummaryAcctNo;
    /**
     * 原交易流水号
     * 小额鉴权流水
     */
    private String OldTranSeqNo;
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

    public String getOldTranSeqNo() {
        return OldTranSeqNo;
    }

    public void setOldTranSeqNo(String oldTranSeqNo) {
        OldTranSeqNo = oldTranSeqNo;
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
