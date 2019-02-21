package com.dllg.ebpay.model.generator;

import java.math.BigDecimal;
import java.util.Date;

/**
 *   申请交易记录
 *   application_trade
 * @mbg.generated do_not_delete_during_merge
 */
public class ApplicationTrade {
    /**
     *   id
     *   application_trade.application_trade_id
     * @mbg.generated
     */
    private String applicationTradeId;

    /**
     *   子账户流水记录id
     *   application_trade.application_trade_account_sub_trade_id
     * @mbg.generated
     */
    private String applicationTradeAccountSubTradeId;

    /**
     *   业务交易记录id
     *   application_trade.application_trade_business_trade_id
     * @mbg.generated
     */
    private String applicationTradeBusinessTradeId;

    /**
     *   绑定的业务三方id
     *   application_trade.application_trade_bind_relation_id
     * @mbg.generated
     */
    private String applicationTradeBindRelationId;

    /**
     *   主体子账号id
     *   application_trade.application_trade_account_sub_id
     * @mbg.generated
     */
    private String applicationTradeAccountSubId;

    /**
     *   主体子账户名称
     *   application_trade.application_trade_account_sub_no
     * @mbg.generated
     */
    private String applicationTradeAccountSubNo;

    /**
     *   子账户所属体系
     *   application_trade.application_trade_account_type_name
     * @mbg.generated
     */
    private String applicationTradeAccountTypeName;

    /**
     *   申请单号
     *   application_trade.application_trade_application_no
     * @mbg.generated
     */
    private String applicationTradeApplicationNo;

    /**
     *   申请人
     *   application_trade.application_trade_proposer
     * @mbg.generated
     */
    private String applicationTradeProposer;

    /**
     *   交易金额（元）
     *   application_trade.application_trade_tran_amt
     * @mbg.generated
     */
    private BigDecimal applicationTradeTranAmt;

    /**
     *   业务单号
     *   application_trade.application_trade_business_no
     * @mbg.generated
     */
    private String applicationTradeBusinessNo;

    /**
     *   交易单号（判断是否重复支付）
     *   application_trade.application_trade_trasaction_no
     * @mbg.generated
     */
    private String applicationTradeTrasactionNo;

    /**
     *   业务类型
     *   application_trade.application_trade_business_type
     * @mbg.generated
     */
    private String applicationTradeBusinessType;

    /**
     *   交易对象
     *   application_trade.application_trade_trasaction_object
     * @mbg.generated
     */
    private String applicationTradeTrasactionObject;

    /**
     *   交易来源
     *   application_trade.application_trade_trasaction_from
     * @mbg.generated
     */
    private String applicationTradeTrasactionFrom;

    /**
     *   业务内容
     *   application_trade.application_trade_business_content
     * @mbg.generated
     */
    private String applicationTradeBusinessContent;

    /**
     *   清分类型
     *   application_trade.application_trade_clearing_type
     * @mbg.generated
     */
    private String applicationTradeClearingType;

    /**
     *   品名
     *   application_trade.application_trade_goods_name
     * @mbg.generated
     */
    private String applicationTradeGoodsName;

    /**
     *   业务
     *   application_trade.application_trade_remark
     * @mbg.generated
     */
    private String applicationTradeRemark;

    /**
     *   0、待初审 1、待复审 2、待终审 3、待付款 4、已完成 5、已否决 6、付款失败
     *   application_trade.application_trade_application_status
     * @mbg.generated
     */
    private Integer applicationTradeApplicationStatus;

    /**
     *   审核原因
     *   application_trade.application_trade_reason
     * @mbg.generated
     */
    private String applicationTradeReason;

    /**
     *   回调地址
     *   application_trade.application_trade_back_url
     * @mbg.generated
     */
    private String applicationTradeBackUrl;

    /**
     *   转入子账户id（收款方）
     *   application_trade.application_trade_in_account_sub_id
     * @mbg.generated
     */
    private String applicationTradeInAccountSubId;

    /**
     *   创建人
     *   application_trade.application_trade_create_user_id
     * @mbg.generated
     */
    private String applicationTradeCreateUserId;

    /**
     *   创建时间（交易时间）
     *   application_trade.application_trade_create_time
     * @mbg.generated
     */
    private Date applicationTradeCreateTime;

    /**
     *   修改人
     *   application_trade.application_trade_update_user_id
     * @mbg.generated
     */
    private String applicationTradeUpdateUserId;

