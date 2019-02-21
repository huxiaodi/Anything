package com.dllg.pabank.model.response;

public class CommonTransferRechargeQuerySubResponse {
    /**
     * 入账类型
     * 02：会员充值
     * 03：资金挂账
     */
    private String InAcctType;
    /**
     * 交易网会员代码
     */
    private String TranNetMemberCode;
    /**
     * 子账户账号
     */
    private String SubAcctNo;
    /**
     * 交易金额
     */
    private String TranAmt;
    /**
     * 入金账号
     */
    private String InAcctNo;
    /**
     * 入金账户名称
     */
    private String InAcctName;
    /**
     * 币种
     */
    private String Ccy;
    /**
     * 会计日期
     * 即银行主机记账日期
     */
    private String AccountingDate;
    /**
     * 银行名称
     * 付款账户银行名称
     */
    private String BankName;
    /**
     * 备注
     */
    private String Remark;
    /**
     * 前置流水号
     */
    private String FrontSeqNo;

    public String getInAcctType() {
        return InAcctType;
    }

    public void setInAcctType(String inAcctType) {
        InAcctType = inAcctType;
    }

    public String getTranNetMemberCode() {
        return TranNetMemberCode;
    }

    public void setTranNetMemberCode(String tranNetMemberCode) {
        TranNetMemberCode = tranNetMemberCode;
    }

    public String getSubAcctNo() {
        return SubAcctNo;
    }

    public void setSubAcctNo(String subAcctNo) {
        SubAcctNo = subAcctNo;
    }

    public String getTranAmt() {
        return TranAmt;
    }

    public void setTranAmt(String tranAmt) {
        TranAmt = tranAmt;
    }

    public String getInAcctNo() {
        return InAcctNo;
    }

    public void setInAcctNo(String inAcctNo) {
        InAcctNo = inAcctNo;
    }

    public String getInAcctName() {
        return InAcctName;
    }

    public void setInAcctName(String inAcctName) {
        InAcctName = inAcctName;
    }

    public String getCcy() {
        return Ccy;
    }

    public void setCcy(String ccy) {
        Ccy = ccy;
    }

    public String getAccountingDate() {
        return AccountingDate;
    }

    public void setAccountingDate(String accountingDate) {
        AccountingDate = accountingDate;
    }

    public String getBankName() {
        return BankName;
    }

    public void setBankName(String bankName) {
        BankName = bankName;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public String getFrontSeqNo() {
        return FrontSeqNo;
    }

    public void setFrontSeqNo(String frontSeqNo) {
        FrontSeqNo = frontSeqNo;
    }
}
