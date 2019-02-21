package com.dllg.ebpay.model.generator;

import java.math.BigDecimal;
import java.util.Date;

/**
 *   子账户流水记录
 *   account_sub_trade
 * @mbg.generated do_not_delete_during_merge
 */
public class AccountSubTrade {
    /**
     *   id
     *   account_sub_trade.account_sub_trade_id
     * @mbg.generated
     */
    private String accountSubTradeId;

    /**
     *   主体子账号id
     *   account_sub_trade.account_sub_trade_account_sub_id
     * @mbg.generated
     */
    private String accountSubTradeAccountSubId;

    /**
     *   流水号（银行交易订单号：用于退款）
     *   account_sub_trade.account_sub_trade_no
     * @mbg.generated
     */
    private String accountSubTradeNo;

    /**
     *   银行流水号
     *   account_sub_trade.account_sub_trade_bank_no
     * @mbg.generated
     */
    private String accountSubTradeBankNo;

    /**
     *   流水类型，1-会员交易，2-转账，3-充值，4-提现，5-服务费，6-退款，7-挂账
     *   account_sub_trade.account_sub_trade_type
     * @mbg.generated
     */
    private Integer accountSubTradeType;

    /**
     *   0、充值 1、充值交易
     *   account_sub_trade.account_is_deal
     * @mbg.generated
     */
    private String accountIsDeal;

    /**
     *   会员交易功能标识；1：下单预支付 （付款方→担保），2：确认并付款（担保→收款方），3：退款（担保→付款方），6：直接支付（会员A→会员B）
     *   account_sub_trade.account_sub_trade_feature_identifier
     * @mbg.generated
     */
    private Integer accountSubTradeFeatureIdentifier;

    /**
     *   交易金额（元）
     *   account_sub_trade.account_sub_trade_tran_amt
     * @mbg.generated
     */
    private BigDecimal accountSubTradeTranAmt;

    /**
     *   交易费用（元）（平台收取交易费用-手续费）
     *   account_sub_trade.account_sub_trade_tran_fee
     * @mbg.generated
     */
    private BigDecimal accountSubTradeTranFee;

    /**
     *   交易时间
     *   account_sub_trade.account_sub_trade_tran_date
     * @mbg.generated
     */
    private Date accountSubTradeTranDate;

    /**
     *   交易类型，01：普通交易
     *   account_sub_trade.account_sub_trade_tran_type
     * @mbg.generated
     */
    private String accountSubTradeTranType;

    /**
     *   交易状态，（0：成功，1：失败，2：待确认, 5：待处理，6：处理中, 7: 待挂账，8：挂账成功，9：挂账失败， 10：提现退单）
     *   account_sub_trade.account_sub_trade_tran_status
     * @mbg.generated
     */
    private Integer accountSubTradeTranStatus;

    /**
     *   账户名称（挂账）
     *   account_sub_trade.account_sub_trade_bank_account
     * @mbg.generated
     */
    private String accountSubTradeBankAccount;

    /**
     *   银行卡号(挂账)
     *   account_sub_trade.account_sub_trade_bank_num
     * @mbg.generated
     */
    private String accountSubTradeBankNum;

    /**
     *   交易相对主体收支类型，1：转出 2：转入
     *   account_sub_trade.account_sub_trade_tran_payment_type
     * @mbg.generated
     */
    private Integer accountSubTradeTranPaymentType;

    /**
     *   币种，默认：RMB
     *   account_sub_trade.account_sub_trade_ccy
     * @mbg.generated
     */
    private String accountSubTradeCcy;

    /**
     *   会计日期（即银行主机记账日期）
     *   account_sub_trade.account_sub_trade_accounting_date
     * @mbg.generated
     */
    private Date accountSubTradeAccountingDate;

    /**
     *   银行名称（付款账户银行名称）
     *   account_sub_trade.account_sub_trade_bank_name
     * @mbg.generated
     */
    private String accountSubTradeBankName;

