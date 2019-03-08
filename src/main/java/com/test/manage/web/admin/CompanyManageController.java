package com.test.manage.web.admin;

import com.test.manage.model.generator.Company;
import com.test.manage.model.request.QueryParams;
import com.test.manage.service.company.CompanyService;
import com.test.framework.controller.BaseController;
import com.test.framework.model.ApiResult;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 个人会员管理
 */
@RestController
@RequestMapping("admin/companyManage")
public class CompanyManageController extends BaseController {

    @Autowired
    private CompanyService companyService;


    /**
     * 用户列表
     */
    @PostMapping("/list")
    public ApiResult list(@RequestBody(required = false) QueryParams queryParams) {
        queryParams.startPage();
        List list = companyService.manageList(queryParams);
        return success(new PageInfo<>(list));
    }
    /**
     * 修改前
     */
    @PostMapping("/company/info")
    public ApiResult companyInfo(@RequestBody(required = false) QueryParams queryParams) {
        final String id = queryParams.getId();
        if(StringUtils.isBlank(id)){
            return error("参数错误");
        }
        Company company = companyService.selectByPrimaryKey(id);

        return success(company);
    }
    /**
     * 修改前
     */
    @PostMapping("/company/save")
    public ApiResult companyInfo(@RequestBody(required = false) Company company) {
        final String id = company.getCompanyId();
        if(StringUtils.isBlank(id)){
            return error("参数错误");
        }
        Company c = new Company();
        c.setCompanyId(company.getCompanyId());
        c.setCompanyPrincipalName(company.getCompanyPrincipalName());
        c.setCompanyPrincipalIdCode(company.getCompanyPrincipalIdCode());
        c.setCompanyPrincipalTel(company.getCompanyPrincipalTel());
        c.setCompanyTel(company.getCompanyTel());
        c.setCompanyName(company.getCompanyName());
        c.setCompanyToPublicBank(company.getCompanyToPublicBank());
        c.setCompanyToPublicAccountBank(company.getCompanyToPublicAccountBank());
        c.setCompanyShortName(company.getCompanyShortName());
        c.setCompanyContact(company.getCompanyContact());
        c.setCompanyContactTel(company.getCompanyContactTel());
        c.setCompanyContactIdCard(company.getCompanyContactIdCard());
        c.setCompanyTaxpayerRegistrationNumber(company.getCompanyTaxpayerRegistrationNumber());
        c.setCompanyFax(company.getCompanyFax());
        c.setCompanyRegistedCapital(company.getCompanyRegistedCapital());
        c.setCompanyEmail(company.getCompanyEmail());
        c.setCompanyProvince(company.getCompanyProvince());
        c.setCompanyCity(company.getCompanyCity());
        c.setCompanyDistrict(company.getCompanyDistrict());
        c.setCompanyUnifiedSocialImg(company.getCompanyUnifiedSocialImg());
        companyService.updateByPrimaryKeySelective(c);
        return success("信息修改成功");
    }

}
