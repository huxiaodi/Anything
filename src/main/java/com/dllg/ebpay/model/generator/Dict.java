package com.dllg.ebpay.model.generator;

import java.util.Date;

/**
 *   字典
 *   dict
 * @mbg.generated do_not_delete_during_merge
 */
public class Dict {
    /**
     *   id
     *   dict.dict_id
     * @mbg.generated
     */
    private String dictId;

    /**
     *   字典作用域，0-系统字典，1-用户管理字典
     *   dict.dict_scope
     * @mbg.generated
     */
    private Integer dictScope;

    /**
     *   字典类型
     *   dict.dict_type
     * @mbg.generated
     */
    private String dictType;

    /**
     *   字典父id
     *   dict.dict_parent_id
     * @mbg.generated
     */
    private String dictParentId;

    /**
     *   字典key
     *   dict.dict_key
     * @mbg.generated
     */
    private String dictKey;



    /**
     *   字典值
     *   dict.dict_value
     * @mbg.generated
     */
    private String dictValue;

    /**
     *   字典描述
     *   dict.dict_desc
     * @mbg.generated
     */
    private String dictDesc;

    /**
     *   创建人
     *   dict.dict_create_user_id
     * @mbg.generated
     */
    private String dictCreateUserId;

    /**
     *   创建时间
     *   dict.dict_create_time
     * @mbg.generated
     */
    private Date dictCreateTime;

    /**
     *   修改人
     *   dict.dict_update_user_id
     * @mbg.generated
     */
    private String dictUpdateUserId;

    /**
     *   修改时间
     *   dict.dict_update_time
     * @mbg.generated
     */
    private Date dictUpdateTime;

    /**
     *   是否删除 0-否,1-是
     *   dict.dict_is_delete
     * @mbg.generated
     */
    private Boolean dictIsDelete;

    /**
     * id
     * @return the value of dict.dict_id
     * @mbg.generated
     */
    public String getDictId() {
        return dictId;
    }

    /**
     * id
     * @param dictId the value for dict.dict_id
     * @mbg.generated
     */
    public void setDictId(String dictId) {
        this.dictId = dictId == null ? null : dictId.trim();
    }

    /**
     * 字典作用域，0-系统字典，1-用户管理字典
     * @return the value of dict.dict_scope
     * @mbg.generated
     */
    public Integer getDictScope() {
        return dictScope;
    }

    /**
     * 字典作用域，0-系统字典，1-用户管理字典
     * @param dictScope the value for dict.dict_scope
     * @mbg.generated
     */
    public void setDictScope(Integer dictScope) {
        this.dictScope = dictScope;
    }





    /**
     * 字典父id
     * @return the value of dict.dict_parent_id
     * @mbg.generated
     */
    public String getDictParentId() {
        return dictParentId;
    }

    /**
     * 字典父id
     * @param dictParentId the value for dict.dict_parent_id
     * @mbg.generated
     */
    public void setDictParentId(String dictParentId) {
        this.dictParentId = dictParentId == null ? null : dictParentId.trim();
    }

    /**
     * 字典key
     * @return the value of dict.dict_key
     * @mbg.generated
     */
    public String getDictKey() {
        return dictKey;
    }

    /**
     * 字典key
     * @param dictKey the value for dict.dict_key
     * @mbg.generated
     */
    public void setDictKey(String dictKey) {
        this.dictKey = dictKey == null ? null : dictKey.trim();
    }

    /**
     * 字典值
     * @return the value of dict.dict_value
     * @mbg.generated
     */
    public String getDictValue() {
        return dictValue;
    }

    /**
     * 字典值
     * @param dictValue the value for dict.dict_value
     * @mbg.generated
     */
    public void setDictValue(String dictValue) {
        this.dictValue = dictValue == null ? null : dictValue.trim();
    }

    /**
     * 字典描述
     * @return the value of dict.dict_desc
     * @mbg.generated
     */
    public String getDictDesc() {
        return dictDesc;
    }

    /**
     * 字典描述
     * @param dictDesc the value for dict.dict_desc
     * @mbg.generated
     */
    public void setDictDesc(String dictDesc) {
        this.dictDesc = dictDesc == null ? null : dictDesc.trim();
    }

    /**
     * 创建人
     * @return the value of dict.dict_create_user_id
     * @mbg.generated
     */
    public String getDictCreateUserId() {
        return dictCreateUserId;
    }

    /**
     * 创建人
     * @param dictCreateUserId the value for dict.dict_create_user_id
     * @mbg.generated
     */
    public void setDictCreateUserId(String dictCreateUserId) {
        this.dictCreateUserId = dictCreateUserId == null ? null : dictCreateUserId.trim();
    }

    /**
     * 创建时间
     * @return the value of dict.dict_create_time
     * @mbg.generated
     */
    public Date getDictCreateTime() {
        return dictCreateTime;
    }

    /**
     * 创建时间
     * @param dictCreateTime the value for dict.dict_create_time
     * @mbg.generated
     */
    public void setDictCreateTime(Date dictCreateTime) {
        this.dictCreateTime = dictCreateTime;
    }

    /**
     * 修改人
     * @return the value of dict.dict_update_user_id
     * @mbg.generated
     */
    public String getDictUpdateUserId() {
        return dictUpdateUserId;
    }

    /**
     * 修改人
     * @param dictUpdateUserId the value for dict.dict_update_user_id
     * @mbg.generated
     */
    public void setDictUpdateUserId(String dictUpdateUserId) {
        this.dictUpdateUserId = dictUpdateUserId == null ? null : dictUpdateUserId.trim();
    }

    /**
     * 修改时间
     * @return the value of dict.dict_update_time
     * @mbg.generated
     */
    public Date getDictUpdateTime() {
        return dictUpdateTime;
    }

    /**
     * 修改时间
     * @param dictUpdateTime the value for dict.dict_update_time
     * @mbg.generated
     */
    public void setDictUpdateTime(Date dictUpdateTime) {
        this.dictUpdateTime = dictUpdateTime;
    }

    /**
     * 是否删除 0-否,1-是
     * @return the value of dict.dict_is_delete
     * @mbg.generated
     */
    public Boolean getDictIsDelete() {
        return dictIsDelete;
    }

    /**
     * 是否删除 0-否,1-是
     * @param dictIsDelete the value for dict.dict_is_delete
     * @mbg.generated
     */
    public void setDictIsDelete(Boolean dictIsDelete) {
        this.dictIsDelete = dictIsDelete;
    }

    public String getDictType() {
        return dictType;
    }

    public void setDictType(String dictType) {
        this.dictType = dictType;
    }
}