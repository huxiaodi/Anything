package com.dllg.framework.model;

/**
 * Created by hugo on 2016/5/5.
 */
public class BaseForm {
    //当前页
    private int pageNum;
    //每页的数量
    private int pageSize;
    //排序
    private String orderBy;
    //关键字
    private String keyword;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        if(pageSize == 0){
            pageSize = 10;
        }
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

}
