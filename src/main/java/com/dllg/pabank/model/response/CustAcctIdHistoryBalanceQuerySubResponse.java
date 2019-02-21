package com.dllg.pabank.model.response;

public class CustAcctIdHistoryBalanceQuerySubResponse {
    /**
     * 日期
     */
    private String Date;
    /**
     * 日终可用余额
     */
    private String DayAcctAvailBal;
    /**
     * 日终可提现余额
     */
    private String DayCashBal;
    /**
     * 日终冻结余额
     */
    private String DayFreezeBal;
    /**
     * 当日待转可提现发生额
     */
    private String DayCashOccurAmt;
    /**
     * 日终待转可提现余额
     */
    private String DayWaitCashBal;
    /**
     * 待转可提现状态
     * 0：待转
     * 1：已转
     * 2：无需转
     * 3：异常
     */
    private String CashStatus;

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getDayAcctAvailBal() {
        return DayAcctAvailBal;
    }

    public void setDayAcctAvailBal(String dayAcctAvailBal) {
        DayAcctAvailBal = dayAcctAvailBal;
    }

    public String getDayCashBal() {
        return DayCashBal;
    }

    public void setDayCashBal(String dayCashBal) {
        DayCashBal = dayCashBal;
    }

    public String getDayFreezeBal() {
        return DayFreezeBal;
    }

    public void setDayFreezeBal(String dayFreezeBal) {
        DayFreezeBal = dayFreezeBal;
    }

    public String getDayCashOccurAmt() {
        return DayCashOccurAmt;
    }

    public void setDayCashOccurAmt(String dayCashOccurAmt) {
        DayCashOccurAmt = dayCashOccurAmt;
    }

    public String getDayWaitCashBal() {
        return DayWaitCashBal;
    }

    public void setDayWaitCashBal(String dayWaitCashBal) {
        DayWaitCashBal = dayWaitCashBal;
    }

    public String getCashStatus() {
        return CashStatus;
    }

    public void setCashStatus(String cashStatus) {
        CashStatus = cashStatus;
    }
}
