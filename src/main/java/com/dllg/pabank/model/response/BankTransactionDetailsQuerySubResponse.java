package com.dllg.pabank.model.response;

public class BankTransactionDetailsQuerySubResponse {
    /**
     * 记账标志
     * 1：转出
     * 2：转入
     */
    private String BookingFlag;
    /**
     * 交易状态
     * 0：成功
     */
    private String TranStatus;
    /**
     * 交易金额
     */
    private String TranAmt;
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
     * 记账类型
     * 1：会员支付
     * 2：会员冻结
     * 3：会员解冻
     * 4：登记挂账
     * 5：清分支付
     * 6：下单预支付
     * 7：确认并付款
     * 8：会员退款
     * 9：支付到平台
     */
    private String BookingType;
    /**
     * 转入子账户
     */
    private String InSubAcctNo;
    /**
     * 转出子账户
     */
    private String OutSubAcctNo;
    /**
     * 备注
     * 返回交易订单号
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

    public String getTranAmt() {
        return TranAmt;
    }

    public void setTranAmt(String tranAmt) {
        TranAmt = tranAmt;
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

    public String getBookingType() {
        return BookingType;
    }

    public void setBookingType(String bookingType) {
        BookingType = bookingType;
    }

    public String getInSubAcctNo() {
        return InSubAcctNo;
    }

    public void setInSubAcctNo(String inSubAcctNo) {
        InSubAcctNo = inSubAcctNo;
    }

    public String getOutSubAcctNo() {
        return OutSubAcctNo;
    }

    public void setOutSubAcctNo(String outSubAcctNo) {
        OutSubAcctNo = outSubAcctNo;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }
}
