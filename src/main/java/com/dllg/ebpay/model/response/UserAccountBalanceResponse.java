package com.dllg.ebpay.model.response;


import java.math.BigDecimal;

public class UserAccountBalanceResponse {

    // 可用余额
    private BigDecimal acctAvailBal;
    // 可提现金额
    private BigDecimal cashAmt;

    private String subAcctNo;

    public String getSubAcctNo() {
        return subAcctNo;
    }

    public void setSubAcctNo(String subAcctNo) {
        this.subAcctNo = subAcctNo;
    }

    public BigDecimal getAcctAvailBal() {
        return acctAvailBal;
    }

    public void setAcctAvailBal(BigDecimal acctAvailBal) {
        this.acctAvailBal = acctAvailBal;
    }

    public BigDecimal getCashAmt() {
        return cashAmt;
    }

    public void setCashAmt(BigDecimal cashAmt) {
        this.cashAmt = cashAmt;
    }

    @Override
    public String toString() {
        return "UserAccountBalanceResponse{" +
                "acctAvailBal=" + acctAvailBal +
                ", cashAmt=" + cashAmt +
                '}';
    }
}
