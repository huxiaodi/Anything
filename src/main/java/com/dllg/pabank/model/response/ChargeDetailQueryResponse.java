package com.dllg.pabank.model.response;

/**
 * 查询充值明细-见证收单
 */
public class ChargeDetailQueryResponse extends PABankResponse {
    /**
     * 交易状态
     * （0：成功，1：失败，2：异常,3:冲正，5：待处理）
     */
    private String TranStatus;
    /**
     * 交易金额
     */
    private String TranAmt;
    /**
     * 佣金费
     */
    private String CommissionAmt;
    /**
     * 支付方式
     */
    private String PayMode;
    /**
     * 交易日期
     */
    private String TranDate;
    /**
     * 交易时间
     */
    private String TranTime;
    /**
     * 订单转入子账户
     */
    private String OrderInSubAcctNo;
    /**
     * 订单转入子账户名称
     */
    private String OrderInSubAcctName;
    /**
     * 订单实际转入子账户
     */
    private String OrderActInSubAcctNo;
    /**
     * 订单实际子账户名称
     */
    private String OrderActInSubAcctName;
    /**
     * 前置流水号
     */
    private String FrontSeqNo;
    /**
     * 交易描述
     */
    private String TranDesc;

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

    public String getCommissionAmt() {
        return CommissionAmt;
    }

    public void setCommissionAmt(String commissionAmt) {
        CommissionAmt = commissionAmt;
    }

    public String getPayMode() {
        return PayMode;
    }

    public void setPayMode(String payMode) {
        PayMode = payMode;
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

    public String getOrderInSubAcctNo() {
        return OrderInSubAcctNo;
    }

    public void setOrderInSubAcctNo(String orderInSubAcctNo) {
        OrderInSubAcctNo = orderInSubAcctNo;
    }

    public String getOrderInSubAcctName() {
        return OrderInSubAcctName;
    }

    public void setOrderInSubAcctName(String orderInSubAcctName) {
        OrderInSubAcctName = orderInSubAcctName;
    }

    public String getOrderActInSubAcctNo() {
        return OrderActInSubAcctNo;
    }

    public void setOrderActInSubAcctNo(String orderActInSubAcctNo) {
        OrderActInSubAcctNo = orderActInSubAcctNo;
    }

    public String getOrderActInSubAcctName() {
        return OrderActInSubAcctName;
    }

    public void setOrderActInSubAcctName(String orderActInSubAcctName) {
        OrderActInSubAcctName = orderActInSubAcctName;
    }

    public String getFrontSeqNo() {
        return FrontSeqNo;
    }

    public void setFrontSeqNo(String frontSeqNo) {
        FrontSeqNo = frontSeqNo;
    }

    public String getTranDesc() {
        return TranDesc;
    }

    public void setTranDesc(String tranDesc) {
        TranDesc = tranDesc;
    }
}
