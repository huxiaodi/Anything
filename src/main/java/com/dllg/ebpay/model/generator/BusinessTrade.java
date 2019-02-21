package com.dllg.ebpay.model.generator;

import java.util.Date;

/**
 *   业务交易记录
 *   business_trade
 * @mbg.generated do_not_delete_during_merge
 */
public class BusinessTrade {
    /**
     *   id
     *   business_trade.business_trade_id
     * @mbg.generated
     */
    private String businessTradeId;

    /**
     *   子账户流水记录id
     *   business_trade.business_trade_account_sub_trade_id
     * @mbg.generated
     */
    private String businessTradeAccountSubTradeId;

    /**
     *   业务单号
     *   business_trade.business_trade_business_no
     * @mbg.generated
     */
    private String businessTradeBusinessNo;

    /**
     *   交易单号（判断是否重复支付）
     *   business_trade.business_trade_trasaction_no
     * @mbg.generated
     */
    private String businessTradeTrasactionNo;

    /**
     *   业务类型
     *   business_trade.business_trade_business_type
     * @mbg.generated
     */
    private String businessTradeBusinessType;

    /**
     *   交易对象
     *   business_trade.business_trade_object
     * @mbg.generated
     */
    private String businessTradeObject;

    /**
     *   交易来源
     *   business_trade.business_trade_business_from
     * @mbg.generated
     */
    private String businessTradeBusinessFrom;

    /**
     *   业务内容
     *   business_trade.business_trade_business_content
     * @mbg.generated
     */
    private String businessTradeBusinessContent;

    /**
     *   清分类型
     *   business_trade.business_trade_clearing_type
     * @mbg.generated
     */
    private String businessTradeClearingType;

    /**
     *   品名
     *   business_trade.business_trade_goods_name
     * @mbg.generated
     */
    private String businessTradeGoodsName;

    /**
     *   业务备注
     *   business_trade.business_trade_remark
     * @mbg.generated
     */
    private String businessTradeRemark;

    /**
     *   状态，1-创建，2-交易中，3-交易成功，4-交易失败，5-交易撤销
     *   business_trade.business_trade_status
     * @mbg.generated
     */
    private Integer businessTradeStatus;

    /**
     *   支付页面地址
     *   business_trade.business_trade_pay_url
     * @mbg.generated
     */
    private String businessTradePayUrl;

    /**
     *   回调地址
     *   business_trade.business_trade_back_url
     * @mbg.generated
     */
    private String businessTradeBackUrl;

    /**
     *   回调json
     *   business_trade.business_trade_back_json
     * @mbg.generated
     */
    private String businessTradeBackJson;

    /**
     *   绑定的业务三方id
     *   business_trade.business_trade_bind_relation_id
     * @mbg.generated
     */
    private String businessTradeBindRelationId;

    /**
     *   创建人
     *   business_trade.business_trade_create_user_id
     * @mbg.generated
     */
    private String businessTradeCreateUserId;

    /**
     *   创建时间（交易时间）
     *   business_trade.business_trade_create_time
     * @mbg.generated
     */
    private Date businessTradeCreateTime;

    /**
     *   修改人
     *   business_trade.business_trade_update_user_id
     * @mbg.generated
     */
    private String businessTradeUpdateUserId;

    /**
     *   修改时间
     *   business_trade.business_trade_update_time
     * @mbg.generated
     */
    private Date businessTradeUpdateTime;

    /**
     *   是否删除 0-否,1-是
     *   business_trade.business_trade_is_delete
     * @mbg.generated
     */
    private Boolean businessTradeIsDelete;

    /**
     *   保留域
     *   business_trade.business_trade_reserved_msg
     * @mbg.generated
     */
    private String businessTradeReservedMsg;

    /**
     * id
     * @return the value of business_trade.business_trade_id
     * @mbg.generated
     */
    public String getBusinessTradeId() {
        return businessTradeId;
    }

