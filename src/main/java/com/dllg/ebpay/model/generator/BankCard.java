package com.dllg.ebpay.model.generator;

import java.util.Date;

/**
 *   银行卡
 *   bank_card
 * @mbg.generated do_not_delete_during_merge
 */
public class BankCard {
    /**
     *   id
     *   bank_card.bank_card_id
     * @mbg.generated
     */
    private String bankCardId;

    /**
     *   子账户id
     *   bank_card.bank_card_account_sub_id
     * @mbg.generated
     */
    private String bankCardAccountSubId;

    /**
     *   开户银行
     *   bank_card.bank_card_bank
     * @mbg.generated
     */
    private String bankCardBank;

    /**
     *   开户银行网点
     *   bank_card.bank_card_bank_branch
     * @mbg.generated
     */
    private String bankCardBankBranch;

    /**
     *   卡种
     *   bank_card.bank_card_type
     * @mbg.generated
     */
    private String bankCardType;

    /**
     *   卡主类型，1-企业，2-个人
     *   bank_card.bank_card_master_type
     * @mbg.generated
     */
    private Integer bankCardMasterType;

    /**
     *   银行卡号
     *   bank_card.bank_card_no
     * @mbg.generated
     */
    private String bankCardNo;

    /**
     *   银行账户名称
     *   bank_card.bank_card_reserve_bank_account_name
     * @mbg.generated
     */
    private String bankCardReserveBankAccountName;

    /**
     *   手机号码
     *   bank_card.bank_card_reserve_mobile
     * @mbg.generated
     */
    private String bankCardReserveMobile;

    /**
     *   证件类型
     *   bank_card.bank_card_document_type
     * @mbg.generated
     */
    private String bankCardDocumentType;

    /**
     *   证件号码
     *   bank_card.bank_card_document_no
     * @mbg.generated
     */
    private String bankCardDocumentNo;

    /**
     *   单笔交易额度类型，1-<=5万，2->5万
     *   bank_card.bank_card_transaction_amount_type
     * @mbg.generated
     */
    private Integer bankCardTransactionAmountType;

    /**
     *   状态，1-待绑定，2-绑定成功，3-已解绑，4-绑定失败
     *   bank_card.bank_card_status
     * @mbg.generated
     */
    private Integer bankCardStatus;

    /**
     *   绑卡时间
     *   bank_card.bank_card_bind_bank_card_date
     * @mbg.generated
     */
    private Date bankCardBindBankCardDate;

    /**
     *   解绑时间
     *   bank_card.bank_card_unbind_bank_card_date
     * @mbg.generated
     */
    private Date bankCardUnbindBankCardDate;

    /**
     *   创建人
     *   bank_card.bank_card_create_user_id
     * @mbg.generated
     */
    private String bankCardCreateUserId;

    /**
     *   创建时间
     *   bank_card.bank_card_create_time
     * @mbg.generated
     */
    private Date bankCardCreateTime;

    /**
     *   修改人
     *   bank_card.bank_card_update_user_id
     * @mbg.generated
     */
    private String bankCardUpdateUserId;

    /**
     *   修改时间
     *   bank_card.bank_card_update_time
     * @mbg.generated
     */
    private Date bankCardUpdateTime;

    /**
     *   是否删除 0-否,1-是
     *   bank_card.bank_card_is_delete
     * @mbg.generated
     */
    private Boolean bankCardIsDelete;

    /**
     *   logo
     *   bank_card.bank_logo
     * @mbg.generated
     */
    private String bankLogo;

    /**
     *   原鉴权时发送的流水号，用于查询小额鉴权转账结果
     *   bank_card.bank_cnsmr_seq_no
     * @mbg.generated
     */
    private String bankCnsmrSeqNo;

    /**
     * id
     * @return the value of bank_card.bank_card_id
     * @mbg.generated
     */
    public String getBankCardId() {
        return bankCardId;
    }

    /**
     * id
     * @param bankCardId the value for bank_card.bank_card_id
     * @mbg.generated
     */
    public void setBankCardId(String bankCardId) {
        this.bankCardId = bankCardId == null ? null : bankCardId.trim();
    }

    /**
     * 子账户id
     * @return the value of bank_card.bank_card_account_sub_id
     * @mbg.generated
     */
    public String getBankCardAccountSubId() {
        return bankCardAccountSubId;
    }

    /**
     * 子账户id
     * @param bankCardAccountSubId the value for bank_card.bank_card_account_sub_id
     * @mbg.generated
     */
    public void setBankCardAccountSubId(String bankCardAccountSubId) {
        this.bankCardAccountSubId = bankCardAccountSubId == null ? null : bankCardAccountSubId.trim();
    }

    /**
     * 开户银行
     * @return the value of bank_card.bank_card_bank
     * @mbg.generated
     */
    public String getBankCardBank() {
        return bankCardBank;
    }

    /**
     * 开户银行
     * @param bankCardBank the value for bank_card.bank_card_bank
     * @mbg.generated
     */
    public void setBankCardBank(String bankCardBank) {
        this.bankCardBank = bankCardBank == null ? null : bankCardBank.trim();
    }

