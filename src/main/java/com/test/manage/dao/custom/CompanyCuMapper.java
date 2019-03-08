package com.test.manage.dao.custom;

import com.test.manage.model.request.QueryParams;

import java.util.List;

public interface CompanyCuMapper{

    /**
     * 管理端 企业列表
     */
    List manageList(QueryParams queryParams);
}
