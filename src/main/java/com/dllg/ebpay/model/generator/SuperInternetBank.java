package com.dllg.ebpay.model.generator;

/**
 *   超级网银号
 *   super_internet_bank
 * @mbg.generated do_not_delete_during_merge
 */
public class SuperInternetBank {
    /**
     *   银行编号
     *   super_internet_bank.bank_no
     * @mbg.generated
     */
    private String bankNo;

    /**
     *   银行名称
     *   super_internet_bank.bank_name
     * @mbg.generated
     */
    private String bankName;

    /**
     *   银行简称
     *   super_internet_bank.bank_alias_name
     * @mbg.generated
     */
    private String bankAliasName;

    /**
     *   1-只能是银联鉴权的银行 2-无限制
     *   super_internet_bank.bank_target
     * @mbg.generated
     */
    private Integer bankTarget;

    /**
     *   银行logo
     *   super_internet_bank.bank_logo
     * @mbg.generated
     */
    private String bankLogo;

    /**
     * 银行编号
     * @return the value of super_internet_bank.bank_no
     * @mbg.generated
     */
    public String getBankNo() {
        return bankNo;
    }

    /**
     * 银行编号
     * @param bankNo the value for super_internet_bank.bank_no
     * @mbg.generated
     */
    public void setBankNo(String bankNo) {
        this.bankNo = bankNo == null ? null : bankNo.trim();
    }

    /**
     * 银行名称
     * @return the value of super_internet_bank.bank_name
     * @mbg.generated
     */
    public String getBankName() {
        return bankName;
    }

    /**
     * 银行名称
     * @param bankName the value for super_internet_bank.bank_name
     * @mbg.generated
     */
    public void setBankName(String bankName) {
        this.bankName = bankName == null ? null : bankName.trim();
    }

    /**
     * 银行简称
     * @return the value of super_internet_bank.bank_alias_name
     * @mbg.generated
     */
    public String getBankAliasName() {
        return bankAliasName;
    }

    /**
     * 银行简称
     * @param bankAliasName the value for super_internet_bank.bank_alias_name
     * @mbg.generated
     */
    public void setBankAliasName(String bankAliasName) {
        this.bankAliasName = bankAliasName == null ? null : bankAliasName.trim();
    }

    /**
     * 1-只能是银联鉴权的银行 2-无限制
     * @return the value of super_internet_bank.bank_target
     * @mbg.generated
     */
    public Integer getBankTarget() {
        return bankTarget;
    }

    /**
     * 1-只能是银联鉴权的银行 2-无限制
     * @param bankTarget the value for super_internet_bank.bank_target
     * @mbg.generated
     */
    public void setBankTarget(Integer bankTarget) {
        this.bankTarget = bankTarget;
    }

    /**
     * 银行logo
     * @return the value of super_internet_bank.bank_logo
     * @mbg.generated
     */
    public String getBankLogo() {
        return bankLogo;
    }

    /**
     * 银行logo
     * @param bankLogo the value for super_internet_bank.bank_logo
     * @mbg.generated
     */
    public void setBankLogo(String bankLogo) {
        this.bankLogo = bankLogo == null ? null : bankLogo.trim();
    }
}