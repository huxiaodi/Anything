package com.dllg.pabank.model.request;

/**
 * 查询银行子账户余额
 */
public class CustAcctIdBalanceQueryRequest extends PABankRequest {
    /**
     * 资金汇总账号
     */
    private String FundSummaryAcctNo;
    /**
     * 子账号
     * 若SelectFlag为2时，子账号必输
     */
    private String SubAcctNo;
    /**
     * 查询标志
     * 2：普通会员子账号
     * 3：功能子账号
     */
    private String QueryFlag;
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

    public String getQueryFlag() {
        return QueryFlag;
    }

    public void setQueryFlag(String queryFlag) {
        QueryFlag = queryFlag;
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