    /**
     *   备注
     *   account_sub_trade.account_sub_trade_remark
     * @mbg.generated
     */
    private String accountSubTradeRemark;

    /**
     *   从银行获取的备注信息
     *   account_sub_trade.account_sub_trade_bank_remark
     * @mbg.generated
     */
    private String accountSubTradeBankRemark;

    /**
     *   提现账号-银行卡号（即收款账户，必须是在系统中维护的提现账号）
     *   account_sub_trade.account_sub_trade_take_cash_account_no
     * @mbg.generated
     */
    private String accountSubTradeTakeCashAccountNo;

    /**
     *   提现账户名称（银行卡户名，必须与子账户名称一致）
     *   account_sub_trade.account_sub_trade_take_cash_account_name
     * @mbg.generated
     */
    private String accountSubTradeTakeCashAccountName;

    /**
     *   转入子账户id（收款方）
     *   account_sub_trade.account_sub_trade_in_account_sub_id
     * @mbg.generated
     */
    private String accountSubTradeInAccountSubId;

    /**
     *   退单原因
     *   account_sub_trade.account_sub_trade_back_reason
     * @mbg.generated
     */
    private String accountSubTradeBackReason;

    /**
     *   退单-银行流水号
     *   account_sub_trade.account_sub_trade_back_bank_seq_no
     * @mbg.generated
     */
    private String accountSubTradeBackBankSeqNo;

    /**
     *   退单-市场流水号
     *   account_sub_trade.account_sub_trade_back_market_seq_no
     * @mbg.generated
     */
    private String accountSubTradeBackMarketSeqNo;

    /**
     *   调账人
     *   account_sub_trade.account_sub_trade_adjust_user_id
     * @mbg.generated
     */
    private String accountSubTradeAdjustUserId;

    /**
     *   调账时间
     *   account_sub_trade.account_sub_trade_adjust_time
     * @mbg.generated
     */
    private Date accountSubTradeAdjustTime;

    /**
     *   失败原因
     *   account_sub_trade.account_sub_failure_reason
     * @mbg.generated
     */
    private String accountSubFailureReason;

    /**
     *   是否发送短信
     *   account_sub_trade.account_sub_trade_is_send_sms
     * @mbg.generated
     */
    private Boolean accountSubTradeIsSendSms;

    /**
     *   支付人
     *   account_sub_trade.account_sub_trade_pay_user_id
     * @mbg.generated
     */
    private String accountSubTradePayUserId;

    /**
     *   支付人姓名
     *   account_sub_trade.account_sub_trade_pay_user_name
     * @mbg.generated
     */
    private String accountSubTradePayUserName;

    /**
     *   创建人
     *   account_sub_trade.account_sub_trade_create_user_id
     * @mbg.generated
     */
    private String accountSubTradeCreateUserId;

    /**
     *   创建时间
     *   account_sub_trade.account_sub_trade_create_time
     * @mbg.generated
     */
    private Date accountSubTradeCreateTime;

    /**
     *   修改人
     *   account_sub_trade.account_sub_trade_update_user_id
     * @mbg.generated
     */
    private String accountSubTradeUpdateUserId;

    /**
     *   修改时间
     *   account_sub_trade.account_sub_trade_update_time
     * @mbg.generated
     */
    private Date accountSubTradeUpdateTime;

    /**
     *   是否删除 0-否,1-是
     *   account_sub_trade.account_sub_trade_is_delete
     * @mbg.generated
     */
    private Boolean accountSubTradeIsDelete;

    /**
     * id
     * @return the value of account_sub_trade.account_sub_trade_id
     * @mbg.generated
     */
    public String getAccountSubTradeId() {
        return accountSubTradeId;
    }

    /**
     * id
     * @param accountSubTradeId the value for account_sub_trade.account_sub_trade_id
     * @mbg.generated
     */
    public void setAccountSubTradeId(String accountSubTradeId) {
        this.accountSubTradeId = accountSubTradeId == null ? null : accountSubTradeId.trim();
    }