    /**
     *   修改时间
     *   application_trade.application_trade_update_time
     * @mbg.generated
     */
    private Date applicationTradeUpdateTime;

    /**
     *   是否删除 0-否,1-是
     *   application_trade.application_trade_is_delete
     * @mbg.generated
     */
    private Boolean applicationTradeIsDelete;

    /**
     *   保留域
     *   application_trade.application_trade_reserved_msg
     * @mbg.generated
     */
    private String applicationTradeReservedMsg;

    /**
     * id
     * @return the value of application_trade.application_trade_id
     * @mbg.generated
     */
    public String getApplicationTradeId() {
        return applicationTradeId;
    }

    /**
     * id
     * @param applicationTradeId the value for application_trade.application_trade_id
     * @mbg.generated
     */
    public void setApplicationTradeId(String applicationTradeId) {
        this.applicationTradeId = applicationTradeId == null ? null : applicationTradeId.trim();
    }

    /**
     * 子账户流水记录id
     * @return the value of application_trade.application_trade_account_sub_trade_id
     * @mbg.generated
     */
    public String getApplicationTradeAccountSubTradeId() {
        return applicationTradeAccountSubTradeId;
    }

    /**
     * 子账户流水记录id
     * @param applicationTradeAccountSubTradeId the value for application_trade.application_trade_account_sub_trade_id
     * @mbg.generated
     */
    public void setApplicationTradeAccountSubTradeId(String applicationTradeAccountSubTradeId) {
        this.applicationTradeAccountSubTradeId = applicationTradeAccountSubTradeId == null ? null : applicationTradeAccountSubTradeId.trim();
    }

    /**
     * 业务交易记录id
     * @return the value of application_trade.application_trade_business_trade_id
     * @mbg.generated
     */
    public String getApplicationTradeBusinessTradeId() {
        return applicationTradeBusinessTradeId;
    }

    /**
     * 业务交易记录id
     * @param applicationTradeBusinessTradeId the value for application_trade.application_trade_business_trade_id
     * @mbg.generated
     */
    public void setApplicationTradeBusinessTradeId(String applicationTradeBusinessTradeId) {
        this.applicationTradeBusinessTradeId = applicationTradeBusinessTradeId == null ? null : applicationTradeBusinessTradeId.trim();
    }

    /**
     * 绑定的业务三方id
     * @return the value of application_trade.application_trade_bind_relation_id
     * @mbg.generated
     */
    public String getApplicationTradeBindRelationId() {
        return applicationTradeBindRelationId;
    }

    /**
     * 绑定的业务三方id
     * @param applicationTradeBindRelationId the value for application_trade.application_trade_bind_relation_id
     * @mbg.generated
     */
    public void setApplicationTradeBindRelationId(String applicationTradeBindRelationId) {
        this.applicationTradeBindRelationId = applicationTradeBindRelationId == null ? null : applicationTradeBindRelationId.trim();
    }

    /**
     * 主体子账号id
     * @return the value of application_trade.application_trade_account_sub_id
     * @mbg.generated
     */
    public String getApplicationTradeAccountSubId() {
        return applicationTradeAccountSubId;
    }

    /**
     * 主体子账号id
     * @param applicationTradeAccountSubId the value for application_trade.application_trade_account_sub_id
     * @mbg.generated
     */
    public void setApplicationTradeAccountSubId(String applicationTradeAccountSubId) {
        this.applicationTradeAccountSubId = applicationTradeAccountSubId == null ? null : applicationTradeAccountSubId.trim();
    }

    /**
     * 主体子账户名称
     * @return the value of application_trade.application_trade_account_sub_no
     * @mbg.generated
     */
    public String getApplicationTradeAccountSubNo() {
        return applicationTradeAccountSubNo;
    }

    /**
     * 主体子账户名称
     * @param applicationTradeAccountSubNo the value for application_trade.application_trade_account_sub_no
     * @mbg.generated
     */
    public void setApplicationTradeAccountSubNo(String applicationTradeAccountSubNo) {
        this.applicationTradeAccountSubNo = applicationTradeAccountSubNo == null ? null : applicationTradeAccountSubNo.trim();
    }

    /**
     * 子账户所属体系
     * @return the value of application_trade.application_trade_account_type_name
     * @mbg.generated
     */
    public String getApplicationTradeAccountTypeName() {
        return applicationTradeAccountTypeName;
    }

