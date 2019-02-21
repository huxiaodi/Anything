package com.dllg.ebpay.web.admin;

import com.dllg.ebpay.model.form.ServiceChargeForm;
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
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * 交易管理
 */
@RestController
@RequestMapping("admin/tradeManage")
public class TradeManageController extends BaseController {

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
        if (StringUtils.isNotBlank(id)) {
            AccountSub accountSub = accountSubService.selectByPrimaryKey(id);
            String accountSubNo = accountSub.getAccountSubNo();
            queryParams.setNos(Arrays.asList(accountSubNo));
        }

        queryParams.startPage();
        List list = accountSubTradeService.getTradeRecordList(queryParams);
        return success(new PageInfo<>(list));
    }

    /**
     * 列表
     */
    @PostMapping("/listAdmin")
    public ApiResult listAdmin(@RequestBody(required = false) QueryParams queryParams) {
        queryParams.startPage();
        List list = accountSubTradeService.getTradeRecordListAdmin(queryParams);
        return success(new PageInfo<>(list));
    }

    @PostMapping("/getServiceChargeList")
    public ApiResult getServiceChargeList(@RequestBody ServiceChargeForm serviceChargeForm) {
        return success(new PageInfo<>(accountSubTradeService.getServiceChargeList(serviceChargeForm)));
    }

    /**
     * 获取手续费账户管理的 交易对象
     * @return
     */
    @GetMapping("/getServiceChargeAccountName")
    public ApiResult getServiceChargeAccountName() {
        return success(accountSubTradeService.getServiceChargeAccountName());
    }

}