    /**
     * 主体子账号id
     * @return the value of account_sub_trade.account_sub_trade_account_sub_id
     * @mbg.generated
     */
    public String getAccountSubTradeAccountSubId() {
        return accountSubTradeAccountSubId;
    }

    /**
     * 主体子账号id
     * @param accountSubTradeAccountSubId the value for account_sub_trade.account_sub_trade_account_sub_id
     * @mbg.generated
     */
    public void setAccountSubTradeAccountSubId(String accountSubTradeAccountSubId) {
        this.accountSubTradeAccountSubId = accountSubTradeAccountSubId == null ? null : accountSubTradeAccountSubId.trim();
    }

    /**
     * 流水号（银行交易订单号：用于退款）
     * @return the value of account_sub_trade.account_sub_trade_no
     * @mbg.generated
     */
    public String getAccountSubTradeNo() {
        return accountSubTradeNo;
    }

    /**
     * 流水号（银行交易订单号：用于退款）
     * @param accountSubTradeNo the value for account_sub_trade.account_sub_trade_no
     * @mbg.generated
     */
    public void setAccountSubTradeNo(String accountSubTradeNo) {
        this.accountSubTradeNo = accountSubTradeNo == null ? null : accountSubTradeNo.trim();
    }

    /**
     * 银行流水号
     * @return the value of account_sub_trade.account_sub_trade_bank_no
     * @mbg.generated
     */
    public String getAccountSubTradeBankNo() {
        return accountSubTradeBankNo;
    }

    /**
     * 银行流水号
     * @param accountSubTradeBankNo the value for account_sub_trade.account_sub_trade_bank_no
     * @mbg.generated
     */
    public void setAccountSubTradeBankNo(String accountSubTradeBankNo) {
        this.accountSubTradeBankNo = accountSubTradeBankNo == null ? null : accountSubTradeBankNo.trim();
    }

    /**
     * 流水类型，1-会员交易，2-转账，3-充值，4-提现，5-服务费，6-退款，7-挂账
     * @return the value of account_sub_trade.account_sub_trade_type
     * @mbg.generated
     */
    public Integer getAccountSubTradeType() {
        return accountSubTradeType;
    }

    /**
     * 流水类型，1-会员交易，2-转账，3-充值，4-提现，5-服务费，6-退款，7-挂账
     * @param accountSubTradeType the value for account_sub_trade.account_sub_trade_type
     * @mbg.generated
     */
    public void setAccountSubTradeType(Integer accountSubTradeType) {
        this.accountSubTradeType = accountSubTradeType;
    }

    /**
     * 0、充值 1、充值交易
     * @return the value of account_sub_trade.account_is_deal
     * @mbg.generated
     */
    public String getAccountIsDeal() {
        return accountIsDeal;
    }

    /**
     * 0、充值 1、充值交易
     * @param accountIsDeal the value for account_sub_trade.account_is_deal
     * @mbg.generated
     */
    public void setAccountIsDeal(String accountIsDeal) {
        this.accountIsDeal = accountIsDeal == null ? null : accountIsDeal.trim();
    }

    /**
     * 会员交易功能标识；1：下单预支付 （付款方→担保），2：确认并付款（担保→收款方），3：退款（担保→付款方），6：直接支付（会员A→会员B）
     * @return the value of account_sub_trade.account_sub_trade_feature_identifier
     * @mbg.generated
     */
    public Integer getAccountSubTradeFeatureIdentifier() {
        return accountSubTradeFeatureIdentifier;
    }

    /**
     * 会员交易功能标识；1：下单预支付 （付款方→担保），2：确认并付款（担保→收款方），3：退款（担保→付款方），6：直接支付（会员A→会员B）
     * @param accountSubTradeFeatureIdentifier the value for account_sub_trade.account_sub_trade_feature_identifier
     * @mbg.generated
     */
    public void setAccountSubTradeFeatureIdentifier(Integer accountSubTradeFeatureIdentifier) {
        this.accountSubTradeFeatureIdentifier = accountSubTradeFeatureIdentifier;
    }

