package com.dllg.ebpay.model.form;

/**
 * @Description：
 * @Author:hudi
 * @Date: Created in 9:55 2018/3/29
 */
public class BindBankCardForm {
    private String accountSubId;
    private Integer bankCardTransactionAmountType;
    private String accountName;
    private String bankCardDocumentNo;
    private String bankCardBank;
    private String bankCardBankName;
    private String bankCardBankBranch;
    private String bankCardBankBranchName;
    private String bankCardReserveBankAccountName;
    private String bankCardNo;  // 注意 该字段的get方法中含有过滤方法
    private String bankLogo;
    private String mobilePhone;

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public Integer getBankCardTransactionAmountType() {
        return bankCardTransactionAmountType;
    }

    public void setBankCardTransactionAmountType(Integer bankCardTransactionAmountType) {
        this.bankCardTransactionAmountType = bankCardTransactionAmountType;
    }

    public String getBankCardBankName() {
        return bankCardBankName;
    }

    public void setBankCardBankName(String bankCardBankName) {
        this.bankCardBankName = bankCardBankName;
    }

    public String getBankCardBankBranchName() {
        return bankCardBankBranchName;
    }

    public void setBankCardBankBranchName(String bankCardBankBranchName) {
        this.bankCardBankBranchName = bankCardBankBranchName;
    }

    public String getAccountSubId() {
        return accountSubId;
    }

    public void setAccountSubId(String accountSubId) {
        this.accountSubId = accountSubId;
    }


    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getBankCardDocumentNo() {
        return bankCardDocumentNo;
    }

    public void setBankCardDocumentNo(String bankCardDocumentNo) {
        this.bankCardDocumentNo = bankCardDocumentNo;
    }

    public String getBankCardBank() {
        return bankCardBank;
    }

    public void setBankCardBank(String bankCardBank) {
        this.bankCardBank = bankCardBank;
    }

    public String getBankCardBankBranch() {
        return bankCardBankBranch;
    }

    public void setBankCardBankBranch(String bankCardBankBranch) {
        this.bankCardBankBranch = bankCardBankBranch;
    }

    public String getBankCardReserveBankAccountName() {
        return bankCardReserveBankAccountName;
    }

    public void setBankCardReserveBankAccountName(String bankCardReserveBankAccountName) {
        this.bankCardReserveBankAccountName = bankCardReserveBankAccountName;
    }

    public String getBankCardNo() {
        return bankCardNo = bankCardNo.replaceAll("\\s*","");
    }

    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo;
    }

    public String getBankLogo() {
        return bankLogo;
    }

    public void setBankLogo(String bankLogo) {
        this.bankLogo = bankLogo;
    }
}
