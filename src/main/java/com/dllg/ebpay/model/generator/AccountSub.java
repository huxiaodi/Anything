package com.dllg.ebpay.model.generator;

import java.math.BigDecimal;
import java.util.Date;

/**
 *   子账户
 *   account_sub
 * @mbg.generated do_not_delete_during_merge
 */
public class AccountSub {
    /**
     *   子账户id
     *   account_sub.account_sub_id
     * @mbg.generated
     */
    private String accountSubId;

    /**
     *   总账户id
     *   account_sub.account_sub_account_id
     * @mbg.generated
     */
    private String accountSubAccountId;

    /**
     *   关系id，企业是company_id，个人是user_id
     *   account_sub.account_sub_relation_id
     * @mbg.generated
     */
    private String accountSubRelationId;

    /**
     *   关系id类型，1-个人，2-企业
     *   account_sub.account_sub_relation_id_type
     * @mbg.generated
     */
    private Integer accountSubRelationIdType;

    /**
     *   账户名称
     *   account_sub.account_sub_name
     * @mbg.generated
     */
    private String accountSubName;

    /**
     *   账号号码
     *   account_sub.account_sub_no
     * @mbg.generated
     */
    private String accountSubNo;

    /**
     *   子帐号类型 1: 普通会员子账号 2: 挂账子账号  3: 手续费子账号 4: 利息子账号 5: 平台担保子账号 7:在途 8:理财购买子帐号 9:理财赎回子帐号 10:平台子拥有结算子帐号
     *   account_sub.account_sub_type
     * @mbg.generated
     */
    private Integer accountSubType;

    /**
     *   子账号可用余额
     *   account_sub.account_sub_avail_balance
     * @mbg.generated
     */
    private BigDecimal accountSubAvailBalance;

    /**
     *   子账号可提现金额
     *   account_sub.account_sub_ash_balance
     * @mbg.generated
     */
    private BigDecimal accountSubAshBalance;

    /**
     *   子账户冻结金额（指在担保子账户里冻结的金额）
     *   account_sub.account_sub_freeze_amt
     * @mbg.generated
     */
    private BigDecimal accountSubFreezeAmt;

    /**
     *   开户时间
     *   account_sub.account_sub_account_opening_date
     * @mbg.generated
     */
    private Date accountSubAccountOpeningDate;

    /**
     *   维护日期
     *   account_sub.account_sub_maintenance_date
     * @mbg.generated
     */
    private Date accountSubMaintenanceDate;

    /**
     *   是否启用，0；否 1：是
     *   account_sub.account_sub_status
     * @mbg.generated
     */
    private Integer accountSubStatus;

    /**
     *   对账日期
     *   account_sub.account_sub_check_date
     * @mbg.generated
     */
    private Date accountSubCheckDate;

    /**
     *   对账状态
     *   account_sub.account_sub_check_status
     * @mbg.generated
     */
    private Integer accountSubCheckStatus;

    /**
     *   对账返回信息
     *   account_sub.account_sub_reconcile_return_msg
     * @mbg.generated
     */
    private String accountSubReconcileReturnMsg;

    /**
     *   清算状态，0：成功，1：失败，2：异常 3:待处理
     *   account_sub.account_sub_clearing_status
     * @mbg.generated
     */
    private Integer accountSubClearingStatus;

    /**
     *   清算返回信息
     *   account_sub.account_sub_clearing_return_msg
     * @mbg.generated
     */
    private String accountSubClearingReturnMsg;

    /**
     *   待转可提现状态，0：待转，1：已转，2：无需转，3：异常
     *   account_sub.account_sub_cash_status
     * @mbg.generated
     */
    private Integer accountSubCashStatus;

    /**
     *   待转可提现状态查询时间
     *   account_sub.account_sub_cash_status_query_time
     * @mbg.generated
     */
    private BigDecimal accountSubCashStatusQueryTime;