    /**
     * 交易金额（元）
     * @return the value of account_sub_trade.account_sub_trade_tran_amt
     * @mbg.generated
     */
    public BigDecimal getAccountSubTradeTranAmt() {
        return accountSubTradeTranAmt;
    }

    /**
     * 交易金额（元）
     * @param accountSubTradeTranAmt the value for account_sub_trade.account_sub_trade_tran_amt
     * @mbg.generated
     */
    public void setAccountSubTradeTranAmt(BigDecimal accountSubTradeTranAmt) {
        this.accountSubTradeTranAmt = accountSubTradeTranAmt;
    }

    /**
     * 交易费用（元）（平台收取交易费用-手续费）
     * @return the value of account_sub_trade.account_sub_trade_tran_fee
     * @mbg.generated
     */
    public BigDecimal getAccountSubTradeTranFee() {
        return accountSubTradeTranFee;
    }

    /**
     * 交易费用（元）（平台收取交易费用-手续费）
     * @param accountSubTradeTranFee the value for account_sub_trade.account_sub_trade_tran_fee
     * @mbg.generated
     */
    public void setAccountSubTradeTranFee(BigDecimal accountSubTradeTranFee) {
        this.accountSubTradeTranFee = accountSubTradeTranFee;
    }

    /**
     * 交易时间
     * @return the value of account_sub_trade.account_sub_trade_tran_date
     * @mbg.generated
     */
    public Date getAccountSubTradeTranDate() {
        return accountSubTradeTranDate;
    }

    /**
     * 交易时间
     * @param accountSubTradeTranDate the value for account_sub_trade.account_sub_trade_tran_date
     * @mbg.generated
     */
    public void setAccountSubTradeTranDate(Date accountSubTradeTranDate) {
        this.accountSubTradeTranDate = accountSubTradeTranDate;
    }

    /**
     * 交易类型，01：普通交易
     * @return the value of account_sub_trade.account_sub_trade_tran_type
     * @mbg.generated
     */
    public String getAccountSubTradeTranType() {
        return accountSubTradeTranType;
    }

    /**
     * 交易类型，01：普通交易
     * @param accountSubTradeTranType the value for account_sub_trade.account_sub_trade_tran_type
     * @mbg.generated
     */
    public void setAccountSubTradeTranType(String accountSubTradeTranType) {
        this.accountSubTradeTranType = accountSubTradeTranType == null ? null : accountSubTradeTranType.trim();
    }

    /**
     * 交易状态，（0：成功，1：失败，2：待确认, 5：待处理，6：处理中, 7: 待挂账，8：挂账成功，9：挂账失败， 10：提现退单）
     * @return the value of account_sub_trade.account_sub_trade_tran_status
     * @mbg.generated
     */
    public Integer getAccountSubTradeTranStatus() {
        return accountSubTradeTranStatus;
    }

    /**
     * 交易状态，（0：成功，1：失败，2：待确认, 5：待处理，6：处理中, 7: 待挂账，8：挂账成功，9：挂账失败， 10：提现退单）
     * @param accountSubTradeTranStatus the value for account_sub_trade.account_sub_trade_tran_status
     * @mbg.generated
     */
    public void setAccountSubTradeTranStatus(Integer accountSubTradeTranStatus) {
        this.accountSubTradeTranStatus = accountSubTradeTranStatus;
    }

    /**
     * 账户名称（挂账）
     * @return the value of account_sub_trade.account_sub_trade_bank_account
     * @mbg.generated
     */
    public String getAccountSubTradeBankAccount() {
        return accountSubTradeBankAccount;
    }

    /**
     * 账户名称（挂账）
     * @param accountSubTradeBankAccount the value for account_sub_trade.account_sub_trade_bank_account
     * @mbg.generated
     */
    public void setAccountSubTradeBankAccount(String accountSubTradeBankAccount) {
        this.accountSubTradeBankAccount = accountSubTradeBankAccount == null ? null : accountSubTradeBankAccount.trim();
    }

