package com.dllg.pabank.model.request;

/**
 * 查询银行单笔交易状态
 */
public class SingleTransactionStatusQueryRequest extends PABankRequest {
    /**
     * 资金汇总账号
     */
    private String FundSummaryAcctNo;
    /**
     * 功能标志
     * 2：会员间交易
     * 3：提现
     * 4：充值
     */
    private String FunctionFlag;
    /**
     * 交易网流水号
     */
    private String TranNetSeqNo;
    /**
     * 子账户账号
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

    public String getFunctionFlag() {
        return FunctionFlag;
    }

    public void setFunctionFlag(String functionFlag) {
        FunctionFlag = functionFlag;
    }

    public String getTranNetSeqNo() {
        return TranNetSeqNo;
    }

    public void setTranNetSeqNo(String tranNetSeqNo) {
        TranNetSeqNo = tranNetSeqNo;
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