    /**
     *   日终可用余额
     *   account_sub.account_sub_day_avail_balance
     * @mbg.generated
     */
    private BigDecimal accountSubDayAvailBalance;

    /**
     *   日终可提现余额
     *   account_sub.account_sub_day_cash_balance
     * @mbg.generated
     */
    private BigDecimal accountSubDayCashBalance;

    /**
     *   日终冻结余额
     *   account_sub.account_sub_day_freeze_balance
     * @mbg.generated
     */
    private BigDecimal accountSubDayFreezeBalance;

    /**
     *   当日待转可提现发生额
     *   account_sub.account_sub_day_cash_occur_amt
     * @mbg.generated
     */
    private BigDecimal accountSubDayCashOccurAmt;

    /**
     *   日终待转可提现余额
     *   account_sub.account_sub_day_wait_cash_balance
     * @mbg.generated
     */
    private BigDecimal accountSubDayWaitCashBalance;

    /**
     *   会员属性，00-普通子账号，SH-商户子账户，（暂时只支持00-普通子账号改为SH-商户子账户）
     *   account_sub.account_sub_member_property
     * @mbg.generated
     */
    private String accountSubMemberProperty;

    /**
     *   会员资金冻结，1：冻结（会员→担保），2：解冻（担保→会员），3：清分+冻结，4：见证+收单的冻结资金解冻
     *   account_sub.account_sub_tranche_freeze
     * @mbg.generated
     */
    private Integer accountSubTrancheFreeze;

    /**
     *   支付密码状态，0-未设置，1-已设置
     *   account_sub.account_sub_pwd_status
     * @mbg.generated
     */
    private Integer accountSubPwdStatus;

    /**
     *   创建人
     *   account_sub.account_sub_create_user_id
     * @mbg.generated
     */
    private String accountSubCreateUserId;

    /**
     *   创建时间
     *   account_sub.account_sub_create_time
     * @mbg.generated
     */
    private Date accountSubCreateTime;

    /**
     *   修改人
     *   account_sub.account_sub_update_user_id
     * @mbg.generated
     */
    private String accountSubUpdateUserId;

    /**
     *   修改时间
     *   account_sub.account_sub_update_time
     * @mbg.generated
     */
    private Date accountSubUpdateTime;

    /**
     *   是否删除 0-否,1-是
     *   account_sub.account_sub_is_delete
     * @mbg.generated
     */
    private Boolean accountSubIsDelete;

    /**
     *   平安设置支付密码流水号
     *   account_sub.account_sub_order_id
     * @mbg.generated
     */
    private String accountSubOrderId;

    /**
     *   是否显示账户余额 默认0显示  1不显示
     *   account_sub.account_money_state
     * @mbg.generated
     */
    private String accountMoneyState;


    private String accountTransactionPhone;

    /**
     * 子账户id
     * @return the value of account_sub.account_sub_id
     * @mbg.generated
     */
    public String getAccountSubId() {
        return accountSubId;
    }

    /**
     * 子账户id
     * @param accountSubId the value for account_sub.account_sub_id
     * @mbg.generated
     */
    public void setAccountSubId(String accountSubId) {
        this.accountSubId = accountSubId == null ? null : accountSubId.trim();
    }

    /**
     * 总账户id
     * @return the value of account_sub.account_sub_account_id
     * @mbg.generated
     */
    public String getAccountSubAccountId() {
        return accountSubAccountId;
    }

    /**
     * 总账户id
     * @param accountSubAccountId the value for account_sub.account_sub_account_id
     * @mbg.generated
     */
    public void setAccountSubAccountId(String accountSubAccountId) {
        this.accountSubAccountId = accountSubAccountId == null ? null : accountSubAccountId.trim();
    }

    /**
     * 关系id，企业是company_id，个人是user_id
     * @return the value of account_sub.account_sub_relation_id
     * @mbg.generated
     */
    public String getAccountSubRelationId() {
        return accountSubRelationId;
    }

