package com.dllg.ebpay.dao.custom;

import com.dllg.ebpay.model.request.QueryParams;

import java.util.List;

public interface CompanyCuMapper{

    /**
     * 管理端 企业列表
     */
    List manageList(QueryParams queryParams);
}
