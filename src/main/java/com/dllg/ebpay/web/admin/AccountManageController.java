package com.dllg.ebpay.web.admin;

import com.dllg.ebpay.model.generator.AccountSub;
import com.dllg.ebpay.model.generator.Company;
import com.dllg.ebpay.model.generator.User;
import com.dllg.ebpay.model.request.QueryParams;
import com.dllg.ebpay.service.account.UserAccountService;
import com.dllg.ebpay.service.accountSub.AccountSubService;
import com.dllg.ebpay.service.bankCard.LocalBankCardService;
import com.dllg.ebpay.service.company.CompanyService;
import com.dllg.ebpay.service.pay.AccountSubTradeService;
import com.dllg.ebpay.service.user.UserService;
import com.dllg.framework.controller.BaseController;
import com.dllg.framework.model.ApiResult;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 账户管理
 */
@RestController
@RequestMapping("admin/accountManage")
public class AccountManageController extends BaseController {

    @Autowired
    private AccountSubService accountSubService;
    @Autowired
    private LocalBankCardService localBankCardService;
    @Autowired
    private AccountSubTradeService accountSubTradeService;
    @Autowired
    private UserService userService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private UserAccountService userAccountService;

    /**
     * 账户
     */
    @PostMapping("/list")
    public ApiResult list(@RequestBody(required = false) QueryParams queryParams) throws Exception{
        queryParams.startPage();
        List list = accountSubService.manageList(queryParams);
        return success(new PageInfo<>(list));
    }

    /**
     * 启用
     */
    @PostMapping("/switch/enable")
    public ApiResult enable(@RequestBody(required = false) QueryParams queryParams) {
        final String id = queryParams.getId();
        if (StringUtils.isBlank(id)) {
            return error("参数错误");
        }
        AccountSub accountSub = accountSubService.selectByPrimaryKey(id);
        Integer userStatus = accountSub.getAccountSubStatus();
        if (userStatus != null && userStatus.intValue() == 1) {
            return error("该账户已启用，请勿重复操作");
        }
        AccountSub u = new AccountSub();
        u.setAccountSubId(id);
        u.setAccountSubStatus(1);
        accountSubService.updateByPrimaryKeySelective(u);
        return success("操作成功");
    }

    /**
     * 禁用
     */
    @PostMapping("/switch/disable")
    public ApiResult disable(@RequestBody(required = false) QueryParams queryParams) {
        final String id = queryParams.getId();
        if (StringUtils.isBlank(id)) {
            return error("参数错误");
        }
        AccountSub accountSub = accountSubService.selectByPrimaryKey(id);
        Integer userStatus = accountSub.getAccountSubStatus();
        if (userStatus != null && userStatus.intValue() == 0) {
            return error("该账户已禁用，请勿重复操作");
        }
        AccountSub u = new AccountSub();
        u.setAccountSubId(id);
        u.setAccountSubStatus(0);
        accountSubService.updateByPrimaryKeySelective(u);
        return success("操作成功");
    }

    /**
     * 详情- 账户
     */
    @PostMapping("/details/account")
    public ApiResult detailsAccount(@RequestBody(required = false) QueryParams queryParams) throws Exception {
        final String id = queryParams.getId();
        if (StringUtils.isBlank(id)) {
            return error("参数错误");
        }
        AccountSub accountSub = accountSubService.selectByPrimaryKey(id);
        Integer accountSubRelationIdType = accountSub.getAccountSubRelationIdType();
        String accountSubRelationId = accountSub.getAccountSubRelationId();
        if (accountSubRelationIdType.intValue() == 1) { //个人
            User user = userService.selectByPrimaryKey(accountSubRelationId);
            accountSub.setAccountSubRelationId(user.getUserCnName());
        } else if (accountSubRelationIdType.intValue() == 2) {
            Company company = companyService.selectByPrimaryKey(accountSubRelationId);
            if (company != null) {
                accountSub.setAccountSubRelationId(company.getCompanyName());
            } else {
                return error("对应账户不存在");
            }
        } else {
            accountSub.setAccountSubRelationId("无");
        }
        Map<String, BigDecimal> balanceByAccountSub = userAccountService.getBalanceByAccountSub(accountSub.getAccountSubNo());
        accountSub.setAccountSubAvailBalance(balanceByAccountSub.get("acctAvailBal"));
        accountSub.setAccountSubAshBalance(balanceByAccountSub.get("cashAmt"));
        return success(accountSub);
    }

    /**
     * 详情- 银行卡
     */
    @PostMapping("/details/bankcard")
    public ApiResult detailsBankcard(@RequestBody(required = false) QueryParams queryParams) {
        final String id = queryParams.getId();
        if (StringUtils.isBlank(id)) {
            return error("参数错误");
        }
        queryParams.startPage();
        List list = localBankCardService.getByAccountSubId(id);
        return success(new PageInfo<>(list));
    }

    /**
     * 详情- 交易记录
     */
    @PostMapping("/details/trade")
    public ApiResult detailsTrade(@RequestBody(required = false) QueryParams queryParams) {
        final String id = queryParams.getId();
        if (StringUtils.isBlank(id)) {
            return error("参数错误");
        }
        AccountSub accountSub = accountSubService.selectByPrimaryKey(id);
        String accountSubNo = accountSub.getAccountSubNo();
        queryParams.setNos(Arrays.asList(accountSubNo));
        queryParams.startPage();
        List list = accountSubTradeService.getTradeRecordList(queryParams);
        return success(new PageInfo<>(list));
    }

    /**
     * 总账户管理获取数据
     *
     * @return
     */
    @GetMapping()
    public ApiResult AccountManagement() throws Exception {
        Map<String, Object> map = accountSubService.getAccountSubBalance();
        return success(map);
    }
}