    /**
     * 关系id，企业是company_id，个人是user_id
     * @param accountSubRelationId the value for account_sub.account_sub_relation_id
     * @mbg.generated
     */
    public void setAccountSubRelationId(String accountSubRelationId) {
        this.accountSubRelationId = accountSubRelationId == null ? null : accountSubRelationId.trim();
    }

    /**
     * 关系id类型，1-个人，2-企业
     * @return the value of account_sub.account_sub_relation_id_type
     * @mbg.generated
     */
    public Integer getAccountSubRelationIdType() {
        return accountSubRelationIdType;
    }

    /**
     * 关系id类型，1-个人，2-企业
     * @param accountSubRelationIdType the value for account_sub.account_sub_relation_id_type
     * @mbg.generated
     */
    public void setAccountSubRelationIdType(Integer accountSubRelationIdType) {
        this.accountSubRelationIdType = accountSubRelationIdType;
    }

    /**
     * 账户名称
     * @return the value of account_sub.account_sub_name
     * @mbg.generated
     */
    public String getAccountSubName() {
        return accountSubName;
    }

    /**
     * 账户名称
     * @param accountSubName the value for account_sub.account_sub_name
     * @mbg.generated
     */
    public void setAccountSubName(String accountSubName) {
        this.accountSubName = accountSubName == null ? null : accountSubName.trim();
    }

    /**
     * 账号号码
     * @return the value of account_sub.account_sub_no
     * @mbg.generated
     */
    public String getAccountSubNo() {
        return accountSubNo;
    }

    /**
     * 账号号码
     * @param accountSubNo the value for account_sub.account_sub_no
     * @mbg.generated
     */
    public void setAccountSubNo(String accountSubNo) {
        this.accountSubNo = accountSubNo == null ? null : accountSubNo.trim();
    }

    /**
     * 子帐号类型 1: 普通会员子账号 2: 挂账子账号  3: 手续费子账号 4: 利息子账号 5: 平台担保子账号 7:在途 8:理财购买子帐号 9:理财赎回子帐号 10:平台子拥有结算子帐号
     * @return the value of account_sub.account_sub_type
     * @mbg.generated
     */
    public Integer getAccountSubType() {
        return accountSubType;
    }

    /**
     * 子帐号类型 1: 普通会员子账号 2: 挂账子账号  3: 手续费子账号 4: 利息子账号 5: 平台担保子账号 7:在途 8:理财购买子帐号 9:理财赎回子帐号 10:平台子拥有结算子帐号
     * @param accountSubType the value for account_sub.account_sub_type
     * @mbg.generated
     */
    public void setAccountSubType(Integer accountSubType) {
        this.accountSubType = accountSubType;
    }

    /**
     * 子账号可用余额
     * @return the value of account_sub.account_sub_avail_balance
     * @mbg.generated
     */
    public BigDecimal getAccountSubAvailBalance() {
        return accountSubAvailBalance;
    }

    /**
     * 子账号可用余额
     * @param accountSubAvailBalance the value for account_sub.account_sub_avail_balance
     * @mbg.generated
     */
    public void setAccountSubAvailBalance(BigDecimal accountSubAvailBalance) {
        this.accountSubAvailBalance = accountSubAvailBalance;
    }

    /**
     * 子账号可提现金额
     * @return the value of account_sub.account_sub_ash_balance
     * @mbg.generated
     */
    public BigDecimal getAccountSubAshBalance() {
        return accountSubAshBalance;
    }

    /**
     * 子账号可提现金额
     * @param accountSubAshBalance the value for account_sub.account_sub_ash_balance
     * @mbg.generated
     */
    public void setAccountSubAshBalance(BigDecimal accountSubAshBalance) {
        this.accountSubAshBalance = accountSubAshBalance;
    }

