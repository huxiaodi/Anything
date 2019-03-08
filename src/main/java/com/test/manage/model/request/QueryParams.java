package com.test.manage.model.request;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class QueryParams extends Page {
    private String id;
    private String no;
    private Date startTime;
    private Date endTime;
    private BigDecimal startAmt; //起始金额
    private BigDecimal endAmt; // 结束金额
    private Integer tradeType; //交易类型
    private String type;
    private List<String> nos; //所有账户信息
    private String accountName; //交易对象
    private Integer accountType; //账户类型

    private Boolean income; //收入支出
    private String businessType; //业务类型
    private String source; //来源
    private String status; //状态
    private String keyword;
    private String tradeStatus;

    private String freeDayKind;
    private String freeDayCustomer;
    private String interestManagerKind;
    private String interestManagerCustomerName;
    private String interestManagerInd;

    public String getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
    public Integer getTradeType() {
        return tradeType;
    }

    public void setTradeType(Integer tradeType) {
        this.tradeType = tradeType;
    }
    public List<String> getNos() {
        return nos;
    }

    public void setNos(List<String> nos) {
        this.nos = nos;
    }
    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
    public BigDecimal getStartAmt() {
        return startAmt;
    }

    public void setStartAmt(BigDecimal startAmt) {
        this.startAmt = startAmt;
    }

    public BigDecimal getEndAmt() {
        return endAmt;
    }

    public void setEndAmt(BigDecimal endAmt) {
        this.endAmt = endAmt;
    }
    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
    public Boolean getIncome() {
        return income;
    }

    public void setIncome(Boolean income) {
        this.income = income;
    }
    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public Integer getAccountType() {
        return accountType;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    public String getFreeDayKind() {return freeDayKind;    }

    public void setFreeDayKind(String freeDayKind) {        this.freeDayKind = freeDayKind;    }

    public String getFreeDayCustomer() { return freeDayCustomer; }

    public void setFreeDayCustomer(String freeDayCustomer) { this.freeDayCustomer = freeDayCustomer; }

    public String getInterestManagerKind() {return interestManagerKind;}

    public void setInterestManagerKind(String interestManagerKind) {this.interestManagerKind = interestManagerKind;}

    public String getInterestManagerCustomerName() {
        return interestManagerCustomerName;
    }

    public void setInterestManagerCustomerName(String interestManagerCustomerName) {
        this.interestManagerCustomerName = interestManagerCustomerName;
    }

    public String getInterestManagerInd() {
        return interestManagerInd;
    }

    public void setInterestManagerInd(String interestManagerInd) {
        this.interestManagerInd = interestManagerInd;
    }
}
