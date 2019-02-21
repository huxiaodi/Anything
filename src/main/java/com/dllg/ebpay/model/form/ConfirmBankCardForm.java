package com.dllg.ebpay.model.form;

/**
 * @Description：
 * @Author:hudi
 * @Date: Created in 10:51 2018/3/30
 */
public class ConfirmBankCardForm {
    private String accountSubId;
    private String bankCardNo;  // 注意 该字段的get方法中含有过滤方法
    private String verification;
    private String authentication;
    private String bankCardId;

    public String getBankCardId() {
        return bankCardId;
    }

    public void setBankCardId(String bankCardId) {
        this.bankCardId = bankCardId;
    }

    public String getAccountSubId() {
        return accountSubId;
    }

    public void setAccountSubId(String accountSubId) {
        this.accountSubId = accountSubId;
    }

    public String getBankCardNo() {
         return bankCardNo = bankCardNo.replaceAll("\\s*","");
    }

    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo;
    }

    public String getVerification() {
        return verification;
    }

    public void setVerification(String verification) {
        this.verification = verification;
    }

    public String getAuthentication() {
        return authentication;
    }

    public void setAuthentication(String authentication) {
        this.authentication = authentication;
    }
}
