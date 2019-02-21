package com.dllg.pabank.model.request;

/**
 * 会员提现-不验证
 */
public class MembershipWithdrawCashRequest extends PABankRequest {
    /**
     * 交易网名称
     * 市场名称
     */
    private String TranWebName;
    /**
     * 子账户账号
     */
    private String SubAcctNo;
    /**
     * 会员证件类型
     * 见文档附录的“接口证件类型说明”
     * 例如：身份证，送1
     */
    private String MemberGlobalType;
    /**
     * 会员证件号码
     */
    private String MemberGlobalId;
    /**
     * 交易网会员代码
     */
    private String TranNetMemberCode;
    /**
     * 会员名称
     */
    private String MemberName;
    /**
     * 资金汇总账号
     */
    private String FundSummaryAcctNo;
    /**
     * 提现账号
     * 银行卡
     */
    private String TakeCashAcctNo;
    /**
     * 出金账户名称
     * 银行卡户名
     */
    private String OutAmtAcctName;
    /**
     * 币种
     * 默认为RMB
     */
    private String Ccy;
    /**
     * 可提现金额
     */
    private String CashAmt;
    /**
     * 备注
     * 建议可送订单号，可在对账文件的备注字段获取到。
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

    public String getTranWebName() {
        return TranWebName;
    }

    public void setTranWebName(String tranWebName) {
        TranWebName = tranWebName;
    }

    public String getSubAcctNo() {
        return SubAcctNo;
    }

    public void setSubAcctNo(String subAcctNo) {
        SubAcctNo = subAcctNo;
    }

    public String getMemberGlobalType() {
        return MemberGlobalType;
    }

    public void setMemberGlobalType(String memberGlobalType) {
        MemberGlobalType = memberGlobalType;
    }

    public String getMemberGlobalId() {
        return MemberGlobalId;
    }

    public void setMemberGlobalId(String memberGlobalId) {
        MemberGlobalId = memberGlobalId;
    }

    public String getTranNetMemberCode() {
        return TranNetMemberCode;
    }

    public void setTranNetMemberCode(String tranNetMemberCode) {
        TranNetMemberCode = tranNetMemberCode;
    }

    public String getMemberName() {
        return MemberName;
    }

    public void setMemberName(String memberName) {
        MemberName = memberName;
    }

    public String getFundSummaryAcctNo() {
        return FundSummaryAcctNo;
    }

    public void setFundSummaryAcctNo(String fundSummaryAcctNo) {
        FundSummaryAcctNo = fundSummaryAcctNo;
    }

    public String getTakeCashAcctNo() {
        return TakeCashAcctNo;
    }

    public void setTakeCashAcctNo(String takeCashAcctNo) {
        TakeCashAcctNo = takeCashAcctNo;
    }

    public String getOutAmtAcctName() {
        return OutAmtAcctName;
    }

    public void setOutAmtAcctName(String outAmtAcctName) {
        OutAmtAcctName = outAmtAcctName;
    }

    public String getCcy() {
        return Ccy;
    }

    public void setCcy(String ccy) {
        Ccy = ccy;
    }

    public String getCashAmt() {
        return CashAmt;
    }

    public void setCashAmt(String cashAmt) {
        CashAmt = cashAmt;
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
