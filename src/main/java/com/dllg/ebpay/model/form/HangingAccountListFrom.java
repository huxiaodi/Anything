package com.dllg.ebpay.model.form;

import com.dllg.ebpay.model.request.Page;
import com.dllg.framework.model.BaseForm;

import java.util.Date;

/**
 * @Description：
 * @Author:hudi
 * @Date: Created in 10:18 2018/4/12
 */
public class HangingAccountListFrom extends Page {
    private Date startTime; // 开始时间
    private Date endTime;  // 结束时间
    private Integer tranStatus; // 交易状态
    private Integer type; // 充值渠道
    private String accountSubId; // 子账户名称
    private String keyword; // 关键字


    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
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

    public Integer getTranStatus() {
        return tranStatus;
    }

    public void setTranStatus(Integer tranStatus) {
        this.tranStatus = tranStatus;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getAccountSubId() {
        return accountSubId;
    }

    public void setAccountSubId(String accountSubId) {
        this.accountSubId = accountSubId;
    }
}
