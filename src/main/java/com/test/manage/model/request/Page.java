package com.test.manage.model.request;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

public class Page<T> extends PageInfo<T> {
    private static final long serialVersionUID = -4681422765755298032L;

    /**
     * 默认分页 10条
     */
    public void startPage(){
        int pageNum = this.getPageNum();
        int pageSize = this.getPageSize();
        if(pageSize <=0 || pageSize > 1000){
            pageSize = 10;
        }
        PageHelper.startPage(pageNum, pageSize);
    }
}