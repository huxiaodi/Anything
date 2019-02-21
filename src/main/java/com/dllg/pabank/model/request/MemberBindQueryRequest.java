package com.dllg.pabank.model.request;

/**
 * 会员绑定信息查询
 */
public class MemberBindQueryRequest extends PABankRequest {
    /**
     * 查询标志
     * 1:全部会员
     * 2:单个会员
     */
    private String QueryFlag;
    /**
     * 资金汇总账号
     */
    private String FundSummaryAcctNo;
    /**
     * 子账户账号
     * 若QueryFlag为2时，子账户账号必输
     */
    private String SubAcctNo;
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

    public String getQueryFlag() {
        return QueryFlag;
    }

    public void setQueryFlag(String queryFlag) {
        QueryFlag = queryFlag;
    }

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
