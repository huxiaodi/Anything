package com.dllg.pabank.model.request;

/**
 * 调账-见证收单
 */
public class AccountRegulationRequest extends PABankRequest {
    /**
     * 资金汇总账号
     */
    private String FundSummaryAcctNo;
    /**
     * 收单渠道类型
     * 例如：01-橙E收款
     * 02-跨行快收（非T0）
     * 03-跨行快收（T0）
     * 04-聚合支付
     */
    private String AcquiringChannelType;
    /**
     * 订单号
     */
    private String OrderNo;
    /**
     * 子账户账号
     */
    private String SubAcctNo;
    /**
     * 交易网会员代码
     */
    private String TranNetMemberCode;
    /**
     * 子账户名称
     */
    private String SubAcctName;
    /**
     * 金额
     */
    private String Amt;
    /**
     * 币种
     */
    private String Ccy;
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

    public String getAcquiringChannelType() {
        return AcquiringChannelType;
    }

    public void setAcquiringChannelType(String acquiringChannelType) {
        AcquiringChannelType = acquiringChannelType;
    }

    public String getOrderNo() {
        return OrderNo;
    }

    public void setOrderNo(String orderNo) {
        OrderNo = orderNo;
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

    public String getAmt() {
        return Amt;
    }

    public void setAmt(String amt) {
        Amt = amt;
    }

    public String getCcy() {
        return Ccy;
    }

    public void setCcy(String ccy) {
        Ccy = ccy;
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
