package com.dllg.pabank.model.request;

/**
 * 查询明细单验证码
 */
public class DetailVerifiedCodeQueryRequest extends PABankRequest {
    /**
     * 资金汇总账号
     */
    private String FundSummaryAcctNo;
    /**
     * 原前置流水号
     */
    private String OldFrontSeqNo;
    /**
     * 原交易类型
     */
    private String OldTranType;
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

    public String getOldFrontSeqNo() {
        return OldFrontSeqNo;
    }

    public void setOldFrontSeqNo(String oldFrontSeqNo) {
        OldFrontSeqNo = oldFrontSeqNo;
    }

    public String getOldTranType() {
        return OldTranType;
    }

    public void setOldTranType(String oldTranType) {
        OldTranType = oldTranType;
    }

    public String getReservedMsg() {
        return ReservedMsg;
    }

    public void setReservedMsg(String reservedMsg) {
        ReservedMsg = reservedMsg;
    }
}
