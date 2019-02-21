package com.dllg.pabank.model.request;

/**
 * 会员资金冻结-验证短信动态码
 */
public class MbrTchFrzVerifiedTextMsgsRequest extends PABankRequest {
    /**
     * 功能标志
     * 1：冻结（会员→担保）
     * 2：解冻（担保→会员）
     * 3：清分+冻结
     * 4：见证+收单的冻结资金解冻
     */
    private String FunctionFlag;
    /**
     * 资金汇总账号
     */
    private String FundSummaryAcctNo;
    /**
     * 子账户账号
     */
    private String SubAcctNo;
    /**
     * 交易网会员代码
     */
    private String TranNetMemberCode;
    /**
     * 交易金额
     */
    private String TranAmt;
    /**
     * 交易手续费
     * 解冻时，将根据该金额收取手续费，若无手续费则送0。
     */
    private String TranCommission;
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
     * 订单内容
     */
    private String OrderContent;
    /**
     * 备注
     */
    private String Remark;
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

    public String getFundSummaryAcctNo() {
        return FundSummaryAcctNo;
    }

    public void setFundSummaryAcctNo(String fundSummaryAcctNo) {
        FundSummaryAcctNo = fundSummaryAcctNo;
    }

    public String getSubAcctNo() {
        return SubAcctNo;
    }

    public void setSubAcctNo(String subAcctNo) {
        SubAcctNo = subAcctNo;
    }

    public String getTranNetMemberCode() {
        return TranNetMemberCode;
    }

    public void setTranNetMemberCode(String tranNetMemberCode) {
        TranNetMemberCode = tranNetMemberCode;
    }

    public String getTranAmt() {
        return TranAmt;
    }

    public void setTranAmt(String tranAmt) {
        TranAmt = tranAmt;
    }

    public String getTranCommission() {
        return TranCommission;
    }

    public void setTranCommission(String tranCommission) {
        TranCommission = tranCommission;
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
