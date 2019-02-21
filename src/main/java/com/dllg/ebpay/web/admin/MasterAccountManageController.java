package com.dllg.ebpay.web.admin;

import com.dllg.ebpay.model.dto.AccountSubTradeDto;
import com.dllg.ebpay.model.form.AdjustAccountSubForm;
import com.dllg.ebpay.model.form.HangingAccountListFrom;
import com.dllg.ebpay.model.form.InSubAcctForm;
import com.dllg.ebpay.service.accountSub.HangingAccountService;
import com.dllg.framework.controller.BaseController;
import com.dllg.framework.model.ApiResult;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description： 总账户管理
 * @Author:hudi
 * @Date: Created in 10:04 2018/4/12
 */

@RestController
@RequestMapping("/masterAccount")
public class MasterAccountManageController  extends BaseController{
    private final Logger logger = LoggerFactory.getLogger(MasterAccountManageController.class);

    @Autowired
    private HangingAccountService hangingAccountService;

    @PostMapping("/getHangingAccountList")
    public ApiResult getHangingAccountList(@RequestBody HangingAccountListFrom hangingAccountListFrom) throws Exception{
            List<AccountSubTradeDto> gangingAccountList =hangingAccountService.getHangingAccountList(hangingAccountListFrom);
            return success(new PageInfo<>(gangingAccountList));
    }

    @GetMapping("/getHangingAccountSub")
    public ApiResult getHangingAccountNo() throws Exception{
        return success(hangingAccountService.getHangingAccountSub());
    }


    @GetMapping("/getAllAccountSub")
    public ApiResult getAllAccountSub(){
        return success(hangingAccountService.getAllAccountSub());
    }

    @PostMapping("/adjustAccountSub")
    public ApiResult adjustAccountSub(@RequestBody AdjustAccountSubForm form)  throws  Exception{
        hangingAccountService.adjustAccountSub(form);
        return success();
    }

    @GetMapping("/checkAccountSubTrade")
    public ApiResult checkAccountSubTrade(String accountSubTradeId)  throws  Exception{
        hangingAccountService.checkAccountSubTrade(accountSubTradeId);
        return success();
    }


    @GetMapping("/getHangingTradeDetailById")
    public ApiResult getHangingTradeDetailById(@RequestParam String accountSubTradeId){
        return success(hangingAccountService.getHangingTradeDetailById(accountSubTradeId));
    }






}
