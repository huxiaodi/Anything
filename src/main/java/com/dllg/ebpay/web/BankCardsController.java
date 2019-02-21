package com.dllg.ebpay.web;

import com.dllg.ebpay.model.form.AccountForm;
import com.dllg.ebpay.model.form.BankCardForm;
import com.dllg.ebpay.model.generator.BankCard;
import com.dllg.ebpay.service.bankCard.LocalBankCardService;
import com.dllg.ebpay.service.msgCode.MsgCodeService;
import com.dllg.framework.model.ApiResult;
import com.dllg.framework.model.AuthUser;
import com.dllg.framework.security.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.dllg.framework.utils.ApiResultUtil.error;
import static com.dllg.framework.utils.ApiResultUtil.success;

@RestController
@RequestMapping("/bankCards")
public class BankCardsController {

    @Autowired
    private MsgCodeService msgCodeService;
    @Autowired
    private LocalBankCardService localBankCardService;
    @Autowired
    Auth auth;

    /**
     * 解绑 - 图形、手机验证码 - 校验
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/validateCode")
    public ApiResult validateCode(@RequestBody BankCardForm bankCardForm) throws Exception {

          if (bankCardForm.getUnImgCode() != null) {
            //校验图形验证码成功....

            //if (true) {
                // 校对短信验证码
                if (bankCardForm.getPhone() != null && bankCardForm.getCode() != null) {
                    boolean flag = msgCodeService.judgeCode(bankCardForm.getPhone(), bankCardForm.getCode());
                    if (flag) {
                        return success("验证码正确");
                    } else {
                        return error("验证码错误");
                    }
                } else {
                    return error("验证码错误");
                }
            //}
        }else {
              return error("验证码错误");
        }
    }


    /**
     * 获取本地银行卡列表
     * @param bankCard
     * @return
     * @throws Exception
     */
    @RequestMapping("/getLocalBankCradList")
    public ApiResult getLocalBankCradList(@RequestBody BankCard bankCard) throws Exception {

        AuthUser user = auth.getCurrentUser();
        if(user.getUserType()==1) { bankCard.setBankCardCreateUserId(user.getUserId());}          //用户id
        if(user.getUserType()==2) { bankCard.setBankCardCreateUserId(user.getUserCompanyId());}   //企业id
        bankCard.setBankCardAccountSubId(user.getAccountSubNo());                                 //通过子账户号 - 获取子账户id

        return success(localBankCardService.getLocalBankCradList(bankCard));
    }
    @RequestMapping("/isBundBankCard")
    public ApiResult isBundBankCard(@RequestBody BankCard bankCard) throws Exception {

        return success(localBankCardService.getBundBankCradList(bankCard));
    }



    //解绑本地银行卡
    @RequestMapping("/unLocalBankCrad")
    public ApiResult unLocalBankCrad(@RequestBody BankCard bankCard) throws Exception {

        AuthUser user = auth.getCurrentUser();

        bankCard.setBankCardUpdateUserId(user.getUserId());
        localBankCardService.unLocalBankCrad(bankCard);

        return success("解绑成功");
    }

    //保存我的e宝-资金状态
    @RequestMapping("/saveState")
    public ApiResult saveState(@RequestBody AccountForm accountForm) throws Exception {

        AuthUser user = auth.getCurrentUser();
        if(user.getUserType()==1){accountForm.setUserId(user.getUserId());}
        if(user.getUserType()==2){accountForm.setUserId(user.getUserCompanyId());}
        accountForm.setAccountSubNo(user.getAccountSubNo());
        localBankCardService.saveState(accountForm);

        return success("成功");
    }


    @RequestMapping("/queryMoneyState")
    public ApiResult queryMoneyState(@RequestBody AccountForm accountForm) throws Exception {

        AuthUser user = auth.getCurrentUser();
        if(user.getUserType()==1){accountForm.setUserId(user.getUserId());}
        if(user.getUserType()==2){accountForm.setUserId(user.getUserCompanyId());}
        accountForm.setAccountSubNo(user.getAccountSubNo());

        return success(localBankCardService.queryMoneyState(accountForm));
    }


}
