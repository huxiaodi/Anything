package com.dllg.ebpay.web.AccountSub;

import com.dllg.ebpay.api.ApiPayController;
import com.dllg.ebpay.configurer.EbpayProperties;
import com.dllg.ebpay.model.form.PaymentCodeForm;
import com.dllg.ebpay.service.accountSub.AccountSubService;
import com.dllg.framework.controller.BaseController;
import com.dllg.framework.enums.ApiCodeEnum;
import com.dllg.framework.model.ApiResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accountSub/accountSubController")
public class AccountSubController extends BaseController{
    private static final Logger logger = LogManager.getLogger(ApiPayController.class);
    @Autowired
    private AccountSubService accountSubService;
    @Autowired
    private EbpayProperties ebpayProperties;

    @RequestMapping(value = "/getAccountSubs")
    public ApiResult getAccountSubs(){
        try {
            return success(accountSubService.getAccountSubs());
        } catch (Exception e) {
            e.printStackTrace();
            return error(ApiCodeEnum.FAILURE,null);
        }
    }

    @RequestMapping(value = "/paymentCodeManage")
    public ApiResult paymentCodeManage(String accountSubNo, String type, String mobile){
        try {
            PaymentCodeForm paymentCodeForm = null;
            if("S".equals(type)){
                paymentCodeForm = accountSubService.paymentCodeManage(null,accountSubNo, type ,"", null, ebpayProperties.getSetPasswordBackUrl(),null);
            }else if("M".equals(type)){
                paymentCodeForm = accountSubService.paymentCodeManage(null,accountSubNo, type ,"", null, ebpayProperties.getSetPasswordBackUrl(),mobile);
            }else{
                paymentCodeForm = accountSubService.paymentCodeManage(null,accountSubNo, type ,"", null, "",null);
            }
            return success(paymentCodeForm);
        } catch (Exception e) {
            e.printStackTrace();
            return error(ApiCodeEnum.FAILURE,null);
        }
    }
    @RequestMapping(value = "/returnBack")
    public ApiResult returnBack(String orderid, String P2PCode, String flag, String orig, String sign){
        try {
            logger.info("----------------------支付密码设置成功回调-----------------------");
            accountSubService.returnBack(orderid, flag);
            return success("");
        } catch (Exception e) {
            e.printStackTrace();
            return error(ApiCodeEnum.FAILURE,null);
        }
    }

    @PostMapping("/setSendSMS")
    public ApiResult setSendSMS(@RequestParam String id){
        try {
            accountSubService.setSendSMS(id);
            return success();
        } catch (Exception e) {
            e.printStackTrace();
            return error("设置是否发送短信失败");
        }
    }

}