    /**
     * 子账户所属体系
     * @param applicationTradeAccountTypeName the value for application_trade.application_trade_account_type_name
     * @mbg.generated
     */
    public void setApplicationTradeAccountTypeName(String applicationTradeAccountTypeName) {
        this.applicationTradeAccountTypeName = applicationTradeAccountTypeName == null ? null : applicationTradeAccountTypeName.trim();
    }

    /**
     * 申请单号
     * @return the value of application_trade.application_trade_application_no
     * @mbg.generated
     */
    public String getApplicationTradeApplicationNo() {
        return applicationTradeApplicationNo;
    }

    /**
     * 申请单号
     * @param applicationTradeApplicationNo the value for application_trade.application_trade_application_no
     * @mbg.generated
     */
    public void setApplicationTradeApplicationNo(String applicationTradeApplicationNo) {
        this.applicationTradeApplicationNo = applicationTradeApplicationNo == null ? null : applicationTradeApplicationNo.trim();
    }

    /**
     * 申请人
     * @return the value of application_trade.application_trade_proposer
     * @mbg.generated
     */
    public String getApplicationTradeProposer() {
        return applicationTradeProposer;
    }

    /**
     * 申请人
     * @param applicationTradeProposer the value for application_trade.application_trade_proposer
     * @mbg.generated
     */
    public void setApplicationTradeProposer(String applicationTradeProposer) {
        this.applicationTradeProposer = applicationTradeProposer == null ? null : applicationTradeProposer.trim();
    }

    /**
     * 交易金额（元）
     * @return the value of application_trade.application_trade_tran_amt
     * @mbg.generated
     */
    public BigDecimal getApplicationTradeTranAmt() {
        return applicationTradeTranAmt;
    }

    /**
     * 交易金额（元）
     * @param applicationTradeTranAmt the value for application_trade.application_trade_tran_amt
     * @mbg.generated
     */
    public void setApplicationTradeTranAmt(BigDecimal applicationTradeTranAmt) {
        this.applicationTradeTranAmt = applicationTradeTranAmt;
    }

    /**
     * 业务单号
     * @return the value of application_trade.application_trade_business_no
     * @mbg.generated
     */
    public String getApplicationTradeBusinessNo() {
        return applicationTradeBusinessNo;
    }

    /**
     * 业务单号
     * @param applicationTradeBusinessNo the value for application_trade.application_trade_business_no
     * @mbg.generated
     */
    public void setApplicationTradeBusinessNo(String applicationTradeBusinessNo) {
        this.applicationTradeBusinessNo = applicationTradeBusinessNo == null ? null : applicationTradeBusinessNo.trim();
    }

    /**
     * 交易单号（判断是否重复支付）
     * @return the value of application_trade.application_trade_trasaction_no
     * @mbg.generated
     */
    public String getApplicationTradeTrasactionNo() {
        return applicationTradeTrasactionNo;
    }

    /**
     * 交易单号（判断是否重复支付）
     * @param applicationTradeTrasactionNo the value for application_trade.application_trade_trasaction_no
     * @mbg.generated
     */
    public void setApplicationTradeTrasactionNo(String applicationTradeTrasactionNo) {
        this.applicationTradeTrasactionNo = applicationTradeTrasactionNo == null ? null : applicationTradeTrasactionNo.trim();
    }

    /**
     * 业务类型
     * @return the value of application_trade.application_trade_business_type
     * @mbg.generated
     */
    public String getApplicationTradeBusinessType() {
        return applicationTradeBusinessType;
    }

    /**
     * 业务类型
     * @param applicationTradeBusinessType the value for application_trade.application_trade_business_type
     * @mbg.generated
     */
    public void setApplicationTradeBusinessType(String applicationTradeBusinessType) {
        this.applicationTradeBusinessType = applicationTradeBusinessType == null ? null : applicationTradeBusinessType.trim();
    }

    /**
     * 交易对象
     * @return the value of application_trade.application_trade_trasaction_object
     * @mbg.generated
     */
    public String getApplicationTradeTrasactionObject() {
        return applicationTradeTrasactionObject;
    }

    /**
     * 交易对象
     * @param applicationTradeTrasactionObject the value for application_trade.application_trade_trasaction_object
     * @mbg.generated
     */
    public void setApplicationTradeTrasactionObject(String applicationTradeTrasactionObject) {
        this.applicationTradeTrasactionObject = applicationTradeTrasactionObject == null ? null : applicationTradeTrasactionObject.trim();
    }

