package com.dllg.ebpay.model.response;

public class WithdrawalBankCardResponse {
    // Id
    private String bankCardId;
    // 银行卡号
    private String bankCardNo;
    // 开户银行
    private String bankCardBank;

    public String getBankCardId() {
        return bankCardId;
    }

    public void setBankCardId(String bankCardId) {
        this.bankCardId = bankCardId;
    }

    public String getBankCardNo() {
        return bankCardNo;
    }

    public void setBankCardNo(String bankCardNo) {
        this.bankCardNo = bankCardNo;
    }

    public String getBankCardBank() {
        return bankCardBank;
    }

    public void setBankCardBank(String bankCardBank) {
        this.bankCardBank = bankCardBank;
    }
}
