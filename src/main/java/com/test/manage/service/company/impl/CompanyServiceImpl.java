package com.test.manage.service.company.impl;

import com.test.manage.dao.custom.CompanyCuMapper;
import com.test.manage.dao.generator.CompanyMapper;
import com.test.manage.model.generator.Company;
import com.test.manage.model.request.QueryParams;
import com.test.manage.service.company.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 */
@Service
@Transactional
public class CompanyServiceImpl implements CompanyService{
    @Autowired
    private CompanyCuMapper companyCuMapper;
    @Autowired
    private CompanyMapper companyMapper;

    /**
     * 管理端 企业列表
     */
    @Override
    public List manageList(QueryParams queryParams) {
        return companyCuMapper.manageList(queryParams);
    }
    @Override
    public Company selectByPrimaryKey(String id) {
        return companyMapper.selectByPrimaryKey(id);
    }
    @Override
    public void updateByPrimaryKeySelective(Company company) {
        companyMapper.updateByPrimaryKeySelective(company);
    }
}
