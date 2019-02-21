package com.dllg.ebpay.model.form;

import com.dllg.framework.model.BaseForm;

import java.util.Date;

/**
 * @Description：
 * @Author:hudi
 * @Date: Created in 14:39 2018/8/7
 */
public class PayApplicationForm extends BaseForm {

    private String applicationTradeTrasactionFrom;
    private String applicationTradeTrasactionObject;
    private Integer applicationStatus;
    private String relationId;
    private String keyword;
    private Date startTime;
    private Date endTime;

    public String getApplicationTradeTrasactionFrom() {
        return applicationTradeTrasactionFrom;
    }

    public void setApplicationTradeTrasactionFrom(String applicationTradeTrasactionFrom) {
        this.applicationTradeTrasactionFrom = applicationTradeTrasactionFrom;
    }

    public Integer getApplicationStatus() {
        return applicationStatus;
    }

    public void setApplicationStatus(Integer applicationStatus) {
        this.applicationStatus = applicationStatus;
    }

    public String getApplicationTradeTrasactionObject() {
        return applicationTradeTrasactionObject;
    }

    public void setApplicationTradeTrasactionObject(String applicationTradeTrasactionObject) {
        this.applicationTradeTrasactionObject = applicationTradeTrasactionObject;
    }

    @Override
    public String getKeyword() {
        return keyword;
    }

    @Override
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

    public String getRelationId() {
        return relationId;
    }

    public void setRelationId(String relationId) {
        this.relationId = relationId;
    }
}
