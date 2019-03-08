package com.test.manage.service.company;

import com.test.manage.model.generator.Company;
import com.test.manage.model.request.QueryParams;

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
