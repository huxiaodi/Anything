package com.dllg.pabank.model.request;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;

/**
 * 会员提现-支持手续费及支付密码验证
 */
public class MemberWithdrawSprtFeePwdVerifyRequest extends PABankRequest {
    /**
     * 资金汇总账号
     */
    @NotBlank(message = "资金总账号不能为空")
    private String FundSummaryAcctNo;
    /**
     * 子账户账号
     */
    @NotBlank(message = "提现子账户不能为空")
    private String SubAcctNo;
    /**
     * 交易网会员代码
     */
    @NotBlank(message = "提现子账户代码不能为空")
    private String TranNetMemberCode;
    /**
     * 子账户名称
     */
    @NotBlank(message = "提现子账户名称不能为空")
    private String SubAcctName;
    /**
     * 提现账号
     * 即收款账户，必须是在系统中维护的提现账号
     */
    @NotBlank(message = "提现银行账户不能为空")
    private String TakeCashAcctNo;
    /**
     * 提现账户名称
     * 银行卡户名，必须与子账户名称一致
     */
    @NotBlank(message = "提现银行账户名称不能为空")
    private String TakeCashAcctName;
    /**
     * 币种
     * 默认为RMB
     */
    @NotBlank(message = "币种不能为空")
    private String Ccy;
    /**
     * 申请提现金额
     */
    @Pattern(regexp = "^\\d{1,17}(?:\\.\\d{1,2})?$",message = "提现金额填写有误")
    private String ApplyTakeCashAmt;
    /**
     * 提现手续费
     * 收取给平台的
     */
    @Pattern(regexp = "^\\d{1,17}(?:\\.\\d{1,2})?$",message = "提现手续费填写有误")
    private String TakeCashCommission;
    /**
     * 短信指令号
     * 送空
     */
    private String MessageOrderNo;
    /**
     * 短信验证码
     * 送空
     */
    private String MessageCheckCode;
    /**
     * 备注
     */
    private String Remark;
    /**
     * 保留域
     */
    private String ReservedMsg;
    /**
     * 网银签名
     */
    private String WebSign;

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

    public String getSubAcctName() {
        return SubAcctName;
    }

    public void setSubAcctName(String subAcctName) {
        SubAcctName = subAcctName;
    }

    public String getTakeCashAcctNo() {
        return TakeCashAcctNo;
    }

    public void setTakeCashAcctNo(String takeCashAcctNo) {
        TakeCashAcctNo = takeCashAcctNo;
    }

    public String getTakeCashAcctName() {
        return TakeCashAcctName;
    }

    public void setTakeCashAcctName(String takeCashAcctName) {
        TakeCashAcctName = takeCashAcctName;
    }

    public String getCcy() {
        return Ccy;
    }

    public void setCcy(String ccy) {
        Ccy = ccy;
    }

    public String getApplyTakeCashAmt() {
        return ApplyTakeCashAmt;
    }

    public void setApplyTakeCashAmt(String applyTakeCashAmt) {
        ApplyTakeCashAmt = applyTakeCashAmt;
    }

    public String getTakeCashCommission() {
        return TakeCashCommission;
    }

    public void setTakeCashCommission(String takeCashCommission) {
        TakeCashCommission = takeCashCommission;
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
