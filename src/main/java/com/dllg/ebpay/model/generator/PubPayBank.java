package com.dllg.ebpay.model.generator;

/**
 *   大小额银行代码
 *   pub_pay_bank
 * @mbg.generated do_not_delete_during_merge
 */
public class PubPayBank {
    /**
     *   id
     *   pub_pay_bank.bank_id
     * @mbg.generated
     */
    private Integer bankId;

    /**
     *   银行名称
     *   pub_pay_bank.bank_name
     * @mbg.generated
     */
    private String bankName;

    /**
     *   银行编码
     *   pub_pay_bank.bank_code
     * @mbg.generated
     */
    private String bankCode;

    /**
     *   1-只能是银联鉴权的银行 2-无限制
     *   pub_pay_bank.bank_target
     * @mbg.generated
     */
    private Integer bankTarget;

    private String bankLogo = "";
    /**
     * id
     * @return the value of pub_pay_bank.bank_id
     * @mbg.generated
     */
    public Integer getBankId() {
        return bankId;
    }

    /**
     * id
     * @param bankId the value for pub_pay_bank.bank_id
     * @mbg.generated
     */
    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }

    /**
     * 银行名称
     * @return the value of pub_pay_bank.bank_name
     * @mbg.generated
     */
    public String getBankName() {
        return bankName;
    }

    /**
     * 银行名称
     * @param bankName the value for pub_pay_bank.bank_name
     * @mbg.generated
     */
    public void setBankName(String bankName) {
        this.bankName = bankName == null ? null : bankName.trim();
    }

    /**
     * 银行编码
     * @return the value of pub_pay_bank.bank_code
     * @mbg.generated
     */
    public String getBankCode() {
        return bankCode;
    }

    /**
     * 银行编码
     * @param bankCode the value for pub_pay_bank.bank_code
     * @mbg.generated
     */
    public void setBankCode(String bankCode) {
        this.bankCode = bankCode == null ? null : bankCode.trim();
    }

    /**
     * 1-只能是银联鉴权的银行 2-无限制
     * @return the value of pub_pay_bank.bank_target
     * @mbg.generated
     */
    public Integer getBankTarget() {
        return bankTarget;
    }

    /**
     * 1-只能是银联鉴权的银行 2-无限制
     * @param bankTarget the value for pub_pay_bank.bank_target
     * @mbg.generated
     */
    public void setBankTarget(Integer bankTarget) {
        this.bankTarget = bankTarget;
    }

    public String getBankLogo() {
        return bankLogo;
    }

    public void setBankLogo(String bankLogo) {
        this.bankLogo = bankLogo;
    }
}