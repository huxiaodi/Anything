package com.dllg.ebpay.model.generator;

import java.util.Date;

/**
 *   子账号绑定关系表
 *   account_sub_bind
 * @mbg.generated do_not_delete_during_merge
 */
public class AccountSubBind {
    /**
     *   id
     *   account_sub_bind.account_sub_bind_id
     * @mbg.generated
     */
    private String accountSubBindId;

    /**
     *   子账户id
     *   account_sub_bind.account_sub_bind_account_sub_id
     * @mbg.generated
     */
    private String accountSubBindAccountSubId;

    /**
     *   关联id
     *   account_sub_bind.account_sub_bind_relation_id
     * @mbg.generated
     */
    private String accountSubBindRelationId;

    /**
     *   绑定来源
     *   account_sub_bind.account_sub_bind_from
     * @mbg.generated
     */
    private String accountSubBindFrom;

    /**
     *   创建时间
     *   account_sub_bind.account_sub_bind_create_time
     * @mbg.generated
     */
    private Date accountSubBindCreateTime;

    /**
     *   修改时间
     *   account_sub_bind.account_sub_bind_update_time
     * @mbg.generated
     */
    private Date accountSubBindUpdateTime;

    /**
     *   状态,1-绑定成功，2-已解绑，3-绑定失败
     *   account_sub_bind.account_sub_bind_status
     * @mbg.generated
     */
    private Integer accountSubBindStatus;

    /**
     * id
     * @return the value of account_sub_bind.account_sub_bind_id
     * @mbg.generated
     */
    public String getAccountSubBindId() {
        return accountSubBindId;
    }

    /**
     * id
     * @param accountSubBindId the value for account_sub_bind.account_sub_bind_id
     * @mbg.generated
     */
    public void setAccountSubBindId(String accountSubBindId) {
        this.accountSubBindId = accountSubBindId == null ? null : accountSubBindId.trim();
    }

    /**
     * 子账户id
     * @return the value of account_sub_bind.account_sub_bind_account_sub_id
     * @mbg.generated
     */
    public String getAccountSubBindAccountSubId() {
        return accountSubBindAccountSubId;
    }

    /**
     * 子账户id
     * @param accountSubBindAccountSubId the value for account_sub_bind.account_sub_bind_account_sub_id
     * @mbg.generated
     */
    public void setAccountSubBindAccountSubId(String accountSubBindAccountSubId) {
        this.accountSubBindAccountSubId = accountSubBindAccountSubId == null ? null : accountSubBindAccountSubId.trim();
    }

    /**
     * 关联id
     * @return the value of account_sub_bind.account_sub_bind_relation_id
     * @mbg.generated
     */
    public String getAccountSubBindRelationId() {
        return accountSubBindRelationId;
    }

    /**
     * 关联id
     * @param accountSubBindRelationId the value for account_sub_bind.account_sub_bind_relation_id
     * @mbg.generated
     */
    public void setAccountSubBindRelationId(String accountSubBindRelationId) {
        this.accountSubBindRelationId = accountSubBindRelationId == null ? null : accountSubBindRelationId.trim();
    }

    /**
     * 绑定来源
     * @return the value of account_sub_bind.account_sub_bind_from
     * @mbg.generated
     */
    public String getAccountSubBindFrom() {
        return accountSubBindFrom;
    }

    /**
     * 绑定来源
     * @param accountSubBindFrom the value for account_sub_bind.account_sub_bind_from
     * @mbg.generated
     */
    public void setAccountSubBindFrom(String accountSubBindFrom) {
        this.accountSubBindFrom = accountSubBindFrom == null ? null : accountSubBindFrom.trim();
    }

    /**
     * 创建时间
     * @return the value of account_sub_bind.account_sub_bind_create_time
     * @mbg.generated
     */
    public Date getAccountSubBindCreateTime() {
        return accountSubBindCreateTime;
    }

    /**
     * 创建时间
     * @param accountSubBindCreateTime the value for account_sub_bind.account_sub_bind_create_time
     * @mbg.generated
     */
    public void setAccountSubBindCreateTime(Date accountSubBindCreateTime) {
        this.accountSubBindCreateTime = accountSubBindCreateTime;
    }

    /**
     * 修改时间
     * @return the value of account_sub_bind.account_sub_bind_update_time
     * @mbg.generated
     */
    public Date getAccountSubBindUpdateTime() {
        return accountSubBindUpdateTime;
    }

    /**
     * 修改时间
     * @param accountSubBindUpdateTime the value for account_sub_bind.account_sub_bind_update_time
     * @mbg.generated
     */
    public void setAccountSubBindUpdateTime(Date accountSubBindUpdateTime) {
        this.accountSubBindUpdateTime = accountSubBindUpdateTime;
    }

    /**
     * 状态,1-绑定成功，2-已解绑，3-绑定失败
     * @return the value of account_sub_bind.account_sub_bind_status
     * @mbg.generated
     */
    public Integer getAccountSubBindStatus() {
        return accountSubBindStatus;
    }

    /**
     * 状态,1-绑定成功，2-已解绑，3-绑定失败
     * @param accountSubBindStatus the value for account_sub_bind.account_sub_bind_status
     * @mbg.generated
     */
    public void setAccountSubBindStatus(Integer accountSubBindStatus) {
        this.accountSubBindStatus = accountSubBindStatus;
    }
}