package com.dllg.ebpay.web.AccountSub;

import com.dllg.ebpay.api.ApiPayController;
import com.dllg.ebpay.configurer.EbpayProperties;
import com.dllg.ebpay.model.form.PaymentCodeForm;
import com.dllg.ebpay.model.generator.AccountSub;
import com.dllg.ebpay.service.accountSub.AccountSubService;
import com.dllg.ebpay.service.accountSub.ModifyAccountPhoneService;
import com.dllg.framework.controller.BaseController;
import com.dllg.framework.enums.ApiCodeEnum;
import com.dllg.framework.model.ApiResult;
import com.dllg.framework.security.Auth;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 修改子账户手机号
 * 调用平安银行两个接口
 * 1.申请修改手机号码(1.短信验证码 2.银联鉴权))
 * 2.回填动态码
 */
@RestController
@RequestMapping("/accountSub/modifyAccountPhone")
public class ModifyAccountPhoneController extends BaseController{

    private static final Logger logger = LogManager.getLogger(ApiPayController.class);

    @Autowired
    private ModifyAccountPhoneService modifyAccountPhoneService;
    @Autowired
    private Auth auth;

    //获取子账户交易手机号
    @RequestMapping(value = "/getAccountSub")
    public ApiResult getAccountSub(){
        try {
            return success(modifyAccountPhoneService.getAccountSub(auth.getCurrentUser().getAccountSubNo()));
        } catch (Exception e) {
            e.printStackTrace();
            return error(ApiCodeEnum.FAILURE,null);
        }
    }

    //回写新手机号到系统中
    @RequestMapping(value = "/setAccountSubPhone")
    public ApiResult setAccountSubPhone(@RequestBody AccountSub accountSub){
        try {
                 accountSub.setAccountSubCreateUserId(auth.getCurrentUser().getUserId());
            return success(modifyAccountPhoneService.setAccountSubPhone(accountSub));
        } catch (Exception e) {
            e.printStackTrace();
            return error(ApiCodeEnum.FAILURE,null);
        }
    }



}
