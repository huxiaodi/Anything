package com.dllg.ebpay.web.bankCardController;

import com.dllg.ebpay.model.form.BindBankCardForm;
import com.dllg.ebpay.model.form.ConfirmBankCardForm;
import com.dllg.ebpay.model.generator.*;
import com.dllg.ebpay.service.bankCard.BankCardService;
import com.dllg.framework.controller.BaseController;
import com.dllg.framework.enums.ApiCodeEnum;
import com.dllg.framework.model.ApiResult;
import com.dllg.pabank.model.response.SmallAmountTransferQueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description：
 * @Author:hudi
 * @Date: Created in 16:37 2018/3/28
 */
@RestController
@RequestMapping("/bankCard")
public class BankCardController extends BaseController {

    @Autowired
    private BankCardService bankCardService;


    @GetMapping("/getBankCardBankList")
    public ApiResult getBankCardBankList(@RequestParam Integer bankCardTransactionAmountType){
        if(bankCardTransactionAmountType != null && bankCardTransactionAmountType.equals(1)){ // 大于5万 大小额的

           List list = bankCardService.getBankCardBankList();

            return success(bankCardService.getBankCardBankList()); // 读取的是  大于5万 大小额的 pub_pay_bank 表
        }else if(bankCardTransactionAmountType != null && bankCardTransactionAmountType.equals(2)){ // 小于5万 超级网银的

            return success(bankCardService.getBankCardBankListSuper()); // 读取的是  小于5万 超级网银 super_internet_bank 表
        }else{
            return  error(ApiCodeEnum.FAILURE,null);
        }

    }

    @PostMapping("/sumitBankCard")
    public ApiResult sumitBankCard(@RequestBody BindBankCardForm bindBankCardForm) throws  Exception {
        BankCard bankCard = bankCardService.checkBankCardNo(bindBankCardForm.getBankCardNo());
        /*if(bankCard != null ){
            if( !bankCard.getBankCardAccountSubId().equals(bindBankCardForm.getAccountSubId()) ){
                return error(ApiCodeEnum.FAILURE,"该银行卡号已被其他用户或者子账户使用");
            }else{
              if(bankCard.getBankCardStatus().equals(1)){
                  return success(bankCard);
              }else if (bankCard.getBankCardStatus().equals(2)){
                  return error(ApiCodeEnum.FAILURE,"该银行卡号已被绑定");
              }
            }
        }*/
        BankCard bankCard1 = bankCardService.sumitBankCard(bindBankCardForm);
        if(bankCard1 != null){
            return success(bankCard1);
        }else{
            return  error(ApiCodeEnum.FAILURE,"绑定出错");
        }
    }

    @GetMapping("/querySmallAmount")
    public ApiResult querySmallAmount(String accountSubId, String bankCardNo) throws  Exception {
        SmallAmountTransferQueryResponse smallAmountTransferQueryResponse = bankCardService.querySmallAmount(accountSubId, bankCardNo);
        if(smallAmountTransferQueryResponse != null){
            return success(smallAmountTransferQueryResponse);
        }else{
            return error("查询出错！");
        }
    }
    @PostMapping("/submitconfirmData")
    public ApiResult submitconfirmData(@RequestBody ConfirmBankCardForm confirmBankCardForm) throws  Exception {
        BankCard bankCard = bankCardService.checkBankCardNo(confirmBankCardForm.getBankCardNo());
        /*if(bankCard != null ){
            if( !bankCard.getBankCardAccountSubId().equals(confirmBankCardForm.getAccountSubId()) ){
                return error(ApiCodeEnum.FAILURE,"该银行卡号已被其他用户或者子账户使用");
            }else{
                if (bankCard.getBankCardStatus().equals(2)){
                    return error(ApiCodeEnum.FAILURE,"该银行卡号已被邦定");
                }else if(bankCard.getBankCardStatus().equals(4)){
                    return error(ApiCodeEnum.FAILURE,"该银行卡绑定失败，请重新绑定");
                }
            }
        }*/
        BankCard message = bankCardService.submitconfirmData(confirmBankCardForm);
        if(message != null){  // ConfirmBankCardForm
            return success(message);
        }else{
            return  error(ApiCodeEnum.FAILURE,"绑定出错");
        }

    }

    @GetMapping("/province")
    public ApiResult getProvince(){
        return success(bankCardService.getProvince());
    }
    @PostMapping("/city")
    public ApiResult getCity(@RequestBody PubPayNode pubPayNode){
        return success(bankCardService.getCity(pubPayNode));
    }
    @PostMapping("/district")
    public ApiResult getDistrict(@RequestBody PubPayCity pubPayCity){
        return success(bankCardService.getDistrict(pubPayCity));
    }
    @PostMapping("/getBankCardBankBranch")
    public ApiResult getBankCardBankBranch(@RequestBody ZjjzCnapsBankinfo zjjzCnapsBankinfo){
        return success(bankCardService.getBankCardBankBranch(zjjzCnapsBankinfo));
    }







}
