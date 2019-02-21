package com.dllg.pabank.model.response;

public class BankWithdrawCashDetailsQuerySubResponse {
    /**
     * 记账标志
     * 01:提现
     * 02:清分
     */
    private String BookingFlag;
    /**
     * 交易状态
     * 0：成功
     */
    private String TranStatus;
    /**
     * 记账说明
     */
    private String BookingMsg;
    /**
     * 交易网会员代码
     */
    private String TranNetMemberCode;
    /**
     * 子账户账号
     */
    private String SubAcctNo;
    /**
     * 子账户名称
     */
    private String SubAcctName;
    /**
     * 交易金额
     */
    private String TranAmt;
    /**
     * 手续费
     */
    private String Commission;
    /**
     * 交易日期
     */
    private String TranDate;
    /**
     * 交易时间
     */
    private String TranTime;
    /**
     * 前置流水号
     */
    private String FrontSeqNo;
    /**
     * 备注
     */
    private String Remark;

    public String getBookingFlag() {
        return BookingFlag;
    }

    public void setBookingFlag(String bookingFlag) {
        BookingFlag = bookingFlag;
    }

    public String getTranStatus() {
        return TranStatus;
    }

    public void setTranStatus(String tranStatus) {
        TranStatus = tranStatus;
    }

    public String getBookingMsg() {
        return BookingMsg;
    }

    public void setBookingMsg(String bookingMsg) {
        BookingMsg = bookingMsg;
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

    public String getSubAcctName() {
        return SubAcctName;
    }

    public void setSubAcctName(String subAcctName) {
        SubAcctName = subAcctName;
    }

    public String getTranAmt() {
        return TranAmt;
    }

    public void setTranAmt(String tranAmt) {
        TranAmt = tranAmt;
    }

    public String getCommission() {
        return Commission;
    }

    public void setCommission(String commission) {
        Commission = commission;
    }

    public String getTranDate() {
        return TranDate;
    }

    public void setTranDate(String tranDate) {
        TranDate = tranDate;
    }

    public String getTranTime() {
        return TranTime;
    }

    public void setTranTime(String tranTime) {
        TranTime = tranTime;
    }

    public String getFrontSeqNo() {
        return FrontSeqNo;
    }

    public void setFrontSeqNo(String frontSeqNo) {
        FrontSeqNo = frontSeqNo;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }
}
