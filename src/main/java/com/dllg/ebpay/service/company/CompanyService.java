package com.dllg.ebpay.service.company;

import com.dllg.ebpay.model.generator.Company;
import com.dllg.ebpay.model.request.QueryParams;

import java.util.List;

/**
 *
 */
public interface CompanyService {
    /**
     * 管理端 企业列表
     */
    List manageList(QueryParams queryParams);

    Company selectByPrimaryKey(String id);

    void updateByPrimaryKeySelective(Company company);
}
