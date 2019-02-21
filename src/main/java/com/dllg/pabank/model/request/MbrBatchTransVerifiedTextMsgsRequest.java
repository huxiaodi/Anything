package com.dllg.pabank.model.request;

import java.util.List;

/**
 * 会员批量交易-验证短信动态码
 */
public class MbrBatchTransVerifiedTextMsgsRequest extends PABankRequest {
    /**
     * 功能标志
     * 1：批量支付
     * 2：批量确认
     */
    private String FunctionFlag;
    /**
     * 转出子账户账号
     */
    private String OutSubAcctNo;
    /**
     * 转出会员代码
     */
    private String OutMemberCode;
    /**
     * 资金汇总账号
     */
    private String FundSummaryAcctNo;
    /**
     * 订单数量
     * 必须大于1
     */
    private String OrdersCount;
    /**
     * 交易信息数组
     */
    private String TranItemArray;
    /**
     * 转入子账户账号
     */
    private String InSubAcctNo;

    /**
     * 转入会员代码
     */
    private String InMemberCode;
    /**
     * 交易金额
     */
    private String TranAmt;
    /**
     * 交易费用
     */
    private String TranFee;
    /**
     * 币种
     * 默认为RMB
     */
    private String Ccy;
    /**
     * 订单号
     */
    private String OrderNo;
    /**
     * 订单内容
     */
    private String OrderContent;
    /**
     * 备注
     */
    private String Remark;
    /**
     * 交易流水号
     */
    private String TranSeqNo;
    /**
     * 短信指令号
     * 当使用短信验证时，必输
     */
    private String MessageOrderNo;
    /**
     * 短信验证码
     */
    private String MessageCheckCode;
    /**
     * 保留域
     */
    private String ReservedMsg;

    public String getFunctionFlag() {
        return FunctionFlag;
    }

    public void setFunctionFlag(String functionFlag) {
        FunctionFlag = functionFlag;
    }

    public String getOutSubAcctNo() {
        return OutSubAcctNo;
    }

    public void setOutSubAcctNo(String outSubAcctNo) {
        OutSubAcctNo = outSubAcctNo;
    }

    public String getOutMemberCode() {
        return OutMemberCode;
    }

    public void setOutMemberCode(String outMemberCode) {
        OutMemberCode = outMemberCode;
    }

    public String getFundSummaryAcctNo() {
        return FundSummaryAcctNo;
    }

    public void setFundSummaryAcctNo(String fundSummaryAcctNo) {
        FundSummaryAcctNo = fundSummaryAcctNo;
    }

    public String getOrdersCount() {
        return OrdersCount;
    }

    public void setOrdersCount(String ordersCount) {
        OrdersCount = ordersCount;
    }

    public String getTranItemArray() {
        return TranItemArray;
    }

    public void setTranItemArray(String tranItemArray) {
        TranItemArray = tranItemArray;
    }

    public String getInSubAcctNo() {
        return InSubAcctNo;
    }

    public void setInSubAcctNo(String inSubAcctNo) {
        InSubAcctNo = inSubAcctNo;
    }

    public String getInMemberCode() {
        return InMemberCode;
    }

    public void setInMemberCode(String inMemberCode) {
        InMemberCode = inMemberCode;
    }

    public String getTranAmt() {
        return TranAmt;
    }

    public void setTranAmt(String tranAmt) {
        TranAmt = tranAmt;
    }

    public String getTranFee() {
        return TranFee;
    }

    public void setTranFee(String tranFee) {
        TranFee = tranFee;
    }

    public String getCcy() {
        return Ccy;
    }

    public void setCcy(String ccy) {
        Ccy = ccy;
    }

    public String getOrderNo() {
        return OrderNo;
    }

    public void setOrderNo(String orderNo) {
        OrderNo = orderNo;
    }

    public String getOrderContent() {
        return OrderContent;
    }

    public void setOrderContent(String orderContent) {
        OrderContent = orderContent;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public String getTranSeqNo() {
        return TranSeqNo;
    }

    public void setTranSeqNo(String tranSeqNo) {
        TranSeqNo = tranSeqNo;
    }

    public String getMessageOrderNo() {
        return MessageOrderNo;
    }

    public void setMessageOrderNo(String messageOrderNo) {
        MessageOrderNo = messageOrderNo;
    }

    public String getMessageCheckCode() {
        return MessageCheckCode;
    }

    public void setMessageCheckCode(String messageCheckCode) {
        MessageCheckCode = messageCheckCode;
    }

    public String getReservedMsg() {
        return ReservedMsg;
    }

    public void setReservedMsg(String reservedMsg) {
        ReservedMsg = reservedMsg;
    }
}
