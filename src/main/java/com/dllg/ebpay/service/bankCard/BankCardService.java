package com.dllg.ebpay.service.bankCard;

import com.dllg.ebpay.model.form.BindBankCardForm;
import com.dllg.ebpay.model.form.ConfirmBankCardForm;
import com.dllg.ebpay.model.generator.*;
import com.dllg.pabank.model.response.SmallAmountTransferQueryResponse;

import java.util.List;
import java.util.Map;

/**
 * @Description：
 * @Author:hudi
 * @Date: Created in 16:39 2018/3/28
 */
public interface BankCardService {

   List<PubPayBank> getBankCardBankList();

   List<SuperInternetBank> getBankCardBankListSuper();

   BankCard checkBankCardNo(String bankCardNo);

   BankCard sumitBankCard(BindBankCardForm bindBankCardForm) throws Exception;


   BankCard submitconfirmData(ConfirmBankCardForm confirmBankCardForm) throws Exception;

   SmallAmountTransferQueryResponse querySmallAmount(String accountSubId, String bankCardNo) throws Exception;

   List<PubPayNode> getProvince();

   List<PubPayCity> getCity(PubPayNode pubPayNode);

   List<PubPayCity> getDistrict(PubPayCity pubPayCity);

   List<ZjjzCnapsBankinfo> getBankCardBankBranch(ZjjzCnapsBankinfo zjjzCnapsBankinfo);
}
