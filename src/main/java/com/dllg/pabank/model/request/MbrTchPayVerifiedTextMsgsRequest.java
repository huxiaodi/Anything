package com.dllg.pabank.model.request;

/**
 * 会员资金支付-验短信动态码
 */
public class MbrTchPayVerifiedTextMsgsRequest extends PABankRequest {
    /**
     * 资金汇总账号
     */
    private String FundSummaryAcctNo;
    /**
     * 转出子账户账号
     */
    private String OutSubAcctNo;
    /**
     * 转出交易网会员代码
     */
    private String OutTranNetMemberCode;
    /**
     * 手续费
     * 归于平台的收益
     */
    private String Commission;
    /**
     * 币种
     * RMB
     */
    private String Ccy;
    /**
     * 订单号
     */
    private String OrderNo;
    /**
     * 转入子账户数
     */
    private String InSubAcctNum;
    /**
     * 交易信息数组
     */
    private String TranItemArray;
    /**
     * 交易流水号
     * （用于退款处理）
     */
    private String TranSeqNo;
    /**
     * 转入子账户账号
     */
    private String InSubAcctNo;
    /**
     * 转入交易网会员代码
     */
    private String InTranNetMemberCode;
    /**
     * 交易金额
     */
    private String TranAmt;
    /**
     * 备注
     */
    private String Remark;
    /**
     * 保留域
     */
    private String ReservedMsg;
    /**
     * 短信指令号
     * 当使用短信验证时，必输
     */
    private String MessageOrderNo;
    /**
     * 短信验证码
     */
    private String MessageCheckCode;

    public String getFundSummaryAcctNo() {
        return FundSummaryAcctNo;
    }

    public void setFundSummaryAcctNo(String fundSummaryAcctNo) {
        FundSummaryAcctNo = fundSummaryAcctNo;
    }

    public String getOutSubAcctNo() {
        return OutSubAcctNo;
    }

    public void setOutSubAcctNo(String outSubAcctNo) {
        OutSubAcctNo = outSubAcctNo;
    }

    public String getOutTranNetMemberCode() {
        return OutTranNetMemberCode;
    }

    public void setOutTranNetMemberCode(String outTranNetMemberCode) {
        OutTranNetMemberCode = outTranNetMemberCode;
    }

    public String getCommission() {
        return Commission;
    }

    public void setCommission(String commission) {
        Commission = commission;
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

    public String getInSubAcctNum() {
        return InSubAcctNum;
    }

    public void setInSubAcctNum(String inSubAcctNum) {
        InSubAcctNum = inSubAcctNum;
    }

    public String getTranItemArray() {
        return TranItemArray;
    }

    public void setTranItemArray(String tranItemArray) {
        TranItemArray = tranItemArray;
    }

    public String getTranSeqNo() {
        return TranSeqNo;
    }

    public void setTranSeqNo(String tranSeqNo) {
        TranSeqNo = tranSeqNo;
    }

    public String getInSubAcctNo() {
        return InSubAcctNo;
    }

    public void setInSubAcctNo(String inSubAcctNo) {
        InSubAcctNo = inSubAcctNo;
    }

    public String getInTranNetMemberCode() {
        return InTranNetMemberCode;
    }

    public void setInTranNetMemberCode(String inTranNetMemberCode) {
        InTranNetMemberCode = inTranNetMemberCode;
    }

    public String getTranAmt() {
        return TranAmt;
    }

    public void setTranAmt(String tranAmt) {
        TranAmt = tranAmt;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public String getReservedMsg() {
        return ReservedMsg;
    }

    public void setReservedMsg(String reservedMsg) {
        ReservedMsg = reservedMsg;
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
}