    /**
     * id
     * @param businessTradeId the value for business_trade.business_trade_id
     * @mbg.generated
     */
    public void setBusinessTradeId(String businessTradeId) {
        this.businessTradeId = businessTradeId == null ? null : businessTradeId.trim();
    }

    /**
     * 子账户流水记录id
     * @return the value of business_trade.business_trade_account_sub_trade_id
     * @mbg.generated
     */
    public String getBusinessTradeAccountSubTradeId() {
        return businessTradeAccountSubTradeId;
    }

    /**
     * 子账户流水记录id
     * @param businessTradeAccountSubTradeId the value for business_trade.business_trade_account_sub_trade_id
     * @mbg.generated
     */
    public void setBusinessTradeAccountSubTradeId(String businessTradeAccountSubTradeId) {
        this.businessTradeAccountSubTradeId = businessTradeAccountSubTradeId == null ? null : businessTradeAccountSubTradeId.trim();
    }

    /**
     * 业务单号
     * @return the value of business_trade.business_trade_business_no
     * @mbg.generated
     */
    public String getBusinessTradeBusinessNo() {
        return businessTradeBusinessNo;
    }

    /**
     * 业务单号
     * @param businessTradeBusinessNo the value for business_trade.business_trade_business_no
     * @mbg.generated
     */
    public void setBusinessTradeBusinessNo(String businessTradeBusinessNo) {
        this.businessTradeBusinessNo = businessTradeBusinessNo == null ? null : businessTradeBusinessNo.trim();
    }

    /**
     * 交易单号（判断是否重复支付）
     * @return the value of business_trade.business_trade_trasaction_no
     * @mbg.generated
     */
    public String getBusinessTradeTrasactionNo() {
        return businessTradeTrasactionNo;
    }

    /**
     * 交易单号（判断是否重复支付）
     * @param businessTradeTrasactionNo the value for business_trade.business_trade_trasaction_no
     * @mbg.generated
     */
    public void setBusinessTradeTrasactionNo(String businessTradeTrasactionNo) {
        this.businessTradeTrasactionNo = businessTradeTrasactionNo == null ? null : businessTradeTrasactionNo.trim();
    }

    /**
     * 业务类型
     * @return the value of business_trade.business_trade_business_type
     * @mbg.generated
     */
    public String getBusinessTradeBusinessType() {
        return businessTradeBusinessType;
    }

    /**
     * 业务类型
     * @param businessTradeBusinessType the value for business_trade.business_trade_business_type
     * @mbg.generated
     */
    public void setBusinessTradeBusinessType(String businessTradeBusinessType) {
        this.businessTradeBusinessType = businessTradeBusinessType == null ? null : businessTradeBusinessType.trim();
    }

    /**
     * 交易对象
     * @return the value of business_trade.business_trade_object
     * @mbg.generated
     */
    public String getBusinessTradeObject() {
        return businessTradeObject;
    }

    /**
     * 交易对象
     * @param businessTradeObject the value for business_trade.business_trade_object
     * @mbg.generated
     */
    public void setBusinessTradeObject(String businessTradeObject) {
        this.businessTradeObject = businessTradeObject == null ? null : businessTradeObject.trim();
    }

    /**
     * 交易来源
     * @return the value of business_trade.business_trade_business_from
     * @mbg.generated
     */
    public String getBusinessTradeBusinessFrom() {
        return businessTradeBusinessFrom;
    }

    /**
     * 交易来源
     * @param businessTradeBusinessFrom the value for business_trade.business_trade_business_from
     * @mbg.generated
     */
    public void setBusinessTradeBusinessFrom(String businessTradeBusinessFrom) {
        this.businessTradeBusinessFrom = businessTradeBusinessFrom == null ? null : businessTradeBusinessFrom.trim();
    }

    /**
     * 业务内容
     * @return the value of business_trade.business_trade_business_content
     * @mbg.generated
     */
    public String getBusinessTradeBusinessContent() {
        return businessTradeBusinessContent;
    }

