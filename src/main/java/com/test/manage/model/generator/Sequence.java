package com.test.manage.model.generator;

import java.util.Date;

/**
 *   序列表
 *   sequence
 * @mbg.generated do_not_delete_during_merge
 */
public class Sequence {
    /**
     *   sequence.sequence_id
     * @mbg.generated
     */
    private Integer sequenceId;

    /**
     *   名称
     *   sequence.sequence_name
     * @mbg.generated
     */
    private String sequenceName;

    /**
     *   描述
     *   sequence.sequence_desc
     * @mbg.generated
     */
    private String sequenceDesc;

    /**
     *   创建时间
     *   sequence.sequence_create_time
     * @mbg.generated
     */
    private Date sequenceCreateTime;

    /**
     *   更新时间
     *   sequence.sequence_update_time
     * @mbg.generated
     */
    private Date sequenceUpdateTime;

    /**
     * @return the value of sequence.sequence_id
     * @mbg.generated
     */
    public Integer getSequenceId() {
        return sequenceId;
    }

    /**
     * @param sequenceId the value for sequence.sequence_id
     * @mbg.generated
     */
    public void setSequenceId(Integer sequenceId) {
        this.sequenceId = sequenceId;
    }

    /**
     * 名称
     * @return the value of sequence.sequence_name
     * @mbg.generated
     */
    public String getSequenceName() {
        return sequenceName;
    }

    /**
     * 名称
     * @param sequenceName the value for sequence.sequence_name
     * @mbg.generated
     */
    public void setSequenceName(String sequenceName) {
        this.sequenceName = sequenceName == null ? null : sequenceName.trim();
    }

    /**
     * 描述
     * @return the value of sequence.sequence_desc
     * @mbg.generated
     */
    public String getSequenceDesc() {
        return sequenceDesc;
    }

    /**
     * 描述
     * @param sequenceDesc the value for sequence.sequence_desc
     * @mbg.generated
     */
    public void setSequenceDesc(String sequenceDesc) {
        this.sequenceDesc = sequenceDesc == null ? null : sequenceDesc.trim();
    }

    /**
     * 创建时间
     * @return the value of sequence.sequence_create_time
     * @mbg.generated
     */
    public Date getSequenceCreateTime() {
        return sequenceCreateTime;
    }

    /**
     * 创建时间
     * @param sequenceCreateTime the value for sequence.sequence_create_time
     * @mbg.generated
     */
    public void setSequenceCreateTime(Date sequenceCreateTime) {
        this.sequenceCreateTime = sequenceCreateTime;
    }

    /**
     * 更新时间
     * @return the value of sequence.sequence_update_time
     * @mbg.generated
     */
    public Date getSequenceUpdateTime() {
        return sequenceUpdateTime;
    }

    /**
     * 更新时间
     * @param sequenceUpdateTime the value for sequence.sequence_update_time
     * @mbg.generated
     */
    public void setSequenceUpdateTime(Date sequenceUpdateTime) {
        this.sequenceUpdateTime = sequenceUpdateTime;
    }
}