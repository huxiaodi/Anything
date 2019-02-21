package com.dllg.ebpay.web.paycontroller;

import com.dllg.ebpay.model.request.WithdrawalRequest;
import com.dllg.ebpay.service.account.UserAccountService;
import com.dllg.ebpay.service.pay.impl.WithdrawalService;
import com.dllg.framework.controller.BaseController;
import com.dllg.framework.model.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/pay/withdrawal")
public class WithdrawalController extends BaseController {

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private WithdrawalService withdrawalService;

    @GetMapping("/getInitData")
    public ApiResult getInitData() {
        Map<String, Object> result = new HashMap<>();
        result.put("accountName", userAccountService.getUserAccountName());
        result.put("accountList", userAccountService.getUserAccountList());

        return success(result);
    }

    @GetMapping("/getBindList/{accountNo}")
    public ApiResult getBindList(@PathVariable("accountNo") String accountNo) {
        return success(withdrawalService.getBindList(accountNo));
    }

    @PostMapping
    public ApiResult withdrawal(@RequestBody WithdrawalRequest request) throws Exception{
        return success(withdrawalService.withdrawal(request));
    }

}