    /**
     * 开户银行网点
     * @return the value of bank_card.bank_card_bank_branch
     * @mbg.generated
     */
    public String getBankCardBankBranch() {
        return bankCardBankBranch;
    }

    /**
     * 开户银行网点
     * @param bankCardBankBranch the value for bank_card.bank_card_bank_branch
     * @mbg.generated
     */
    public void setBankCardBankBranch(String bankCardBankBranch) {
        this.bankCardBankBranch = bankCardBankBranch == null ? null : bankCardBankBranch.trim();
    }

    /**
     * 卡种
     * @return the value of bank_card.bank_card_type
     * @mbg.generated
     */
    public String getBankCardType() {
        return bankCardType;
    }

    /**
     * 卡种
     * @param bankCardType the value for bank_card.bank_card_type
     * @mbg.generated
     */
    public void setBankCardType(String bankCardType) {
        this.bankCardType = bankCardType == null ? null : bankCardType.trim();
    }

    /**
     * 卡主类型，1-企业，2-个人
     * @return the value of bank_card.bank_card_master_type
     * @mbg.generated
     */
    public Integer getBankCardMasterType() {
        return bankCardMasterType;
    }

    /**
     * 卡主类型，1-企业，2-个人
     * @param bankCardMasterType the value for bank_card.bank_card_master_type
     * @mbg.generated
     */
    public void setBankCardMasterType(Integer bankCardMasterType) {
        this.bankCardMasterType = bankCardMasterType;
    }

    /**
     * 银行卡号
     * @return the value of bank_card.bank_card_no
     * @mbg.generated
     */
    public String getBankCardNo() {
        return bankCardNo;
    }

