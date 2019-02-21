package com.dllg.pabank.model.response;

import java.util.List;

/**
 * 会员理财余额查询-支持多产品
 */
public class NewCustChrtcBalanceQueryResponse extends PABankResponse {
    /**
     * 账户余额
     */
    private String Balance;
    /**
     * 累计收入
     */
    private String TotalIncome;
    /**
     * 周收益
     */
    private String WeekIncome;
    /**
     * 昨日收益
     */
    private String YesterdayIncome;
    /**
     * 客户号
     */
    private String ClientNo;
    /**
     * 客户姓名
     */
    private String ClientName;
    /**
     * 证件号码
     */
    private List GlobalId;
    /**
     * 基金交易帐号
     */
    private String FundTranAcctNo;
    /**
     * 基金帐号
     */
    private String FundAcctNo;
    /**
     * 银行账号
     */
    private String BankAcctNo;
    /**
     * 保留域
     */
    private String ReservedMsg;

    public String getBalance() {
        return Balance;
    }

    public void setBalance(String balance) {
        Balance = balance;
    }

    public String getTotalIncome() {
        return TotalIncome;
    }

    public void setTotalIncome(String totalIncome) {
        TotalIncome = totalIncome;
    }

    public String getWeekIncome() {
        return WeekIncome;
    }

    public void setWeekIncome(String weekIncome) {
        WeekIncome = weekIncome;
    }

    public String getYesterdayIncome() {
        return YesterdayIncome;
    }

    public void setYesterdayIncome(String yesterdayIncome) {
        YesterdayIncome = yesterdayIncome;
    }

    public String getClientNo() {
        return ClientNo;
    }

    public void setClientNo(String clientNo) {
        ClientNo = clientNo;
    }

    public String getClientName() {
        return ClientName;
    }

    public void setClientName(String clientName) {
        ClientName = clientName;
    }

    public List getGlobalId() {
        return GlobalId;
    }

    public void setGlobalId(List globalId) {
        GlobalId = globalId;
    }

    public String getFundTranAcctNo() {
        return FundTranAcctNo;
    }

    public void setFundTranAcctNo(String fundTranAcctNo) {
        FundTranAcctNo = fundTranAcctNo;
    }

    public String getFundAcctNo() {
        return FundAcctNo;
    }

    public void setFundAcctNo(String fundAcctNo) {
        FundAcctNo = fundAcctNo;
    }

    public String getBankAcctNo() {
        return BankAcctNo;
    }

    public void setBankAcctNo(String bankAcctNo) {
        BankAcctNo = bankAcctNo;
    }

    public String getReservedMsg() {
        return ReservedMsg;
    }

    public void setReservedMsg(String reservedMsg) {
        ReservedMsg = reservedMsg;
    }
}