    /**
     * 银行卡号(挂账)
     * @return the value of account_sub_trade.account_sub_trade_bank_num
     * @mbg.generated
     */
    public String getAccountSubTradeBankNum() {
        return accountSubTradeBankNum;
    }

    /**
     * 银行卡号(挂账)
     * @param accountSubTradeBankNum the value for account_sub_trade.account_sub_trade_bank_num
     * @mbg.generated
     */
    public void setAccountSubTradeBankNum(String accountSubTradeBankNum) {
        this.accountSubTradeBankNum = accountSubTradeBankNum == null ? null : accountSubTradeBankNum.trim();
    }

    /**
     * 交易相对主体收支类型，1：转出 2：转入
     * @return the value of account_sub_trade.account_sub_trade_tran_payment_type
     * @mbg.generated
     */
    public Integer getAccountSubTradeTranPaymentType() {
        return accountSubTradeTranPaymentType;
    }

    /**
     * 交易相对主体收支类型，1：转出 2：转入
     * @param accountSubTradeTranPaymentType the value for account_sub_trade.account_sub_trade_tran_payment_type
     * @mbg.generated
     */
    public void setAccountSubTradeTranPaymentType(Integer accountSubTradeTranPaymentType) {
        this.accountSubTradeTranPaymentType = accountSubTradeTranPaymentType;
    }

    /**
     * 币种，默认：RMB
     * @return the value of account_sub_trade.account_sub_trade_ccy
     * @mbg.generated
     */
    public String getAccountSubTradeCcy() {
        return accountSubTradeCcy;
    }

    /**
     * 币种，默认：RMB
     * @param accountSubTradeCcy the value for account_sub_trade.account_sub_trade_ccy
     * @mbg.generated
     */
    public void setAccountSubTradeCcy(String accountSubTradeCcy) {
        this.accountSubTradeCcy = accountSubTradeCcy == null ? null : accountSubTradeCcy.trim();
    }

    /**
     * 会计日期（即银行主机记账日期）
     * @return the value of account_sub_trade.account_sub_trade_accounting_date
     * @mbg.generated
     */
    public Date getAccountSubTradeAccountingDate() {
        return accountSubTradeAccountingDate;
    }

    /**
     * 会计日期（即银行主机记账日期）
     * @param accountSubTradeAccountingDate the value for account_sub_trade.account_sub_trade_accounting_date
     * @mbg.generated
     */
    public void setAccountSubTradeAccountingDate(Date accountSubTradeAccountingDate) {
        this.accountSubTradeAccountingDate = accountSubTradeAccountingDate;
    }

    /**
     * 银行名称（付款账户银行名称）
     * @return the value of account_sub_trade.account_sub_trade_bank_name
     * @mbg.generated
     */
    public String getAccountSubTradeBankName() {
        return accountSubTradeBankName;
    }

    /**
     * 银行名称（付款账户银行名称）
     * @param accountSubTradeBankName the value for account_sub_trade.account_sub_trade_bank_name
     * @mbg.generated
     */
    public void setAccountSubTradeBankName(String accountSubTradeBankName) {
        this.accountSubTradeBankName = accountSubTradeBankName == null ? null : accountSubTradeBankName.trim();
    }

    /**
     * 备注
     * @return the value of account_sub_trade.account_sub_trade_remark
     * @mbg.generated
     */
    public String getAccountSubTradeRemark() {
        return accountSubTradeRemark;
    }

    /**
     * 备注
     * @param accountSubTradeRemark the value for account_sub_trade.account_sub_trade_remark
     * @mbg.generated
     */
    public void setAccountSubTradeRemark(String accountSubTradeRemark) {
        this.accountSubTradeRemark = accountSubTradeRemark == null ? null : accountSubTradeRemark.trim();
    }

