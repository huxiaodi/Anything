package com.dllg.ebpay.model.dto;

import com.dllg.ebpay.model.generator.AccountSubTrade;

/**
 * @Description：
 * @Author:hudi
 * @Date: Created in 14:35 2018/4/13
 */
public class AccountSubTradeDto extends AccountSubTrade {

    private String  accountSubTradeAdjustUserName;
    private String  businessTradeBusinessNo;
    private String  accountSubName;
    private String  accountSubNo;
    private String percentage;
    private String accountSubTradeInAccountSubName;

    public String getAccountSubName() {
        return accountSubName;
    }

    public void setAccountSubName(String accountSubName) {
        this.accountSubName = accountSubName;
    }

    public String getAccountSubNo() {
        return accountSubNo;
    }

    public void setAccountSubNo(String accountSubNo) {
        this.accountSubNo = accountSubNo;
    }

    public String getAccountSubTradeAdjustUserName() {
        return accountSubTradeAdjustUserName;
    }

    public void setAccountSubTradeAdjustUserName(String accountSubTradeAdjustUserName) {
        this.accountSubTradeAdjustUserName = accountSubTradeAdjustUserName;
    }

    public String getBusinessTradeBusinessNo() {
        return businessTradeBusinessNo;
    }

    public void setBusinessTradeBusinessNo(String businessTradeBusinessNo) {
        this.businessTradeBusinessNo = businessTradeBusinessNo;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    public String getAccountSubTradeInAccountSubName() {
        return accountSubTradeInAccountSubName;
    }

    public void setAccountSubTradeInAccountSubName(String accountSubTradeInAccountSubName) {
        this.accountSubTradeInAccountSubName = accountSubTradeInAccountSubName;
    }
}


