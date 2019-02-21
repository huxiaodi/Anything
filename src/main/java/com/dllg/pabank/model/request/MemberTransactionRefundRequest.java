package com.dllg.pabank.model.request;

/**
 * 会员间交易退款-不验证
 */
public class MemberTransactionRefundRequest extends PABankRequest {
    /**
     * 功能标志
     * 1：会员交易接口【确认付款】退款
     * 针对【6006/6034/6101】funcflag=2-确认并付款的退款
     * 2：会员交易接口【直接支付】退款
     * 针对【6006/6034/6101】funcflag=6、9-直接支付的退款
     * 3：平台订单管理接口【平台代理确认收货】退款。
     * 针对【6031】2-平台代理确认收货的退款
     * 4：会员批量交易接口【批量确认】退款
     * 针对【6052/6120/6133】2-批量确认的退款
     * 5：会员批量交易接口【直接支付】退款
     * 针对【6052/6120/6133】3-直接支付的退款。未提供使用。
     * 6：会员资金支付接口的退款
     * 针对【6163/6165/6166】会员资金支付的退款
     * 7：会员交易（多借多贷）接口的退款
     * 针对【6119】6、9-直接支付的退款。6119是专用接口，不提供开放使用。
     */
    private String FunctionFlag;
    /**
     * 资金汇总账号
     */
    private String FundSummaryAcctNo;
    /**
     * 原交易流水号
     */
    private String OldTranSeqNo;
    /**
     * 原转出子账户
     */
    private String OldOutSubAcctNo;
    /**
     * 转出会员代码
     */
    private String OldOutMemberCode;
    /**
     * 转入子账户账号
     */
    private String OldInSubAcctNo;
    /**
     * 转入会员代码
     */
    private String OldInMemberCode;
    /**
     * 原订单号
     * 1、3、4、5、6分支必输
     */
    private String OldOrderNo;
    /**
     * 退款金额
     * 包含退款手续费
     */
    private String ReturnAmt;
    /**
     * 退款手续费
     * 6分支填0.0
     */
    private String ReturnCommission;
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

    public String getOldTranSeqNo() {
        return OldTranSeqNo;
    }

    public void setOldTranSeqNo(String oldTranSeqNo) {
        OldTranSeqNo = oldTranSeqNo;
    }

    public String getOldOutSubAcctNo() {
        return OldOutSubAcctNo;
    }

    public void setOldOutSubAcctNo(String oldOutSubAcctNo) {
        OldOutSubAcctNo = oldOutSubAcctNo;
    }

    public String getOldOutMemberCode() {
        return OldOutMemberCode;
    }

    public void setOldOutMemberCode(String oldOutMemberCode) {
        OldOutMemberCode = oldOutMemberCode;
    }

    public String getOldInSubAcctNo() {
        return OldInSubAcctNo;
    }

    public void setOldInSubAcctNo(String oldInSubAcctNo) {
        OldInSubAcctNo = oldInSubAcctNo;
    }

    public String getOldInMemberCode() {
        return OldInMemberCode;
    }

    public void setOldInMemberCode(String oldInMemberCode) {
        OldInMemberCode = oldInMemberCode;
    }

    public String getOldOrderNo() {
        return OldOrderNo;
    }

    public void setOldOrderNo(String oldOrderNo) {
        OldOrderNo = oldOrderNo;
    }

    public String getReturnAmt() {
        return ReturnAmt;
    }

    public void setReturnAmt(String returnAmt) {
        ReturnAmt = returnAmt;
    }

    public String getReturnCommission() {
        return ReturnCommission;
    }

    public void setReturnCommission(String returnCommission) {
        ReturnCommission = returnCommission;
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