    /**
     * 业务内容
     * @param businessTradeBusinessContent the value for business_trade.business_trade_business_content
     * @mbg.generated
     */
    public void setBusinessTradeBusinessContent(String businessTradeBusinessContent) {
        this.businessTradeBusinessContent = businessTradeBusinessContent == null ? null : businessTradeBusinessContent.trim();
    }

    /**
     * 清分类型
     * @return the value of business_trade.business_trade_clearing_type
     * @mbg.generated
     */
    public String getBusinessTradeClearingType() {
        return businessTradeClearingType;
    }

    /**
     * 清分类型
     * @param businessTradeClearingType the value for business_trade.business_trade_clearing_type
     * @mbg.generated
     */
    public void setBusinessTradeClearingType(String businessTradeClearingType) {
        this.businessTradeClearingType = businessTradeClearingType == null ? null : businessTradeClearingType.trim();
    }

    /**
     * 品名
     * @return the value of business_trade.business_trade_goods_name
     * @mbg.generated
     */
    public String getBusinessTradeGoodsName() {
        return businessTradeGoodsName;
    }

    /**
     * 品名
     * @param businessTradeGoodsName the value for business_trade.business_trade_goods_name
     * @mbg.generated
     */
    public void setBusinessTradeGoodsName(String businessTradeGoodsName) {
        this.businessTradeGoodsName = businessTradeGoodsName == null ? null : businessTradeGoodsName.trim();
    }

    /**
     * 业务备注
     * @return the value of business_trade.business_trade_remark
     * @mbg.generated
     */
    public String getBusinessTradeRemark() {
        return businessTradeRemark;
    }

    /**
     * 业务备注
     * @param businessTradeRemark the value for business_trade.business_trade_remark
     * @mbg.generated
     */
    public void setBusinessTradeRemark(String businessTradeRemark) {
        this.businessTradeRemark = businessTradeRemark == null ? null : businessTradeRemark.trim();
    }

    /**
     * 状态，1-创建，2-交易中，3-交易成功，4-交易失败，5-交易撤销
     * @return the value of business_trade.business_trade_status
     * @mbg.generated
     */
    public Integer getBusinessTradeStatus() {
        return businessTradeStatus;
    }

    /**
     * 状态，1-创建，2-交易中，3-交易成功，4-交易失败，5-交易撤销
     * @param businessTradeStatus the value for business_trade.business_trade_status
     * @mbg.generated
     */
    public void setBusinessTradeStatus(Integer businessTradeStatus) {
        this.businessTradeStatus = businessTradeStatus;
    }

    /**
     * 支付页面地址
     * @return the value of business_trade.business_trade_pay_url
     * @mbg.generated
     */
    public String getBusinessTradePayUrl() {
        return businessTradePayUrl;
    }

    /**
     * 支付页面地址
     * @param businessTradePayUrl the value for business_trade.business_trade_pay_url
     * @mbg.generated
     */
    public void setBusinessTradePayUrl(String businessTradePayUrl) {
        this.businessTradePayUrl = businessTradePayUrl == null ? null : businessTradePayUrl.trim();
    }

    /**
     * 回调地址
     * @return the value of business_trade.business_trade_back_url
     * @mbg.generated
     */
    public String getBusinessTradeBackUrl() {
        return businessTradeBackUrl;
    }

    /**
     * 回调地址
     * @param businessTradeBackUrl the value for business_trade.business_trade_back_url
     * @mbg.generated
     */
    public void setBusinessTradeBackUrl(String businessTradeBackUrl) {
        this.businessTradeBackUrl = businessTradeBackUrl == null ? null : businessTradeBackUrl.trim();
    }

    /**
     * 回调json
     * @return the value of business_trade.business_trade_back_json
     * @mbg.generated
     */
    public String getBusinessTradeBackJson() {
        return businessTradeBackJson;
    }

    /**
     * 回调json
     * @param businessTradeBackJson the value for business_trade.business_trade_back_json
     * @mbg.generated
     */
    public void setBusinessTradeBackJson(String businessTradeBackJson) {
        this.businessTradeBackJson = businessTradeBackJson == null ? null : businessTradeBackJson.trim();
    }