    /**
     * 子账户冻结金额（指在担保子账户里冻结的金额）
     * @return the value of account_sub.account_sub_freeze_amt
     * @mbg.generated
     */
    public BigDecimal getAccountSubFreezeAmt() {
        return accountSubFreezeAmt;
    }

    /**
     * 子账户冻结金额（指在担保子账户里冻结的金额）
     * @param accountSubFreezeAmt the value for account_sub.account_sub_freeze_amt
     * @mbg.generated
     */
    public void setAccountSubFreezeAmt(BigDecimal accountSubFreezeAmt) {
        this.accountSubFreezeAmt = accountSubFreezeAmt;
    }

    /**
     * 开户时间
     * @return the value of account_sub.account_sub_account_opening_date
     * @mbg.generated
     */
    public Date getAccountSubAccountOpeningDate() {
        return accountSubAccountOpeningDate;
    }

    /**
     * 开户时间
     * @param accountSubAccountOpeningDate the value for account_sub.account_sub_account_opening_date
     * @mbg.generated
     */
    public void setAccountSubAccountOpeningDate(Date accountSubAccountOpeningDate) {
        this.accountSubAccountOpeningDate = accountSubAccountOpeningDate;
    }

    /**
     * 维护日期
     * @return the value of account_sub.account_sub_maintenance_date
     * @mbg.generated
     */
    public Date getAccountSubMaintenanceDate() {
        return accountSubMaintenanceDate;
    }

    /**
     * 维护日期
     * @param accountSubMaintenanceDate the value for account_sub.account_sub_maintenance_date
     * @mbg.generated
     */
    public void setAccountSubMaintenanceDate(Date accountSubMaintenanceDate) {
        this.accountSubMaintenanceDate = accountSubMaintenanceDate;
    }

    /**
     * 是否启用，0；否 1：是
     * @return the value of account_sub.account_sub_status
     * @mbg.generated
     */
    public Integer getAccountSubStatus() {
        return accountSubStatus;
    }

    /**
     * 是否启用，0；否 1：是
     * @param accountSubStatus the value for account_sub.account_sub_status
     * @mbg.generated
     */
    public void setAccountSubStatus(Integer accountSubStatus) {
        this.accountSubStatus = accountSubStatus;
    }

    /**
     * 对账日期
     * @return the value of account_sub.account_sub_check_date
     * @mbg.generated
     */
    public Date getAccountSubCheckDate() {
        return accountSubCheckDate;
    }

    /**
     * 对账日期
     * @param accountSubCheckDate the value for account_sub.account_sub_check_date
     * @mbg.generated
     */
    public void setAccountSubCheckDate(Date accountSubCheckDate) {
        this.accountSubCheckDate = accountSubCheckDate;
    }

    /**
     * 对账状态
     * @return the value of account_sub.account_sub_check_status
     * @mbg.generated
     */
    public Integer getAccountSubCheckStatus() {
        return accountSubCheckStatus;
    }

    /**
     * 对账状态
     * @param accountSubCheckStatus the value for account_sub.account_sub_check_status
     * @mbg.generated
     */
    public void setAccountSubCheckStatus(Integer accountSubCheckStatus) {
        this.accountSubCheckStatus = accountSubCheckStatus;
    }

    /**
     * 对账返回信息
     * @return the value of account_sub.account_sub_reconcile_return_msg
     * @mbg.generated
     */
    public String getAccountSubReconcileReturnMsg() {
        return accountSubReconcileReturnMsg;
    }

    /**
     * 对账返回信息
     * @param accountSubReconcileReturnMsg the value for account_sub.account_sub_reconcile_return_msg
     * @mbg.generated
     */
    public void setAccountSubReconcileReturnMsg(String accountSubReconcileReturnMsg) {
        this.accountSubReconcileReturnMsg = accountSubReconcileReturnMsg == null ? null : accountSubReconcileReturnMsg.trim();
    }

