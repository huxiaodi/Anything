package com.dllg.pabank.model.request;

/**
 * 查询银行在途清算结果
 */
public class BankClearQueryRequest extends PABankRequest {
    /**
     * 功能标志
     * 1:全部
     * 2：指定时间段
     */
    private String FunctionFlag;
    /**
     * 资金汇总账号
     */
    private String FundSummaryAcctNo;
    /**
     * 开始日期
     * 若是指定时间段查询，则必输，当查询全部时，不起作用
     */
    private String StartDate;
    /**
     * 终止日期
     * 若是指定时间段查询，则必输，当查询全部时，不起作用
     */
    private String EndDate;
    /**
     * 页码
     * 起始值为1，每次最多返回20条记录
     * 第二页返回的记录数为第21至40条记录
     * 第三页为41至60条记录
     * 顺序均按照建立时间的先后
     */
    private String PageNum;
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
