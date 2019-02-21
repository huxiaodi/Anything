package com.dllg.pabank.model.response;

import java.util.List;

public class CustAcctIdBalanceQuerySubResponse {
    /**
     * 子账号
     * 可重复
     */
    private String SubAcctNo;
    /**
     * 子账户属性
     * 可重复
     * 1：普通会员子账号
     * 2：挂账子账号
     * 3：手续费子账号
     * 4：利息子账号
     * 5：平台担保子账号
     */
    private String SubAcctProperty;
    /**
     * 交易网会员代码
     * 可重复
     */
    private String TranNetMemberCode;
    /**
     * 子账户名称
     * 可重复
     */
    private String SubAcctName;
    /**
     * 账户可用余额
     * 可重复
     */
    private String AcctAvailBal;
    /**
     * 可提现金额
     * 可重复
     */
    private String CashAmt;
    /**
     * 维护日期
     * 可重复（开户日期或修改日期）
     */
    private String MaintenanceDate;

    public String getSubAcctNo() {
        return SubAcctNo;
    }

    public void setSubAcctNo(String subAcctNo) {
        SubAcctNo = subAcctNo;
    }

    public String getSubAcctProperty() {
        return SubAcctProperty;
    }

    public void setSubAcctProperty(String subAcctProperty) {
        SubAcctProperty = subAcctProperty;
    }

    public String getTranNetMemberCode() {
        return TranNetMemberCode;
    }

    public void setTranNetMemberCode(String tranNetMemberCode) {
        TranNetMemberCode = tranNetMemberCode;
    }

    public String getSubAcctName() {
        return SubAcctName;
    }

    public void setSubAcctName(String subAcctName) {
        SubAcctName = subAcctName;
    }

    public String getAcctAvailBal() {
        return AcctAvailBal;
    }

    public void setAcctAvailBal(String acctAvailBal) {
        AcctAvailBal = acctAvailBal;
    }

    public String getCashAmt() {
        return CashAmt;
    }

    public void setCashAmt(String cashAmt) {
        CashAmt = cashAmt;
    }

    public String getMaintenanceDate() {
        return MaintenanceDate;
    }

    public void setMaintenanceDate(String maintenanceDate) {
        MaintenanceDate = maintenanceDate;
    }
}
