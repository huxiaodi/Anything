package com.dllg.ebpay.model.generator;

import java.math.BigDecimal;
import java.util.Date;

/**
 *   利率字典
 *   interestDict
 * @mbg.generated do_not_delete_during_merge
 */
public class InterestDict {

    private String interestId;


    private String interestDate;


    private BigDecimal interestValue;


    private String interestFile;


    private String interestFileUrl;


    private String createUserId;


    private Date createTime;


    private String updateUserId;


    private Date updateTime;




    public String getInterestId() { return interestId; }

    public void setInterestId(String interestId) { this.interestId = interestId; }

    public String getInterestDate() { return interestDate; }

    public void setInterestDate(String interestDate) { this.interestDate = interestDate; }

    public BigDecimal getInterestValue() { return interestValue; }

    public void setInterestValue(BigDecimal interestValue) { this.interestValue = interestValue; }

    public String getInterestFile() { return interestFile; }

    public void setInterestFile(String interestFile) { this.interestFile = interestFile; }

    public String getInterestFileUrl() {
        return interestFileUrl;
    }

    public void setInterestFileUrl(String interestFileUrl) {
        this.interestFileUrl = interestFileUrl;
    }

    public String getCreateUserId() { return createUserId; }

    public void setCreateUserId(String createUserId) { this.createUserId = createUserId; }

    public Date getCreateTime() { return createTime; }

    public void setCreateTime(Date createTime) { this.createTime = createTime; }

    public String getUpdateUserId() { return updateUserId; }

    public void setUpdateUserId(String updateUserId) { this.updateUserId = updateUserId; }

    public Date getUpdateTime() { return updateTime; }

    public void setUpdateTime(Date updateTime) { this.updateTime = updateTime; }
}