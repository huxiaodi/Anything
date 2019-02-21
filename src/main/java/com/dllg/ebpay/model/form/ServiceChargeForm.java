package com.dllg.ebpay.model.form;

import com.dllg.ebpay.model.request.Page;

import java.util.Date;

/**
 * @Description：
 * @Author:hudi
 * @Date: Created in 10:56 2018/4/17
 */
public class ServiceChargeForm extends Page {

    private Date startTime; // 开始时间
    private Date endTime;   // 结束时间
    private String relationId;  // 交易对象（因为目前每个账户只有一个子账户 所以暂时用了子账户id 和子账户里面的名称作为唯一）
    private Integer type;   //
    private String keyword;

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
