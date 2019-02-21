package com.dllg.pabank.model.response;

public class BankClearQuerySubResponse {
    /**
     * 日期
     */
    private String Date;
    /**
     * 子账号类型
     * 1: 普通会员子账号
     * 2: 挂账子账号
     * 3: 手续费子账号
     * 4: 利息子账号
     * 5: 平台担保子账号
     * 7:在途
     * 8:理财购买子帐号
     * 9:理财赎回子帐号
     * 10:平台子拥有结算子帐号
     */
    private String SubAcctType;
    /**
     * 对账状态
     * 0；成功
     * 1：失败
     */
    private String ReconcileStatus;
    /**
     * 对账返回信息
     */
    private String ReconcileReturnMsg;
    /**
     * 总金额
     */
    private String TotalAmt;
    /**
     * 清算状态
     * 0：成功
     * 1：失败
     * 2：异常
     * 3:待处理
     */
    private String ClearingStatus;
    /**
     * 清算返回信息
     */
    private String ClearingReturnMsg;

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getSubAcctType() {
        return SubAcctType;
    }

    public void setSubAcctType(String subAcctType) {
        SubAcctType = subAcctType;
    }

    public String getReconcileStatus() {
        return ReconcileStatus;
    }

    public void setReconcileStatus(String reconcileStatus) {
        ReconcileStatus = reconcileStatus;
    }

    public String getReconcileReturnMsg() {
        return ReconcileReturnMsg;
    }

    public void setReconcileReturnMsg(String reconcileReturnMsg) {
        ReconcileReturnMsg = reconcileReturnMsg;
    }

    public String getTotalAmt() {
        return TotalAmt;
    }

    public void setTotalAmt(String totalAmt) {
        TotalAmt = totalAmt;
    }

    public String getClearingStatus() {
        return ClearingStatus;
    }

    public void setClearingStatus(String clearingStatus) {
        ClearingStatus = clearingStatus;
    }

    public String getClearingReturnMsg() {
        return ClearingReturnMsg;
    }

    public void setClearingReturnMsg(String clearingReturnMsg) {
        ClearingReturnMsg = clearingReturnMsg;
    }
}