    /**
     * 清算状态，0：成功，1：失败，2：异常 3:待处理
     * @return the value of account_sub.account_sub_clearing_status
     * @mbg.generated
     */
    public Integer getAccountSubClearingStatus() {
        return accountSubClearingStatus;
    }

    /**
     * 清算状态，0：成功，1：失败，2：异常 3:待处理
     * @param accountSubClearingStatus the value for account_sub.account_sub_clearing_status
     * @mbg.generated
     */
    public void setAccountSubClearingStatus(Integer accountSubClearingStatus) {
        this.accountSubClearingStatus = accountSubClearingStatus;
    }

    /**
     * 清算返回信息
     * @return the value of account_sub.account_sub_clearing_return_msg
     * @mbg.generated
     */
    public String getAccountSubClearingReturnMsg() {
        return accountSubClearingReturnMsg;
    }

    /**
     * 清算返回信息
     * @param accountSubClearingReturnMsg the value for account_sub.account_sub_clearing_return_msg
     * @mbg.generated
     */
    public void setAccountSubClearingReturnMsg(String accountSubClearingReturnMsg) {
        this.accountSubClearingReturnMsg = accountSubClearingReturnMsg == null ? null : accountSubClearingReturnMsg.trim();
    }

    /**
     * 待转可提现状态，0：待转，1：已转，2：无需转，3：异常
     * @return the value of account_sub.account_sub_cash_status
     * @mbg.generated
     */
    public Integer getAccountSubCashStatus() {
        return accountSubCashStatus;
    }

    /**
     * 待转可提现状态，0：待转，1：已转，2：无需转，3：异常
     * @param accountSubCashStatus the value for account_sub.account_sub_cash_status
     * @mbg.generated
     */
    public void setAccountSubCashStatus(Integer accountSubCashStatus) {
        this.accountSubCashStatus = accountSubCashStatus;
    }

    /**
     * 待转可提现状态查询时间
     * @return the value of account_sub.account_sub_cash_status_query_time
     * @mbg.generated
     */
    public BigDecimal getAccountSubCashStatusQueryTime() {
        return accountSubCashStatusQueryTime;
    }

    /**
     * 待转可提现状态查询时间
     * @param accountSubCashStatusQueryTime the value for account_sub.account_sub_cash_status_query_time
     * @mbg.generated
     */
    public void setAccountSubCashStatusQueryTime(BigDecimal accountSubCashStatusQueryTime) {
        this.accountSubCashStatusQueryTime = accountSubCashStatusQueryTime;
    }

    /**
     * 日终可用余额
     * @return the value of account_sub.account_sub_day_avail_balance
     * @mbg.generated
     */
    public BigDecimal getAccountSubDayAvailBalance() {
        return accountSubDayAvailBalance;
    }

    /**
     * 日终可用余额
     * @param accountSubDayAvailBalance the value for account_sub.account_sub_day_avail_balance
     * @mbg.generated
     */
    public void setAccountSubDayAvailBalance(BigDecimal accountSubDayAvailBalance) {
        this.accountSubDayAvailBalance = accountSubDayAvailBalance;
    }

    /**
     * 日终可提现余额
     * @return the value of account_sub.account_sub_day_cash_balance
     * @mbg.generated
     */
    public BigDecimal getAccountSubDayCashBalance() {
        return accountSubDayCashBalance;
    }

    /**
     * 日终可提现余额
     * @param accountSubDayCashBalance the value for account_sub.account_sub_day_cash_balance
     * @mbg.generated
     */
    public void setAccountSubDayCashBalance(BigDecimal accountSubDayCashBalance) {
        this.accountSubDayCashBalance = accountSubDayCashBalance;
    }

    /**
     * 日终冻结余额
     * @return the value of account_sub.account_sub_day_freeze_balance
     * @mbg.generated
     */
    public BigDecimal getAccountSubDayFreezeBalance() {
        return accountSubDayFreezeBalance;
    }

