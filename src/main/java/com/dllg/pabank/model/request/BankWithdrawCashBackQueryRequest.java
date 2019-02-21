package com.dllg.pabank.model.request;

/**
 * 查询银行提现退单信息
 */
public class BankWithdrawCashBackQueryRequest extends PABankRequest {
    /**
     * 功能标志
     * 1为查询当日数据
     */
    private String FunctionFlag;
    /**
     * 资金汇总账号
     */
    private String FundSummaryAcctNo;
    /**
     * 开始日期
     */
    private String StartDate;
    /**
     * 终止日期
     */
    private String EndDate;
    /**
     * 保留域
     */
    private String ReservedMsg;

    public String getFunctionFlag() {
        return FunctionFlag;
    }

    public void setFunctionFlag(String functionFlag) {
        FunctionFlag = functionFlag;
    }

    public String getFundSummaryAcctNo() {
        return FundSummaryAcctNo;
    }

    public void setFundSummaryAcctNo(String fundSummaryAcctNo) {
        FundSummaryAcctNo = fundSummaryAcctNo;
    }

    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String startDate) {
        StartDate = startDate;
    }

    public String getEndDate() {
        return EndDate;
    }

    public void setEndDate(String endDate) {
        EndDate = endDate;
    }

    public String getReservedMsg() {
        return ReservedMsg;
    }

    public void setReservedMsg(String reservedMsg) {
        ReservedMsg = reservedMsg;
    }
}
