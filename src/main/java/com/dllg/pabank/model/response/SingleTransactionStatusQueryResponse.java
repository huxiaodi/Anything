package com.dllg.pabank.model.response;

/**
 * 查询银行单笔交易状态
 */
public class SingleTransactionStatusQueryResponse extends PABankResponse {
    /**
     * 记账标志
     * 1：登记挂账
     * 2：支付
     * 3：提现
     * 4：清分
     * 5:下单预支付
     * 6：确认并付款
     * 7：退款
     * 8：支付到平台
     * N:其他
     */
    private String BookingFlag;
    /**
     * 交易状态
     * 0：成功
     * 1：失败
     * 2：待确认
     * 5：待处理
     * 6：处理中
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
     * 转入子账户账号
     */
    private String InSubAcctNo;
    /**
     * 转出子账户账号
     */
    private String OutSubAcctNo;
    /**
     * 失败信息
     * 当提现失败时，返回交易失败原因
     */
    private String FailMsg;

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

    public String getFailMsg() {
        return FailMsg;
    }

    public void setFailMsg(String failMsg) {
        FailMsg = failMsg;
    }
}
