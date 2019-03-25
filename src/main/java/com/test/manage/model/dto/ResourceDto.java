package com.test.manage.model.dto;

import com.test.manage.model.generator.Resource;

public class ResourceDto extends Resource {
    private Integer childNum;

    public Integer getChildNum() {
        return childNum;
    }

    public void setChildNum(Integer childNum) {
        this.childNum = childNum;
    }
}
