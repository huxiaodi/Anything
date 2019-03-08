package com.test.manage.model.form;

import com.test.framework.model.BaseForm;

import java.util.Date;

public class FpMainForm extends BaseForm {

    private Date startTime;
    private Date endTime;
    private String keyword;
    private String state;

    private String codeDetail;   //过滤明细页数据

    private String[] djhs;

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

    @Override
    public String getKeyword() {
        return keyword;
    }

    @Override
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }


    public String[] getDjhs() {
        return djhs;
    }

    public void setDjhs(String[] djhs) {
        this.djhs = djhs;
    }

    public String getCodeDetail() {
        return codeDetail;
    }

    public void setCodeDetail(String codeDetail) {
        this.codeDetail = codeDetail;
    }
}
