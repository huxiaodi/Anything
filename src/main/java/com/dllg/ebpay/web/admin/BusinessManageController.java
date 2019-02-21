package com.dllg.ebpay.web.admin;

import com.dllg.ebpay.model.generator.AccountSub;
import com.dllg.ebpay.model.request.QueryParams;
import com.dllg.ebpay.service.accountSub.AccountSubService;
import com.dllg.ebpay.service.company.CompanyService;
import com.dllg.ebpay.service.pay.AccountSubTradeService;
import com.dllg.ebpay.service.user.UserService;
import com.dllg.framework.controller.BaseController;
import com.dllg.framework.model.ApiResult;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * 业务管理
 */
@RestController
@RequestMapping("admin/businessManage")
public class BusinessManageController extends BaseController {

    @Autowired
    private AccountSubService accountSubService;
    @Autowired
    private AccountSubTradeService accountSubTradeService;
    @Autowired
    private UserService userService;
    @Autowired
    private CompanyService companyService;

    /**
     * 列表
     */
    @PostMapping("/list")
    public ApiResult list(@RequestBody(required = false) QueryParams queryParams) {
        String id = queryParams.getId();
        if(StringUtils.isNotBlank(id)){
            AccountSub accountSub = accountSubService.selectByPrimaryKey(id);
            String accountSubNo = accountSub.getAccountSubNo();
            queryParams.setNos(Arrays.asList(accountSubNo));
        }

        queryParams.startPage();
        List list = accountSubTradeService.getTradeBusinessList(queryParams);
        return success(new PageInfo<>(list));
    }

    /**
     * 列表
     */
    @PostMapping("/listAdmin")
    public ApiResult listAdmin(@RequestBody(required = false) QueryParams queryParams) {
        queryParams.startPage();
        List list = accountSubTradeService.getTradeBusinessListAdmin(queryParams);
        return success(new PageInfo<>(list));
    }




}
