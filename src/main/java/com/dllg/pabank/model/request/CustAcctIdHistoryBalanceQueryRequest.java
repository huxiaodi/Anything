package com.dllg.pabank.model.request;

/**
 * 查询子帐号历史余额及待转可提现状态信息
 */
public class CustAcctIdHistoryBalanceQueryRequest extends PABankRequest {
    /**
     * 资金汇总账号
     */
    private String FundSummaryAcctNo;
    /**
     * 子账号
     */
    private String SubAcctNo;
    /**
     * 开始日期
     * 若是指定时间段查询，则必输
     * 当查询全部时，不起作用.同时不上送StartDate和EndDate
     * 取系统当天日期查询当天记录
     * 只能查相隔10天内记录
     */
    private String StartDate;
    /**
     * 终止日期
     * 若是指定时间段查询，则必输
     * 当查询全部时，不起作用.同时不上送StartDate和EndDate
     * 取系统当天日期查询当天记录
     * 只能查相隔10天内记录
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

    public String getFundSummaryAcctNo() {
        return FundSummaryAcctNo;
    }

    public void setFundSummaryAcctNo(String fundSummaryAcctNo) {
        FundSummaryAcctNo = fundSummaryAcctNo;
    }

    public String getSubAcctNo() {
        return SubAcctNo;
    }

    public void setSubAcctNo(String subAcctNo) {
        SubAcctNo = subAcctNo;
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