    /**
     * 绑定的业务三方id
     * @return the value of business_trade.business_trade_bind_relation_id
     * @mbg.generated
     */
    public String getBusinessTradeBindRelationId() {
        return businessTradeBindRelationId;
    }

    /**
     * 绑定的业务三方id
     * @param businessTradeBindRelationId the value for business_trade.business_trade_bind_relation_id
     * @mbg.generated
     */
    public void setBusinessTradeBindRelationId(String businessTradeBindRelationId) {
        this.businessTradeBindRelationId = businessTradeBindRelationId == null ? null : businessTradeBindRelationId.trim();
    }

    /**
     * 创建人
     * @return the value of business_trade.business_trade_create_user_id
     * @mbg.generated
     */
    public String getBusinessTradeCreateUserId() {
        return businessTradeCreateUserId;
    }

    /**
     * 创建人
     * @param businessTradeCreateUserId the value for business_trade.business_trade_create_user_id
     * @mbg.generated
     */
    public void setBusinessTradeCreateUserId(String businessTradeCreateUserId) {
        this.businessTradeCreateUserId = businessTradeCreateUserId == null ? null : businessTradeCreateUserId.trim();
    }

    /**
     * 创建时间（交易时间）
     * @return the value of business_trade.business_trade_create_time
     * @mbg.generated
     */
    public Date getBusinessTradeCreateTime() {
        return businessTradeCreateTime;
    }

    /**
     * 创建时间（交易时间）
     * @param businessTradeCreateTime the value for business_trade.business_trade_create_time
     * @mbg.generated
     */
    public void setBusinessTradeCreateTime(Date businessTradeCreateTime) {
        this.businessTradeCreateTime = businessTradeCreateTime;
    }

    /**
     * 修改人
     * @return the value of business_trade.business_trade_update_user_id
     * @mbg.generated
     */
    public String getBusinessTradeUpdateUserId() {
        return businessTradeUpdateUserId;
    }

    /**
     * 修改人
     * @param businessTradeUpdateUserId the value for business_trade.business_trade_update_user_id
     * @mbg.generated
     */
    public void setBusinessTradeUpdateUserId(String businessTradeUpdateUserId) {
        this.businessTradeUpdateUserId = businessTradeUpdateUserId == null ? null : businessTradeUpdateUserId.trim();
    }

    /**
     * 修改时间
     * @return the value of business_trade.business_trade_update_time
     * @mbg.generated
     */
    public Date getBusinessTradeUpdateTime() {
        return businessTradeUpdateTime;
    }

    /**
     * 修改时间
     * @param businessTradeUpdateTime the value for business_trade.business_trade_update_time
     * @mbg.generated
     */
    public void setBusinessTradeUpdateTime(Date businessTradeUpdateTime) {
        this.businessTradeUpdateTime = businessTradeUpdateTime;
    }

    /**
     * 是否删除 0-否,1-是
     * @return the value of business_trade.business_trade_is_delete
     * @mbg.generated
     */
    public Boolean getBusinessTradeIsDelete() {
        return businessTradeIsDelete;
    }

    /**
     * 是否删除 0-否,1-是
     * @param businessTradeIsDelete the value for business_trade.business_trade_is_delete
     * @mbg.generated
     */
    public void setBusinessTradeIsDelete(Boolean businessTradeIsDelete) {
        this.businessTradeIsDelete = businessTradeIsDelete;
    }

    /**
     * 保留域
     * @return the value of business_trade.business_trade_reserved_msg
     * @mbg.generated
     */
    public String getBusinessTradeReservedMsg() {
        return businessTradeReservedMsg;
    }

    /**
     * 保留域
     * @param businessTradeReservedMsg the value for business_trade.business_trade_reserved_msg
     * @mbg.generated
     */
    public void setBusinessTradeReservedMsg(String businessTradeReservedMsg) {
        this.businessTradeReservedMsg = businessTradeReservedMsg == null ? null : businessTradeReservedMsg.trim();
    }
}