    /**
     * 从银行获取的备注信息
     * @return the value of account_sub_trade.account_sub_trade_bank_remark
     * @mbg.generated
     */
    public String getAccountSubTradeBankRemark() {
        return accountSubTradeBankRemark;
    }

    /**
     * 从银行获取的备注信息
     * @param accountSubTradeBankRemark the value for account_sub_trade.account_sub_trade_bank_remark
     * @mbg.generated
     */
    public void setAccountSubTradeBankRemark(String accountSubTradeBankRemark) {
        this.accountSubTradeBankRemark = accountSubTradeBankRemark == null ? null : accountSubTradeBankRemark.trim();
    }

    /**
     * 提现账号-银行卡号（即收款账户，必须是在系统中维护的提现账号）
     * @return the value of account_sub_trade.account_sub_trade_take_cash_account_no
     * @mbg.generated
     */
    public String getAccountSubTradeTakeCashAccountNo() {
        return accountSubTradeTakeCashAccountNo;
    }

    /**
     * 提现账号-银行卡号（即收款账户，必须是在系统中维护的提现账号）
     * @param accountSubTradeTakeCashAccountNo the value for account_sub_trade.account_sub_trade_take_cash_account_no
     * @mbg.generated
     */
    public void setAccountSubTradeTakeCashAccountNo(String accountSubTradeTakeCashAccountNo) {
        this.accountSubTradeTakeCashAccountNo = accountSubTradeTakeCashAccountNo == null ? null : accountSubTradeTakeCashAccountNo.trim();
    }

    /**
     * 提现账户名称（银行卡户名，必须与子账户名称一致）
     * @return the value of account_sub_trade.account_sub_trade_take_cash_account_name
     * @mbg.generated
     */
    public String getAccountSubTradeTakeCashAccountName() {
        return accountSubTradeTakeCashAccountName;
    }

    /**
     * 提现账户名称（银行卡户名，必须与子账户名称一致）
     * @param accountSubTradeTakeCashAccountName the value for account_sub_trade.account_sub_trade_take_cash_account_name
     * @mbg.generated
     */
    public void setAccountSubTradeTakeCashAccountName(String accountSubTradeTakeCashAccountName) {
        this.accountSubTradeTakeCashAccountName = accountSubTradeTakeCashAccountName == null ? null : accountSubTradeTakeCashAccountName.trim();
    }

    /**
     * 转入子账户id（收款方）
     * @return the value of account_sub_trade.account_sub_trade_in_account_sub_id
     * @mbg.generated
     */
    public String getAccountSubTradeInAccountSubId() {
        return accountSubTradeInAccountSubId;
    }

    /**
     * 转入子账户id（收款方）
     * @param accountSubTradeInAccountSubId the value for account_sub_trade.account_sub_trade_in_account_sub_id
     * @mbg.generated
     */
    public void setAccountSubTradeInAccountSubId(String accountSubTradeInAccountSubId) {
        this.accountSubTradeInAccountSubId = accountSubTradeInAccountSubId == null ? null : accountSubTradeInAccountSubId.trim();
    }

    /**
     * 退单原因
     * @return the value of account_sub_trade.account_sub_trade_back_reason
     * @mbg.generated
     */
    public String getAccountSubTradeBackReason() {
        return accountSubTradeBackReason;
    }

    /**
     * 退单原因
     * @param accountSubTradeBackReason the value for account_sub_trade.account_sub_trade_back_reason
     * @mbg.generated
     */
    public void setAccountSubTradeBackReason(String accountSubTradeBackReason) {
        this.accountSubTradeBackReason = accountSubTradeBackReason == null ? null : accountSubTradeBackReason.trim();
    }

    /**
     * 退单-银行流水号
     * @return the value of account_sub_trade.account_sub_trade_back_bank_seq_no
     * @mbg.generated
     */
    public String getAccountSubTradeBackBankSeqNo() {
        return accountSubTradeBackBankSeqNo;
    }

