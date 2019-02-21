package com.dllg.ebpay.model.request;

import com.dllg.pabank.model.request.MemberTransactionRequest;

public class TransferRequest extends MemberTransactionRequest {

    private Integer accountType;

    private String tradeId;

    private Boolean sendSMS;

    public Boolean getSendSMS() {
        return sendSMS;
    }

    public void setSendSMS(Boolean sendSMS) {
        this.sendSMS = sendSMS;
    }

    public Integer getAccountType() {
        return accountType;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    public String getTradeId() {
        return tradeId;
    }

    public void setTradeId(String tradeId) {
        this.tradeId = tradeId;
    }
}
