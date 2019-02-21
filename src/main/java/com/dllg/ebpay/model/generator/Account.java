package com.dllg.ebpay.model.generator;

import java.math.BigDecimal;
import java.util.Date;

/**
 *   账户
 *   account
 * @mbg.generated do_not_delete_during_merge
 */
public class Account {
    /**
     *   id
     *   account.account_id
     * @mbg.generated
     */
    private String accountId;

    /**
     *   总账户类型，1-平安银行
     *   account.account_type
     * @mbg.generated
     */
    private Integer accountType;

    /**
     *   账户名称
     *   account.account_name
     * @mbg.generated
     */
    private String accountName;

    /**
     *   资金汇总账号
     *   account.account_fund_summary_account_no
     * @mbg.generated
     */
    private String accountFundSummaryAccountNo;

    /**
     *   上日余额（前一天的余额）
     *   account.account_last_balance
     * @mbg.generated
     */
    private BigDecimal accountLastBalance;

    /**
     *   当前余额
     *   account.account_cur_balabce
     * @mbg.generated
     */
    private BigDecimal accountCurBalabce;

    /**
     *   状态，1-正常
     *   account.account_status
     * @mbg.generated
     */
    private Integer accountStatus;

    /**
     *   创建人
     *   account.account_create_user_id
     * @mbg.generated
     */
    private String accountCreateUserId;

    /**
     *   创建时间
     *   account.account_create_time
     * @mbg.generated
     */
    private Date accountCreateTime;

    /**
     *   修改人
     *   account.account_update_user_id
     * @mbg.generated
     */
    private String accountUpdateUserId;

    /**
     *   修改时间
     *   account.account_update_time
     * @mbg.generated
     */
    private Date accountUpdateTime;

    /**
     *   是否删除 0-否,1-是
     *   account.account_is_delete
     * @mbg.generated
     */
    private Boolean accountIsDelete;

    /**
     * id
     * @return the value of account.account_id
     * @mbg.generated
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     * id
     * @param accountId the value for account.account_id
     * @mbg.generated
     */
    public void setAccountId(String accountId) {
        this.accountId = accountId == null ? null : accountId.trim();
    }

    /**
     * 总账户类型，1-平安银行
     * @return the value of account.account_type
     * @mbg.generated
     */
    public Integer getAccountType() {
        return accountType;
    }

    /**
     * 总账户类型，1-平安银行
     * @param accountType the value for account.account_type
     * @mbg.generated
     */
    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    /**
     * 账户名称
     * @return the value of account.account_name
     * @mbg.generated
     */
    public String getAccountName() {
        return accountName;
    }

    /**
     * 账户名称
     * @param accountName the value for account.account_name
     * @mbg.generated
     */
    public void setAccountName(String accountName) {
        this.accountName = accountName == null ? null : accountName.trim();
    }

    /**
     * 资金汇总账号
     * @return the value of account.account_fund_summary_account_no
     * @mbg.generated
     */
    public String getAccountFundSummaryAccountNo() {
        return accountFundSummaryAccountNo;
    }

    /**
     * 资金汇总账号
     * @param accountFundSummaryAccountNo the value for account.account_fund_summary_account_no
     * @mbg.generated
     */
    public void setAccountFundSummaryAccountNo(String accountFundSummaryAccountNo) {
        this.accountFundSummaryAccountNo = accountFundSummaryAccountNo == null ? null : accountFundSummaryAccountNo.trim();
    }

    /**
     * 上日余额（前一天的余额）
     * @return the value of account.account_last_balance
     * @mbg.generated
     */
    public BigDecimal getAccountLastBalance() {
        return accountLastBalance;
    }

    /**
     * 上日余额（前一天的余额）
     * @param accountLastBalance the value for account.account_last_balance
     * @mbg.generated
     */
    public void setAccountLastBalance(BigDecimal accountLastBalance) {
        this.accountLastBalance = accountLastBalance;
    }

    /**
     * 当前余额
     * @return the value of account.account_cur_balabce
     * @mbg.generated
     */
    public BigDecimal getAccountCurBalabce() {
        return accountCurBalabce;
    }

    /**
     * 当前余额
     * @param accountCurBalabce the value for account.account_cur_balabce
     * @mbg.generated
     */
    public void setAccountCurBalabce(BigDecimal accountCurBalabce) {
        this.accountCurBalabce = accountCurBalabce;
    }

    /**
     * 状态，1-正常
     * @return the value of account.account_status
     * @mbg.generated
     */
    public Integer getAccountStatus() {
        return accountStatus;
    }

    /**
     * 状态，1-正常
     * @param accountStatus the value for account.account_status
     * @mbg.generated
     */
    public void setAccountStatus(Integer accountStatus) {
        this.accountStatus = accountStatus;
    }

    /**
     * 创建人
     * @return the value of account.account_create_user_id
     * @mbg.generated
     */
    public String getAccountCreateUserId() {
        return accountCreateUserId;
    }

    /**
     * 创建人
     * @param accountCreateUserId the value for account.account_create_user_id
     * @mbg.generated
     */
    public void setAccountCreateUserId(String accountCreateUserId) {
        this.accountCreateUserId = accountCreateUserId == null ? null : accountCreateUserId.trim();
    }

    /**
     * 创建时间
     * @return the value of account.account_create_time
     * @mbg.generated
     */
    public Date getAccountCreateTime() {
        return accountCreateTime;
    }

    /**
     * 创建时间
     * @param accountCreateTime the value for account.account_create_time
     * @mbg.generated
     */
    public void setAccountCreateTime(Date accountCreateTime) {
        this.accountCreateTime = accountCreateTime;
    }

    /**
     * 修改人
     * @return the value of account.account_update_user_id
     * @mbg.generated
     */
    public String getAccountUpdateUserId() {
        return accountUpdateUserId;
    }

    /**
     * 修改人
     * @param accountUpdateUserId the value for account.account_update_user_id
     * @mbg.generated
     */
    public void setAccountUpdateUserId(String accountUpdateUserId) {
        this.accountUpdateUserId = accountUpdateUserId == null ? null : accountUpdateUserId.trim();
    }

    /**
     * 修改时间
     * @return the value of account.account_update_time
     * @mbg.generated
     */
    public Date getAccountUpdateTime() {
        return accountUpdateTime;
    }

    /**
     * 修改时间
     * @param accountUpdateTime the value for account.account_update_time
     * @mbg.generated
     */
    public void setAccountUpdateTime(Date accountUpdateTime) {
        this.accountUpdateTime = accountUpdateTime;
    }

    /**
     * 是否删除 0-否,1-是
     * @return the value of account.account_is_delete
     * @mbg.generated
     */
    public Boolean getAccountIsDelete() {
        return accountIsDelete;
    }

    /**
     * 是否删除 0-否,1-是
     * @param accountIsDelete the value for account.account_is_delete
     * @mbg.generated
     */
    public void setAccountIsDelete(Boolean accountIsDelete) {
        this.accountIsDelete = accountIsDelete;
    }
}