    /**
     * 银行卡号
     * @param bankCardNo the value for bank_card.bank_card_no
     * @mbg.generated
     */
    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo == null ? null : bankCardNo.trim();
    }

    /**
     * 银行账户名称
     * @return the value of bank_card.bank_card_reserve_bank_account_name
     * @mbg.generated
     */
    public String getBankCardReserveBankAccountName() {
        return bankCardReserveBankAccountName;
    }

    /**
     * 银行账户名称
     * @param bankCardReserveBankAccountName the value for bank_card.bank_card_reserve_bank_account_name
     * @mbg.generated
     */
    public void setBankCardReserveBankAccountName(String bankCardReserveBankAccountName) {
        this.bankCardReserveBankAccountName = bankCardReserveBankAccountName == null ? null : bankCardReserveBankAccountName.trim();
    }

    /**
     * 手机号码
     * @return the value of bank_card.bank_card_reserve_mobile
     * @mbg.generated
     */
    public String getBankCardReserveMobile() {
        return bankCardReserveMobile;
    }

    /**
     * 手机号码
     * @param bankCardReserveMobile the value for bank_card.bank_card_reserve_mobile
     * @mbg.generated
     */
    public void setBankCardReserveMobile(String bankCardReserveMobile) {
        this.bankCardReserveMobile = bankCardReserveMobile == null ? null : bankCardReserveMobile.trim();
    }

    /**
     * 证件类型
     * @return the value of bank_card.bank_card_document_type
     * @mbg.generated
     */
    public String getBankCardDocumentType() {
        return bankCardDocumentType;
    }

    /**
     * 证件类型
     * @param bankCardDocumentType the value for bank_card.bank_card_document_type
     * @mbg.generated
     */
    public void setBankCardDocumentType(String bankCardDocumentType) {
        this.bankCardDocumentType = bankCardDocumentType == null ? null : bankCardDocumentType.trim();
    }

    /**
     * 证件号码
     * @return the value of bank_card.bank_card_document_no
     * @mbg.generated
     */
    public String getBankCardDocumentNo() {
        return bankCardDocumentNo;
    }

    /**
     * 证件号码
     * @param bankCardDocumentNo the value for bank_card.bank_card_document_no
     * @mbg.generated
     */
    public void setBankCardDocumentNo(String bankCardDocumentNo) {
        this.bankCardDocumentNo = bankCardDocumentNo == null ? null : bankCardDocumentNo.trim();
    }

    /**
     * 单笔交易额度类型，1-<=5万，2->5万
     * @return the value of bank_card.bank_card_transaction_amount_type
     * @mbg.generated
     */
    public Integer getBankCardTransactionAmountType() {
        return bankCardTransactionAmountType;
    }

    /**
     * 单笔交易额度类型，1-<=5万，2->5万
     * @param bankCardTransactionAmountType the value for bank_card.bank_card_transaction_amount_type
     * @mbg.generated
     */
    public void setBankCardTransactionAmountType(Integer bankCardTransactionAmountType) {
        this.bankCardTransactionAmountType = bankCardTransactionAmountType;
    }

    /**
     * 状态，1-待绑定，2-绑定成功，3-已解绑，4-绑定失败
     * @return the value of bank_card.bank_card_status
     * @mbg.generated
     */
    public Integer getBankCardStatus() {
        return bankCardStatus;
    }

    /**
     * 状态，1-待绑定，2-绑定成功，3-已解绑，4-绑定失败
     * @param bankCardStatus the value for bank_card.bank_card_status
     * @mbg.generated
     */
    public void setBankCardStatus(Integer bankCardStatus) {
        this.bankCardStatus = bankCardStatus;
    }

    /**
     * 绑卡时间
     * @return the value of bank_card.bank_card_bind_bank_card_date
     * @mbg.generated
     */
    public Date getBankCardBindBankCardDate() {
        return bankCardBindBankCardDate;
    }

    /**
     * 绑卡时间
     * @param bankCardBindBankCardDate the value for bank_card.bank_card_bind_bank_card_date
     * @mbg.generated
     */
    public void setBankCardBindBankCardDate(Date bankCardBindBankCardDate) {
        this.bankCardBindBankCardDate = bankCardBindBankCardDate;
    }

    /**
     * 解绑时间
     * @return the value of bank_card.bank_card_unbind_bank_card_date
     * @mbg.generated
     */
    public Date getBankCardUnbindBankCardDate() {
        return bankCardUnbindBankCardDate;
    }

    /**
     * 解绑时间
     * @param bankCardUnbindBankCardDate the value for bank_card.bank_card_unbind_bank_card_date
     * @mbg.generated
     */
    public void setBankCardUnbindBankCardDate(Date bankCardUnbindBankCardDate) {
        this.bankCardUnbindBankCardDate = bankCardUnbindBankCardDate;
    }

    /**
     * 创建人
     * @return the value of bank_card.bank_card_create_user_id
     * @mbg.generated
     */
    public String getBankCardCreateUserId() {
        return bankCardCreateUserId;
    }

    /**
     * 创建人
     * @param bankCardCreateUserId the value for bank_card.bank_card_create_user_id
     * @mbg.generated
     */
    public void setBankCardCreateUserId(String bankCardCreateUserId) {
        this.bankCardCreateUserId = bankCardCreateUserId == null ? null : bankCardCreateUserId.trim();
    }

    /**
     * 创建时间
     * @return the value of bank_card.bank_card_create_time
     * @mbg.generated
     */
    public Date getBankCardCreateTime() {
        return bankCardCreateTime;
    }

    /**
     * 创建时间
     * @param bankCardCreateTime the value for bank_card.bank_card_create_time
     * @mbg.generated
     */
    public void setBankCardCreateTime(Date bankCardCreateTime) {
        this.bankCardCreateTime = bankCardCreateTime;
    }

    /**
     * 修改人
     * @return the value of bank_card.bank_card_update_user_id
     * @mbg.generated
     */
    public String getBankCardUpdateUserId() {
        return bankCardUpdateUserId;
    }

    /**
     * 修改人
     * @param bankCardUpdateUserId the value for bank_card.bank_card_update_user_id
     * @mbg.generated
     */
    public void setBankCardUpdateUserId(String bankCardUpdateUserId) {
        this.bankCardUpdateUserId = bankCardUpdateUserId == null ? null : bankCardUpdateUserId.trim();
    }

    /**
     * 修改时间
     * @return the value of bank_card.bank_card_update_time
     * @mbg.generated
     */
    public Date getBankCardUpdateTime() {
        return bankCardUpdateTime;
    }

    /**
     * 修改时间
     * @param bankCardUpdateTime the value for bank_card.bank_card_update_time
     * @mbg.generated
     */
    public void setBankCardUpdateTime(Date bankCardUpdateTime) {
        this.bankCardUpdateTime = bankCardUpdateTime;
    }

    /**
     * 是否删除 0-否,1-是
     * @return the value of bank_card.bank_card_is_delete
     * @mbg.generated
     */
    public Boolean getBankCardIsDelete() {
        return bankCardIsDelete;
    }

    /**
     * 是否删除 0-否,1-是
     * @param bankCardIsDelete the value for bank_card.bank_card_is_delete
     * @mbg.generated
     */
    public void setBankCardIsDelete(Boolean bankCardIsDelete) {
        this.bankCardIsDelete = bankCardIsDelete;
    }

    /**
     * logo
     * @return the value of bank_card.bank_logo
     * @mbg.generated
     */
    public String getBankLogo() {
        return bankLogo;
    }

    /**
     * logo
     * @param bankLogo the value for bank_card.bank_logo
     * @mbg.generated
     */
    public void setBankLogo(String bankLogo) {
        this.bankLogo = bankLogo == null ? null : bankLogo.trim();
    }

    /**
     * 原鉴权时发送的流水号，用于查询小额鉴权转账结果
     * @return the value of bank_card.bank_cnsmr_seq_no
     * @mbg.generated
     */
    public String getBankCnsmrSeqNo() {
        return bankCnsmrSeqNo;
    }

    /**
     * 原鉴权时发送的流水号，用于查询小额鉴权转账结果
     * @param bankCnsmrSeqNo the value for bank_card.bank_cnsmr_seq_no
     * @mbg.generated
     */
    public void setBankCnsmrSeqNo(String bankCnsmrSeqNo) {
        this.bankCnsmrSeqNo = bankCnsmrSeqNo == null ? null : bankCnsmrSeqNo.trim();
    }
}