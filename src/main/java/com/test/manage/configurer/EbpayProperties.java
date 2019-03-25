package com.test.manage.configurer;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "awesome.ebpay")
@EnableConfigurationProperties(EbpayProperties.class)
public class EbpayProperties {
    private String mrchCode;
    private String masterId;

    private String smsAccount;

    private String smsPwd;

    private String smsSignature;
    private String staticUrl;

    private String staticPath;

    private String projectPath;

    private String setPasswordBackUrl;
    private String transferReturnUrl;
    private String tradeCallbackUrl;
    private String paymentDisposeReturnUrl;
    private String businessCallBackUrl;

    private String frontUrl;
    private String topupUrl;

    private String returnback;

    private String returnbackForBusiness;

    private String reimburseBackUrl;

    private String uid;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getReimburseBackUrl() {
        return reimburseBackUrl;
    }

    public void setReimburseBackUrl(String reimburseBackUrl) {
        this.reimburseBackUrl = reimburseBackUrl;
    }

    public String getReturnback() {
        return returnback;
    }

    public void setReturnback(String returnback) {
        this.returnback = returnback;
    }

    public String getReturnbackForBusiness() {
        return returnbackForBusiness;
    }

    public void setReturnbackForBusiness(String returnbackForBusiness) {
        this.returnbackForBusiness = returnbackForBusiness;
    }

    public String getMasterId() {
        return masterId;
    }

    public void setMasterId(String masterId) {
        this.masterId = masterId;
    }

    public String getTopupUrl() {
        return topupUrl;
    }

    public void setTopupUrl(String topupUrl) {
        this.topupUrl = topupUrl;
    }

    public String getFrontUrl() {
        return frontUrl;
    }

    public void setFrontUrl(String frontUrl) {
        this.frontUrl = frontUrl;
    }

    public String getBusinessCallBackUrl() {
        return businessCallBackUrl;
    }

    public void setBusinessCallBackUrl(String businessCallBackUrl) {
        this.businessCallBackUrl = businessCallBackUrl;
    }

    public String getPaymentDisposeReturnUrl() { return paymentDisposeReturnUrl; }

    public void setPaymentDisposeReturnUrl(String paymentDisposeReturnUrl) { this.paymentDisposeReturnUrl = paymentDisposeReturnUrl; }

    public String getTransferReturnUrl() {
        return transferReturnUrl;
    }

    public void setTransferReturnUrl(String transferReturnUrl) {
        this.transferReturnUrl = transferReturnUrl;
    }


    public String getSetPasswordBackUrl() {
        return setPasswordBackUrl;
    }

    public void setSetPasswordBackUrl(String setPasswordBackUrl) {
        this.setPasswordBackUrl = setPasswordBackUrl;
    }

    public String getTradeCallbackUrl() {
        return tradeCallbackUrl;
    }

    public void setTradeCallbackUrl(String tradeCallbackUrl) {
        this.tradeCallbackUrl = tradeCallbackUrl;
    }

    public String getStaticUrl() {
        return staticUrl;
    }

    public void setStaticUrl(String staticUrl) {
        this.staticUrl = staticUrl;
    }

    public String getStaticPath() {
        return staticPath;
    }

    public void setStaticPath(String staticPath) {
        this.staticPath = staticPath;
    }

    public String getProjectPath() {
        return projectPath;
    }

    public void setProjectPath(String projectPath) {
        this.projectPath = projectPath;
    }

    //资金汇总账号
    private String fundSummaryAcctNo;

    public String getFundSummaryAcctNo() {
        return fundSummaryAcctNo;
    }

    public void setFundSummaryAcctNo(String fundSummaryAcctNo) {
        this.fundSummaryAcctNo = fundSummaryAcctNo;
    }

    public String getSmsAccount() {
        return smsAccount;
    }

    public void setSmsAccount(String smsAccount) {
        this.smsAccount = smsAccount;
    }

    public String getSmsPwd() {
        return smsPwd;
    }

    public void setSmsPwd(String smsPwd) {
        this.smsPwd = smsPwd;
    }

    public String getSmsSignature() {
        return smsSignature;
    }

    public void setSmsSignature(String smsSignature) {
        this.smsSignature = smsSignature;
    }

    public String getMrchCode() {
        return mrchCode;
    }

    public void setMrchCode(String mrchCode) {
        this.mrchCode = mrchCode;
    }
}
