package com.dllg.ebpay.web.paycontroller;

import com.dllg.ebpay.model.form.InSubAcctForm;
import com.dllg.ebpay.model.generator.Dict;
import com.dllg.ebpay.model.request.TransferRequest;
import com.dllg.ebpay.service.account.UserAccountService;
import com.dllg.ebpay.service.pay.TransferService;
import com.dllg.framework.controller.BaseController;
import com.dllg.framework.model.ApiResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/pay/transfer")
public class TransferController extends BaseController {

    public static final Logger logger = LoggerFactory.getLogger(BaseController.class);

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private TransferService transferService;

    @GetMapping("/getPercent")
    public ApiResult getPercent() {
        try {
            Dict dict = transferService.getPercent();
            return success(dict);
        } catch (Exception e) {
            return error("字典出错" + e);
        }
    }

    @GetMapping("/getInitData")
    public ApiResult getInitData() {
        Map<String, Object> result = new HashMap<>();
        result.put("accountName", userAccountService.getUserAccountName());
        result.put("accountList", userAccountService.getUserAccountList());
        return success(result);
    }

    @GetMapping("/getOutSubAcctName/{keyword}")
    public ApiResult getOutSubAcctName(@PathVariable("keyword") String keyword) {
        return success(userAccountService.getOutSubAcctNameList(keyword));
    }

    @GetMapping("/getInSubAcct")
    public ApiResult getInSubAcct(InSubAcctForm form) {
        return success(userAccountService.getInSubAcct(form));
    }

    @PostMapping
    public ApiResult transfer(@RequestBody TransferRequest request) throws Exception {
        return success(transferService.transfer(request));
    }

}