    /**
     * 日终冻结余额
     * @param accountSubDayFreezeBalance the value for account_sub.account_sub_day_freeze_balance
     * @mbg.generated
     */
    public void setAccountSubDayFreezeBalance(BigDecimal accountSubDayFreezeBalance) {
        this.accountSubDayFreezeBalance = accountSubDayFreezeBalance;
    }

    /**
     * 当日待转可提现发生额
     * @return the value of account_sub.account_sub_day_cash_occur_amt
     * @mbg.generated
     */
    public BigDecimal getAccountSubDayCashOccurAmt() {
        return accountSubDayCashOccurAmt;
    }

    /**
     * 当日待转可提现发生额
     * @param accountSubDayCashOccurAmt the value for account_sub.account_sub_day_cash_occur_amt
     * @mbg.generated
     */
    public void setAccountSubDayCashOccurAmt(BigDecimal accountSubDayCashOccurAmt) {
        this.accountSubDayCashOccurAmt = accountSubDayCashOccurAmt;
    }

    /**
     * 日终待转可提现余额
     * @return the value of account_sub.account_sub_day_wait_cash_balance
     * @mbg.generated
     */
    public BigDecimal getAccountSubDayWaitCashBalance() {
        return accountSubDayWaitCashBalance;
    }

    /**
     * 日终待转可提现余额
     * @param accountSubDayWaitCashBalance the value for account_sub.account_sub_day_wait_cash_balance
     * @mbg.generated
     */
    public void setAccountSubDayWaitCashBalance(BigDecimal accountSubDayWaitCashBalance) {
        this.accountSubDayWaitCashBalance = accountSubDayWaitCashBalance;
    }

    /**
     * 会员属性，00-普通子账号，SH-商户子账户，（暂时只支持00-普通子账号改为SH-商户子账户）
     * @return the value of account_sub.account_sub_member_property
     * @mbg.generated
     */
    public String getAccountSubMemberProperty() {
        return accountSubMemberProperty;
    }

    /**
     * 会员属性，00-普通子账号，SH-商户子账户，（暂时只支持00-普通子账号改为SH-商户子账户）
     * @param accountSubMemberProperty the value for account_sub.account_sub_member_property
     * @mbg.generated
     */
    public void setAccountSubMemberProperty(String accountSubMemberProperty) {
        this.accountSubMemberProperty = accountSubMemberProperty == null ? null : accountSubMemberProperty.trim();
    }

    /**
     * 会员资金冻结，1：冻结（会员→担保），2：解冻（担保→会员），3：清分+冻结，4：见证+收单的冻结资金解冻
     * @return the value of account_sub.account_sub_tranche_freeze
     * @mbg.generated
     */
    public Integer getAccountSubTrancheFreeze() {
        return accountSubTrancheFreeze;
    }

    /**
     * 会员资金冻结，1：冻结（会员→担保），2：解冻（担保→会员），3：清分+冻结，4：见证+收单的冻结资金解冻
     * @param accountSubTrancheFreeze the value for account_sub.account_sub_tranche_freeze
     * @mbg.generated
     */
    public void setAccountSubTrancheFreeze(Integer accountSubTrancheFreeze) {
        this.accountSubTrancheFreeze = accountSubTrancheFreeze;
    }

    /**
     * 支付密码状态，0-未设置，1-已设置
     * @return the value of account_sub.account_sub_pwd_status
     * @mbg.generated
     */
    public Integer getAccountSubPwdStatus() {
        return accountSubPwdStatus;
    }

    /**
     * 支付密码状态，0-未设置，1-已设置
     * @param accountSubPwdStatus the value for account_sub.account_sub_pwd_status
     * @mbg.generated
     */
    public void setAccountSubPwdStatus(Integer accountSubPwdStatus) {
        this.accountSubPwdStatus = accountSubPwdStatus;
    }