    /**
     * 退单-银行流水号
     * @param accountSubTradeBackBankSeqNo the value for account_sub_trade.account_sub_trade_back_bank_seq_no
     * @mbg.generated
     */
    public void setAccountSubTradeBackBankSeqNo(String accountSubTradeBackBankSeqNo) {
        this.accountSubTradeBackBankSeqNo = accountSubTradeBackBankSeqNo == null ? null : accountSubTradeBackBankSeqNo.trim();
    }

    /**
     * 退单-市场流水号
     * @return the value of account_sub_trade.account_sub_trade_back_market_seq_no
     * @mbg.generated
     */
    public String getAccountSubTradeBackMarketSeqNo() {
        return accountSubTradeBackMarketSeqNo;
    }

    /**
     * 退单-市场流水号
     * @param accountSubTradeBackMarketSeqNo the value for account_sub_trade.account_sub_trade_back_market_seq_no
     * @mbg.generated
     */
    public void setAccountSubTradeBackMarketSeqNo(String accountSubTradeBackMarketSeqNo) {
        this.accountSubTradeBackMarketSeqNo = accountSubTradeBackMarketSeqNo == null ? null : accountSubTradeBackMarketSeqNo.trim();
    }

    /**
     * 调账人
     * @return the value of account_sub_trade.account_sub_trade_adjust_user_id
     * @mbg.generated
     */
    public String getAccountSubTradeAdjustUserId() {
        return accountSubTradeAdjustUserId;
    }

    /**
     * 调账人
     * @param accountSubTradeAdjustUserId the value for account_sub_trade.account_sub_trade_adjust_user_id
     * @mbg.generated
     */
    public void setAccountSubTradeAdjustUserId(String accountSubTradeAdjustUserId) {
        this.accountSubTradeAdjustUserId = accountSubTradeAdjustUserId == null ? null : accountSubTradeAdjustUserId.trim();
    }

    /**
     * 调账时间
     * @return the value of account_sub_trade.account_sub_trade_adjust_time
     * @mbg.generated
     */
    public Date getAccountSubTradeAdjustTime() {
        return accountSubTradeAdjustTime;
    }

    /**
     * 调账时间
     * @param accountSubTradeAdjustTime the value for account_sub_trade.account_sub_trade_adjust_time
     * @mbg.generated
     */
    public void setAccountSubTradeAdjustTime(Date accountSubTradeAdjustTime) {
        this.accountSubTradeAdjustTime = accountSubTradeAdjustTime;
    }

    /**
     * 失败原因
     * @return the value of account_sub_trade.account_sub_failure_reason
     * @mbg.generated
     */
    public String getAccountSubFailureReason() {
        return accountSubFailureReason;
    }

    /**
     * 失败原因
     * @param accountSubFailureReason the value for account_sub_trade.account_sub_failure_reason
     * @mbg.generated
     */
    public void setAccountSubFailureReason(String accountSubFailureReason) {
        this.accountSubFailureReason = accountSubFailureReason == null ? null : accountSubFailureReason.trim();
    }

    /**
     * 是否发送短信
     * @return the value of account_sub_trade.account_sub_trade_is_send_sms
     * @mbg.generated
     */
    public Boolean getAccountSubTradeIsSendSms() {
        return accountSubTradeIsSendSms;
    }

    /**
     * 是否发送短信
     * @param accountSubTradeIsSendSms the value for account_sub_trade.account_sub_trade_is_send_sms
     * @mbg.generated
     */
    public void setAccountSubTradeIsSendSms(Boolean accountSubTradeIsSendSms) {
        this.accountSubTradeIsSendSms = accountSubTradeIsSendSms;
    }

    /**
     * 支付人
     * @return the value of account_sub_trade.account_sub_trade_pay_user_id
     * @mbg.generated
     */
    public String getAccountSubTradePayUserId() {
        return accountSubTradePayUserId;
    }

