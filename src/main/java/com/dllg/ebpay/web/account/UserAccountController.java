package com.dllg.ebpay.web.account;

import com.dllg.ebpay.service.account.UserAccountService;
import com.dllg.framework.controller.BaseController;
import com.dllg.framework.model.ApiResult;
import com.dllg.framework.security.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/account/userAccount")
public class UserAccountController extends BaseController {

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    Auth auth;

    /**
     * 获取用户可用余额
     */
    @GetMapping("/getAvailableBalance")
    public ApiResult getAvailableBalance() {
        return success(userAccountService.getAvailableBalance());
    }

    /**
     * 获取子账户可用余额
     */
    @GetMapping("/getAccountBalance/{accountNo}")
    public ApiResult getAccountBalance(@PathVariable("accountNo") String accountNo) {
        return success(userAccountService.getAvailableBalance(accountNo));
    }

    /**
     * 获取子账户列表
     */
    @GetMapping("/getSubAccountList/{userId}")
    public ApiResult getSubAccountList(@PathVariable("userId") String userId) {
        return success(userAccountService.getUserAccountList(userId));
    }

    @RequestMapping("/getUploadInfo")
    public ApiResult getUploadInfo(MultipartFile file) {
        return success(userAccountService.getUploadInfo(file));
    }
}