    /**
     * 交易来源
     * @return the value of application_trade.application_trade_trasaction_from
     * @mbg.generated
     */
    public String getApplicationTradeTrasactionFrom() {
        return applicationTradeTrasactionFrom;
    }

    /**
     * 交易来源
     * @param applicationTradeTrasactionFrom the value for application_trade.application_trade_trasaction_from
     * @mbg.generated
     */
    public void setApplicationTradeTrasactionFrom(String applicationTradeTrasactionFrom) {
        this.applicationTradeTrasactionFrom = applicationTradeTrasactionFrom == null ? null : applicationTradeTrasactionFrom.trim();
    }

    /**
     * 业务内容
     * @return the value of application_trade.application_trade_business_content
     * @mbg.generated
     */
    public String getApplicationTradeBusinessContent() {
        return applicationTradeBusinessContent;
    }

    /**
     * 业务内容
     * @param applicationTradeBusinessContent the value for application_trade.application_trade_business_content
     * @mbg.generated
     */
    public void setApplicationTradeBusinessContent(String applicationTradeBusinessContent) {
        this.applicationTradeBusinessContent = applicationTradeBusinessContent == null ? null : applicationTradeBusinessContent.trim();
    }

    /**
     * 清分类型
     * @return the value of application_trade.application_trade_clearing_type
     * @mbg.generated
     */
    public String getApplicationTradeClearingType() {
        return applicationTradeClearingType;
    }

    /**
     * 清分类型
     * @param applicationTradeClearingType the value for application_trade.application_trade_clearing_type
     * @mbg.generated
     */
    public void setApplicationTradeClearingType(String applicationTradeClearingType) {
        this.applicationTradeClearingType = applicationTradeClearingType == null ? null : applicationTradeClearingType.trim();
    }

    /**
     * 品名
     * @return the value of application_trade.application_trade_goods_name
     * @mbg.generated
     */
    public String getApplicationTradeGoodsName() {
        return applicationTradeGoodsName;
    }

    /**
     * 品名
     * @param applicationTradeGoodsName the value for application_trade.application_trade_goods_name
     * @mbg.generated
     */
    public void setApplicationTradeGoodsName(String applicationTradeGoodsName) {
        this.applicationTradeGoodsName = applicationTradeGoodsName == null ? null : applicationTradeGoodsName.trim();
    }

    /**
     * 业务
     * @return the value of application_trade.application_trade_remark
     * @mbg.generated
     */
    public String getApplicationTradeRemark() {
        return applicationTradeRemark;
    }

    /**
     * 业务
     * @param applicationTradeRemark the value for application_trade.application_trade_remark
     * @mbg.generated
     */
    public void setApplicationTradeRemark(String applicationTradeRemark) {
        this.applicationTradeRemark = applicationTradeRemark == null ? null : applicationTradeRemark.trim();
    }

    /**
     * 0、待初审 1、待复审 2、待终审 3、待付款 4、已完成 5、已否决 6、付款失败
     * @return the value of application_trade.application_trade_application_status
     * @mbg.generated
     */
    public Integer getApplicationTradeApplicationStatus() {
        return applicationTradeApplicationStatus;
    }

    /**
     * 0、待初审 1、待复审 2、待终审 3、待付款 4、已完成 5、已否决 6、付款失败
     * @param applicationTradeApplicationStatus the value for application_trade.application_trade_application_status
     * @mbg.generated
     */
    public void setApplicationTradeApplicationStatus(Integer applicationTradeApplicationStatus) {
        this.applicationTradeApplicationStatus = applicationTradeApplicationStatus;
    }

    /**
     * 审核原因
     * @return the value of application_trade.application_trade_reason
     * @mbg.generated
     */
    public String getApplicationTradeReason() {
        return applicationTradeReason;
    }

    /**
     * 审核原因
     * @param applicationTradeReason the value for application_trade.application_trade_reason
     * @mbg.generated
     */
    public void setApplicationTradeReason(String applicationTradeReason) {
        this.applicationTradeReason = applicationTradeReason == null ? null : applicationTradeReason.trim();
    }

    /**
     * 回调地址
     * @return the value of application_trade.application_trade_back_url
     * @mbg.generated
     */
    public String getApplicationTradeBackUrl() {
        return applicationTradeBackUrl;
    }

