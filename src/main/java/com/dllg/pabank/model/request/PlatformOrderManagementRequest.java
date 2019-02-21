package com.dllg.pabank.model.request;

/**
 * 平台订单管理
 */
public class PlatformOrderManagementRequest extends PABankRequest {
    /**
     * 功能标志
     * 2：平台代理确认收货（担保→收款方）
     * 3:平台代理申请退款（担保→付款方）
     */
    private String FunctionFlag;
    /**
     * 资金汇总账号
     */
    private String FundSummaryAcctNo;
    /**
     * 转出子账户
     * 付款方
     */
    private String OutSubAcctNo;
    /**
     * 转出会员代码
     */
    private String OutMemberCode;
    /**
     * 转出子账户名称
     */
    private String OutSubAcctName;
    /**
     * 转入子账户
     * 收款方
     */
    private String InSubAcctNo;
    /**
     * 转入会员代码
     */
    private String InMemberCode;
    /**
     * 转入子账户名称
     */
    private String InSubAcctName;
    /**
     * 交易金额
     */
    private String TranAmt;
    /**
     * 交易费用
     * 平台收取交易费用
     */
    private String TranFee;
    /**
     * 交易类型
     * 01：普通交易
     */
    private String TranType;
    /**
     * 币种
     * 默认：RMB
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

    public String getOutSubAcctName() {
        return OutSubAcctName;
    }

    public void setOutSubAcctName(String outSubAcctName) {
        OutSubAcctName = outSubAcctName;
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

    public String getInSubAcctName() {
        return InSubAcctName;
    }

    public void setInSubAcctName(String inSubAcctName) {
        InSubAcctName = inSubAcctName;
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

    public String getTranType() {
        return TranType;
    }

    public void setTranType(String tranType) {
        TranType = tranType;
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

    public String getReservedMsg() {
        return ReservedMsg;
    }

    public void setReservedMsg(String reservedMsg) {
        ReservedMsg = reservedMsg;
    }
}
