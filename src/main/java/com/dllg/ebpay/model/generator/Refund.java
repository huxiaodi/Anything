package com.dllg.ebpay.model.generator;

import java.math.BigDecimal;
import java.util.Date;

/**
 *   refund
 * @mbg.generated do_not_delete_during_merge
 */
public class Refund {
    /**
     *   退款记录ID
     *   refund.refund_id
     * @mbg.generated
     */
    private String refundId;

    /**
     *   退款单号
     *   refund.refund_order_no
     * @mbg.generated
     */
    private String refundOrderNo;

    /**
     *   银行支付流水号
     *   refund.refund_trade_bank_no
     * @mbg.generated
     */
    private String refundTradeBankNo;

    /**
     *   银行退款流水号
     *   refund.refund_bank_no
     * @mbg.generated
     */
    private String refundBankNo;

    /**
     *   申请人
     *   refund.refund_proposer
     * @mbg.generated
     */
    private String refundProposer;

    /**
     *   申请企业
     *   refund.refund_company
     * @mbg.generated
     */
    private String refundCompany;

    /**
     *   0、待初审 1、待复审 2、待终审 3、待退款 4、已完成 5、已否决 6、退款失败
     *   refund.refund_status
     * @mbg.generated
     */
    private String refundStatus;

    /**
     *   失败原因
     *   refund.refund_false_reason
     * @mbg.generated
     */
    private String refundFalseReason;

    /**
     *   回调地址
     *   refund.refund_back_url
     * @mbg.generated
     */
    private String refundBackUrl;

    /**
     *   申请金额
     *   refund.refund_amount
     * @mbg.generated
     */
    private BigDecimal refundAmount;

    /**
     *   手续费
     *   refund.refund_fee
     * @mbg.generated
     */
    private BigDecimal refundFee;

    /**
     *   备注
     *   refund.refund_remark
     * @mbg.generated
     */
    private String refundRemark;

    /**
     *   申请时间
     *   refund.refund_apply_for_data
     * @mbg.generated
     */
    private Date refundApplyForData;

    /**
     *   更新时间
     *   refund.refund_update_data
     * @mbg.generated
     */
    private Date refundUpdateData;

    /**
     * 退款记录ID
     * @return the value of refund.refund_id
     * @mbg.generated
     */
    public String getRefundId() {
        return refundId;
    }

    /**
     * 退款记录ID
     * @param refundId the value for refund.refund_id
     * @mbg.generated
     */
    public void setRefundId(String refundId) {
        this.refundId = refundId == null ? null : refundId.trim();
    }

    /**
     * 退款单号
     * @return the value of refund.refund_order_no
     * @mbg.generated
     */
    public String getRefundOrderNo() {
        return refundOrderNo;
    }

    /**
     * 退款单号
     * @param refundOrderNo the value for refund.refund_order_no
     * @mbg.generated
     */
    public void setRefundOrderNo(String refundOrderNo) {
        this.refundOrderNo = refundOrderNo == null ? null : refundOrderNo.trim();
    }

    /**
     * 银行支付流水号
     * @return the value of refund.refund_trade_bank_no
     * @mbg.generated
     */
    public String getRefundTradeBankNo() {
        return refundTradeBankNo;
    }

    /**
     * 银行支付流水号
     * @param refundTradeBankNo the value for refund.refund_trade_bank_no
     * @mbg.generated
     */
    public void setRefundTradeBankNo(String refundTradeBankNo) {
        this.refundTradeBankNo = refundTradeBankNo == null ? null : refundTradeBankNo.trim();
    }

    /**
     * 银行退款流水号
     * @return the value of refund.refund_bank_no
     * @mbg.generated
     */
    public String getRefundBankNo() {
        return refundBankNo;
    }

    /**
     * 银行退款流水号
     * @param refundBankNo the value for refund.refund_bank_no
     * @mbg.generated
     */
    public void setRefundBankNo(String refundBankNo) {
        this.refundBankNo = refundBankNo == null ? null : refundBankNo.trim();
    }

    /**
     * 申请人
     * @return the value of refund.refund_proposer
     * @mbg.generated
     */
    public String getRefundProposer() {
        return refundProposer;
    }

