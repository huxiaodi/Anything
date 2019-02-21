package com.dllg.pabank.model.request;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;

/**
 * 会员间交易-不验证
 */
public class MemberTransactionRequest extends PABankRequest {
    /**
     * 功能标志
     * 1：下单预支付 （付款方→担保）
     * 2：确认并付款（担保→收款方）
     * 3：退款（担保→付款方）
     * 6：直接支付（会员A→会员B）
     */
    @Pattern(regexp = "^[1,2,3,6]{1}$",message = "功能标志有误")
    private String FunctionFlag;
    /**
     * 转出子账户
     * 付款方
     */
    @NotBlank(message = "转出子账户不能为空")
    private String OutSubAcctNo;
    /**
     * 资金汇总账号
     */
    @NotBlank(message = "资金总账号不能为空")
    private String FundSummaryAcctNo;
    /**
     * 转出会员代码
     */
    @NotBlank(message = "转出会员代码不能为空")
    private String OutMemberCode;
    /**
     * 转出子账户名称
     */
    @NotBlank(message = "转出子账户名称不能为空")
    private String OutSubAcctName;
    /**
     * 转入子账户
     * 收款方
     */
    @NotBlank(message = "转入子账户不能为空")
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
    @Pattern(regexp = "^\\d{1,17}(?:\\.\\d{1,2})?$",message = "交易金额填写有误")
    private String TranAmt;
    /**
     * 交易费用
     * 平台收取交易费用
     */
    @Pattern(regexp = "^\\d{1,17}(?:\\.\\d{1,2})?$",message = "交易费用填写有误")
    private String TranFee;
    /**
     * 交易类型
     * 01：普通交易
     */
    @Pattern(regexp = "^01$",message = "交易类型有误")
    private String TranType;
    /**
     * 币种
     * 默认：RMB
     */
    @NotBlank(message = "币种不能为空")
    private String Ccy;
    /**
     * 订单号
     */
    @NotBlank(message = "订单号不能为空")
    private String OrderNo;
    /**
     * 订单内容
     */
    private String OrderContent;
    /**
     * 备注
     * 建议可送订单号，可在对账文件的备注字段获取到
     */
    private String Remark;
    /**
     * 保留域
     */
    private String ReservedMsg;
    /**
     * 网银签名
     * 若需短信验证码则此项必输
     */
    private String WebSign;

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

    public String getFundSummaryAcctNo() {
        return FundSummaryAcctNo;
    }

    public void setFundSummaryAcctNo(String fundSummaryAcctNo) {
        FundSummaryAcctNo = fundSummaryAcctNo;
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

    public String getWebSign() {
        return WebSign;
    }

    public void setWebSign(String webSign) {
        WebSign = webSign;
    }
}
