package com.test.manage.model.dto;

import com.test.manage.model.generator.Resource;

/**
 * @Description：
 * @Author:hudi
 * @Date: Created in 17:16 2018/4/18
 */
public class ResourceDto extends Resource {
    private Integer childNum;

    public Integer getChildNum() {
        return childNum;
    }

    public void setChildNum(Integer childNum) {
        this.childNum = childNum;
    }
}
