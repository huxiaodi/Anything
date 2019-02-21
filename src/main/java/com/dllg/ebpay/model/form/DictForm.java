package com.dllg.ebpay.model.form;

import com.dllg.ebpay.model.generator.User;
import com.dllg.framework.model.BaseForm;

import java.util.Date;

public class DictForm extends BaseForm {

    private String dictKey;
    private String dictValue;
    private String dictType;
    private String dictDesc;
    private String dictCreateUserId;
    private Date dictCreateTime;

    public String getDictKey() {
        return dictKey;
    }

    public void setDictKey(String dictKey) {
        this.dictKey = dictKey;
    }

    public String getDictValue() {
        return dictValue;
    }

    public void setDictValue(String dictValue) {
        this.dictValue = dictValue;
    }

    public String getDictType() {
        return dictType;
    }

    public void setDictType(String dictType) {
        this.dictType = dictType;
    }

    public String getDictDesc() {
        return dictDesc;
    }

    public void setDictDesc(String dictDesc) {
        this.dictDesc = dictDesc;
    }

    public String getDictCreateUserId() {
        return dictCreateUserId;
    }

    public void setDictCreateUserId(String dictCreateUserId) {
        this.dictCreateUserId = dictCreateUserId;
    }

    public Date getDictCreateTime() {
        return dictCreateTime;
    }

    public void setDictCreateTime(Date dictCreateTime) {
        this.dictCreateTime = dictCreateTime;
    }
}