    /**
     * 申请人
     * @param refundProposer the value for refund.refund_proposer
     * @mbg.generated
     */
    public void setRefundProposer(String refundProposer) {
        this.refundProposer = refundProposer == null ? null : refundProposer.trim();
    }

    /**
     * 申请企业
     * @return the value of refund.refund_company
     * @mbg.generated
     */
    public String getRefundCompany() {
        return refundCompany;
    }

    /**
     * 申请企业
     * @param refundCompany the value for refund.refund_company
     * @mbg.generated
     */
    public void setRefundCompany(String refundCompany) {
        this.refundCompany = refundCompany == null ? null : refundCompany.trim();
    }

    /**
     * 0、待初审 1、待复审 2、待终审 3、待退款 4、已完成 5、已否决 6、退款失败
     * @return the value of refund.refund_status
     * @mbg.generated
     */
    public String getRefundStatus() {
        return refundStatus;
    }

    /**
     * 0、待初审 1、待复审 2、待终审 3、待退款 4、已完成 5、已否决 6、退款失败
     * @param refundStatus the value for refund.refund_status
     * @mbg.generated
     */
    public void setRefundStatus(String refundStatus) {
        this.refundStatus = refundStatus == null ? null : refundStatus.trim();
    }

    /**
     * 失败原因
     * @return the value of refund.refund_false_reason
     * @mbg.generated
     */
    public String getRefundFalseReason() {
        return refundFalseReason;
    }

    /**
     * 失败原因
     * @param refundFalseReason the value for refund.refund_false_reason
     * @mbg.generated
     */
    public void setRefundFalseReason(String refundFalseReason) {
        this.refundFalseReason = refundFalseReason == null ? null : refundFalseReason.trim();
    }

    /**
     * 回调地址
     * @return the value of refund.refund_back_url
     * @mbg.generated
     */
    public String getRefundBackUrl() {
        return refundBackUrl;
    }

    /**
     * 回调地址
     * @param refundBackUrl the value for refund.refund_back_url
     * @mbg.generated
     */
    public void setRefundBackUrl(String refundBackUrl) {
        this.refundBackUrl = refundBackUrl == null ? null : refundBackUrl.trim();
    }

    /**
     * 申请金额
     * @return the value of refund.refund_amount
     * @mbg.generated
     */
    public BigDecimal getRefundAmount() {
        return refundAmount;
    }

    /**
     * 申请金额
     * @param refundAmount the value for refund.refund_amount
     * @mbg.generated
     */
    public void setRefundAmount(BigDecimal refundAmount) {
        this.refundAmount = refundAmount;
    }

    /**
     * 手续费
     * @return the value of refund.refund_fee
     * @mbg.generated
     */
    public BigDecimal getRefundFee() {
        return refundFee;
    }

    /**
     * 手续费
     * @param refundFee the value for refund.refund_fee
     * @mbg.generated
     */
    public void setRefundFee(BigDecimal refundFee) {
        this.refundFee = refundFee;
    }

    /**
     * 备注
     * @return the value of refund.refund_remark
     * @mbg.generated
     */
    public String getRefundRemark() {
        return refundRemark;
    }

    /**
     * 备注
     * @param refundRemark the value for refund.refund_remark
     * @mbg.generated
     */
    public void setRefundRemark(String refundRemark) {
        this.refundRemark = refundRemark == null ? null : refundRemark.trim();
    }

    /**
     * 申请时间
     * @return the value of refund.refund_apply_for_data
     * @mbg.generated
     */
    public Date getRefundApplyForData() {
        return refundApplyForData;
    }

    /**
     * 申请时间
     * @param refundApplyForData the value for refund.refund_apply_for_data
     * @mbg.generated
     */
    public void setRefundApplyForData(Date refundApplyForData) {
        this.refundApplyForData = refundApplyForData;
    }

    /**
     * 更新时间
     * @return the value of refund.refund_update_data
     * @mbg.generated
     */
    public Date getRefundUpdateData() {
        return refundUpdateData;
    }

    /**
     * 更新时间
     * @param refundUpdateData the value for refund.refund_update_data
     * @mbg.generated
     */
    public void setRefundUpdateData(Date refundUpdateData) {
        this.refundUpdateData = refundUpdateData;
    }
}