    /**
     * 回调地址
     * @param applicationTradeBackUrl the value for application_trade.application_trade_back_url
     * @mbg.generated
     */
    public void setApplicationTradeBackUrl(String applicationTradeBackUrl) {
        this.applicationTradeBackUrl = applicationTradeBackUrl == null ? null : applicationTradeBackUrl.trim();
    }

    /**
     * 转入子账户id（收款方）
     * @return the value of application_trade.application_trade_in_account_sub_id
     * @mbg.generated
     */
    public String getApplicationTradeInAccountSubId() {
        return applicationTradeInAccountSubId;
    }

    /**
     * 转入子账户id（收款方）
     * @param applicationTradeInAccountSubId the value for application_trade.application_trade_in_account_sub_id
     * @mbg.generated
     */
    public void setApplicationTradeInAccountSubId(String applicationTradeInAccountSubId) {
        this.applicationTradeInAccountSubId = applicationTradeInAccountSubId == null ? null : applicationTradeInAccountSubId.trim();
    }

    /**
     * 创建人
     * @return the value of application_trade.application_trade_create_user_id
     * @mbg.generated
     */
    public String getApplicationTradeCreateUserId() {
        return applicationTradeCreateUserId;
    }

    /**
     * 创建人
     * @param applicationTradeCreateUserId the value for application_trade.application_trade_create_user_id
     * @mbg.generated
     */
    public void setApplicationTradeCreateUserId(String applicationTradeCreateUserId) {
        this.applicationTradeCreateUserId = applicationTradeCreateUserId == null ? null : applicationTradeCreateUserId.trim();
    }

    /**
     * 创建时间（交易时间）
     * @return the value of application_trade.application_trade_create_time
     * @mbg.generated
     */
    public Date getApplicationTradeCreateTime() {
        return applicationTradeCreateTime;
    }

    /**
     * 创建时间（交易时间）
     * @param applicationTradeCreateTime the value for application_trade.application_trade_create_time
     * @mbg.generated
     */
    public void setApplicationTradeCreateTime(Date applicationTradeCreateTime) {
        this.applicationTradeCreateTime = applicationTradeCreateTime;
    }

    /**
     * 修改人
     * @return the value of application_trade.application_trade_update_user_id
     * @mbg.generated
     */
    public String getApplicationTradeUpdateUserId() {
        return applicationTradeUpdateUserId;
    }

    /**
     * 修改人
     * @param applicationTradeUpdateUserId the value for application_trade.application_trade_update_user_id
     * @mbg.generated
     */
    public void setApplicationTradeUpdateUserId(String applicationTradeUpdateUserId) {
        this.applicationTradeUpdateUserId = applicationTradeUpdateUserId == null ? null : applicationTradeUpdateUserId.trim();
    }

    /**
     * 修改时间
     * @return the value of application_trade.application_trade_update_time
     * @mbg.generated
     */
    public Date getApplicationTradeUpdateTime() {
        return applicationTradeUpdateTime;
    }

    /**
     * 修改时间
     * @param applicationTradeUpdateTime the value for application_trade.application_trade_update_time
     * @mbg.generated
     */
    public void setApplicationTradeUpdateTime(Date applicationTradeUpdateTime) {
        this.applicationTradeUpdateTime = applicationTradeUpdateTime;
    }

    /**
     * 是否删除 0-否,1-是
     * @return the value of application_trade.application_trade_is_delete
     * @mbg.generated
     */
    public Boolean getApplicationTradeIsDelete() {
        return applicationTradeIsDelete;
    }

    /**
     * 是否删除 0-否,1-是
     * @param applicationTradeIsDelete the value for application_trade.application_trade_is_delete
     * @mbg.generated
     */
    public void setApplicationTradeIsDelete(Boolean applicationTradeIsDelete) {
        this.applicationTradeIsDelete = applicationTradeIsDelete;
    }

    /**
     * 保留域
     * @return the value of application_trade.application_trade_reserved_msg
     * @mbg.generated
     */
    public String getApplicationTradeReservedMsg() {
        return applicationTradeReservedMsg;
    }

    /**
     * 保留域
     * @param applicationTradeReservedMsg the value for application_trade.application_trade_reserved_msg
     * @mbg.generated
     */
    public void setApplicationTradeReservedMsg(String applicationTradeReservedMsg) {
        this.applicationTradeReservedMsg = applicationTradeReservedMsg == null ? null : applicationTradeReservedMsg.trim();
    }
}