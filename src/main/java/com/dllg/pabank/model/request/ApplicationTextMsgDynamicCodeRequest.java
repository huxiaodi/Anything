package com.dllg.pabank.model.request;

/**
 * 申请提现或支付短信动态码
 */
public class ApplicationTextMsgDynamicCodeRequest extends PABankRequest {
    /**
     * 资金汇总账号
     */
    private String FundSummaryAcctNo;
    /**
     * 交易网会员代码
     */
    private String TranNetMemberCode;
    /**
     * 子账户账号
     */
    private String SubAcctNo;
    /**
     * 交易类型
     * 1：提现
     * 2：支付
     */
    private String TranType;
    /**
     * 交易金额
     */
    private String TranAmt;
    /**
     * 银行卡号
     */
    private String BankCardNo;
    /**
     * 订单号
     */
    private String OrderNo;
    /**
     * 备注
     */
    private String Remark;
    /**
     * 保留域
     */
    private String ReservedMsg;

    public String getFundSummaryAcctNo() {
        return FundSummaryAcctNo;
    }

    public void setFundSummaryAcctNo(String fundSummaryAcctNo) {
        FundSummaryAcctNo = fundSummaryAcctNo;
    }

    public String getTranNetMemberCode() {
        return TranNetMemberCode;
    }

    public void setTranNetMemberCode(String tranNetMemberCode) {
        TranNetMemberCode = tranNetMemberCode;
    }

    public String getSubAcctNo() {
        return SubAcctNo;
    }

    public void setSubAcctNo(String subAcctNo) {
        SubAcctNo = subAcctNo;
    }

    public String getTranType() {
        return TranType;
    }

    public void setTranType(String tranType) {
        TranType = tranType;
    }

    public String getTranAmt() {
        return TranAmt;
    }

    public void setTranAmt(String tranAmt) {
        TranAmt = tranAmt;
    }

    public String getBankCardNo() {
        return BankCardNo;
    }

    public void setBankCardNo(String bankCardNo) {
        BankCardNo = bankCardNo;
    }

    public String getOrderNo() {
        return OrderNo;
    }

    public void setOrderNo(String orderNo) {
        OrderNo = orderNo;
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
