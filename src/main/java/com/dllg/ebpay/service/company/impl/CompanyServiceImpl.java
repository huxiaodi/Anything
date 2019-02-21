package com.dllg.ebpay.service.company.impl;

import com.dllg.ebpay.dao.custom.CompanyCuMapper;
import com.dllg.ebpay.dao.generator.CompanyMapper;
import com.dllg.ebpay.model.generator.Company;
import com.dllg.ebpay.model.request.QueryParams;
import com.dllg.ebpay.service.company.CompanyService;
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