    /**
     * 支付人
     * @param accountSubTradePayUserId the value for account_sub_trade.account_sub_trade_pay_user_id
     * @mbg.generated
     */
    public void setAccountSubTradePayUserId(String accountSubTradePayUserId) {
        this.accountSubTradePayUserId = accountSubTradePayUserId == null ? null : accountSubTradePayUserId.trim();
    }

    /**
     * 支付人姓名
     * @return the value of account_sub_trade.account_sub_trade_pay_user_name
     * @mbg.generated
     */
    public String getAccountSubTradePayUserName() {
        return accountSubTradePayUserName;
    }

    /**
     * 支付人姓名
     * @param accountSubTradePayUserName the value for account_sub_trade.account_sub_trade_pay_user_name
     * @mbg.generated
     */
    public void setAccountSubTradePayUserName(String accountSubTradePayUserName) {
        this.accountSubTradePayUserName = accountSubTradePayUserName == null ? null : accountSubTradePayUserName.trim();
    }

    /**
     * 创建人
     * @return the value of account_sub_trade.account_sub_trade_create_user_id
     * @mbg.generated
     */
    public String getAccountSubTradeCreateUserId() {
        return accountSubTradeCreateUserId;
    }

    /**
     * 创建人
     * @param accountSubTradeCreateUserId the value for account_sub_trade.account_sub_trade_create_user_id
     * @mbg.generated
     */
    public void setAccountSubTradeCreateUserId(String accountSubTradeCreateUserId) {
        this.accountSubTradeCreateUserId = accountSubTradeCreateUserId == null ? null : accountSubTradeCreateUserId.trim();
    }

    /**
     * 创建时间
     * @return the value of account_sub_trade.account_sub_trade_create_time
     * @mbg.generated
     */
    public Date getAccountSubTradeCreateTime() {
        return accountSubTradeCreateTime;
    }

    /**
     * 创建时间
     * @param accountSubTradeCreateTime the value for account_sub_trade.account_sub_trade_create_time
     * @mbg.generated
     */
    public void setAccountSubTradeCreateTime(Date accountSubTradeCreateTime) {
        this.accountSubTradeCreateTime = accountSubTradeCreateTime;
    }

    /**
     * 修改人
     * @return the value of account_sub_trade.account_sub_trade_update_user_id
     * @mbg.generated
     */
    public String getAccountSubTradeUpdateUserId() {
        return accountSubTradeUpdateUserId;
    }

    /**
     * 修改人
     * @param accountSubTradeUpdateUserId the value for account_sub_trade.account_sub_trade_update_user_id
     * @mbg.generated
     */
    public void setAccountSubTradeUpdateUserId(String accountSubTradeUpdateUserId) {
        this.accountSubTradeUpdateUserId = accountSubTradeUpdateUserId == null ? null : accountSubTradeUpdateUserId.trim();
    }

    /**
     * 修改时间
     * @return the value of account_sub_trade.account_sub_trade_update_time
     * @mbg.generated
     */
    public Date getAccountSubTradeUpdateTime() {
        return accountSubTradeUpdateTime;
    }

    /**
     * 修改时间
     * @param accountSubTradeUpdateTime the value for account_sub_trade.account_sub_trade_update_time
     * @mbg.generated
     */
    public void setAccountSubTradeUpdateTime(Date accountSubTradeUpdateTime) {
        this.accountSubTradeUpdateTime = accountSubTradeUpdateTime;
    }

    /**
     * 是否删除 0-否,1-是
     * @return the value of account_sub_trade.account_sub_trade_is_delete
     * @mbg.generated
     */
    public Boolean getAccountSubTradeIsDelete() {
        return accountSubTradeIsDelete;
    }

    /**
     * 是否删除 0-否,1-是
     * @param accountSubTradeIsDelete the value for account_sub_trade.account_sub_trade_is_delete
     * @mbg.generated
     */
    public void setAccountSubTradeIsDelete(Boolean accountSubTradeIsDelete) {
        this.accountSubTradeIsDelete = accountSubTradeIsDelete;
    }
}