    /**
     * 创建人
     * @return the value of account_sub.account_sub_create_user_id
     * @mbg.generated
     */
    public String getAccountSubCreateUserId() {
        return accountSubCreateUserId;
    }

    /**
     * 创建人
     * @param accountSubCreateUserId the value for account_sub.account_sub_create_user_id
     * @mbg.generated
     */
    public void setAccountSubCreateUserId(String accountSubCreateUserId) {
        this.accountSubCreateUserId = accountSubCreateUserId == null ? null : accountSubCreateUserId.trim();
    }

    /**
     * 创建时间
     * @return the value of account_sub.account_sub_create_time
     * @mbg.generated
     */
    public Date getAccountSubCreateTime() {
        return accountSubCreateTime;
    }

    /**
     * 创建时间
     * @param accountSubCreateTime the value for account_sub.account_sub_create_time
     * @mbg.generated
     */
    public void setAccountSubCreateTime(Date accountSubCreateTime) {
        this.accountSubCreateTime = accountSubCreateTime;
    }

    /**
     * 修改人
     * @return the value of account_sub.account_sub_update_user_id
     * @mbg.generated
     */
    public String getAccountSubUpdateUserId() {
        return accountSubUpdateUserId;
    }

    /**
     * 修改人
     * @param accountSubUpdateUserId the value for account_sub.account_sub_update_user_id
     * @mbg.generated
     */
    public void setAccountSubUpdateUserId(String accountSubUpdateUserId) {
        this.accountSubUpdateUserId = accountSubUpdateUserId == null ? null : accountSubUpdateUserId.trim();
    }

    /**
     * 修改时间
     * @return the value of account_sub.account_sub_update_time
     * @mbg.generated
     */
    public Date getAccountSubUpdateTime() {
        return accountSubUpdateTime;
    }

    /**
     * 修改时间
     * @param accountSubUpdateTime the value for account_sub.account_sub_update_time
     * @mbg.generated
     */
    public void setAccountSubUpdateTime(Date accountSubUpdateTime) {
        this.accountSubUpdateTime = accountSubUpdateTime;
    }

    /**
     * 是否删除 0-否,1-是
     * @return the value of account_sub.account_sub_is_delete
     * @mbg.generated
     */
    public Boolean getAccountSubIsDelete() {
        return accountSubIsDelete;
    }

    /**
     * 是否删除 0-否,1-是
     * @param accountSubIsDelete the value for account_sub.account_sub_is_delete
     * @mbg.generated
     */
    public void setAccountSubIsDelete(Boolean accountSubIsDelete) {
        this.accountSubIsDelete = accountSubIsDelete;
    }

    /**
     * 平安设置支付密码流水号
     * @return the value of account_sub.account_sub_order_id
     * @mbg.generated
     */
    public String getAccountSubOrderId() {
        return accountSubOrderId;
    }

    /**
     * 平安设置支付密码流水号
     * @param accountSubOrderId the value for account_sub.account_sub_order_id
     * @mbg.generated
     */
    public void setAccountSubOrderId(String accountSubOrderId) {
        this.accountSubOrderId = accountSubOrderId == null ? null : accountSubOrderId.trim();
    }

    /**
     * 是否显示账户余额 默认0显示  1不显示
     * @return the value of account_sub.account_money_state
     * @mbg.generated
     */
    public String getAccountMoneyState() {
        return accountMoneyState;
    }

    /**
     * 是否显示账户余额 默认0显示  1不显示
     * @param accountMoneyState the value for account_sub.account_money_state
     * @mbg.generated
     */
    public void setAccountMoneyState(String accountMoneyState) {
        this.accountMoneyState = accountMoneyState == null ? null : accountMoneyState.trim();
    }

    public String getAccountTransactionPhone() {
        return accountTransactionPhone;
    }

    public void setAccountTransactionPhone(String accountTransactionPhone) {
        this.accountTransactionPhone = accountTransactionPhone;
    }
}