package com.dllg.ebpay.service.bankCard;

import com.dllg.ebpay.model.form.AccountForm;
import com.dllg.ebpay.model.generator.BankCard;

import java.util.List;


public interface LocalBankCardService {

   /**
    * 查询本地银行卡列表
    * @param bankCard 利用 - 用户id、子账户id
    * @return
    */
    List<BankCard> getLocalBankCradList(BankCard bankCard);

    /**
     * 查询绑定过得银行卡
     * @param bankCard
     * @return
     */
    public List<BankCard> getBundBankCradList(BankCard bankCard);

   /**
    * 解绑银行卡 - 调用接口后,更新本地数据库银行卡信息
    * @param bankCard 利用 - 用户id、子账户id、银行卡号
    * @return
    */
    String unLocalBankCrad(BankCard bankCard);

    /**
     * 账户 对应关联银行卡
     */
    List getByAccountSubId(String id);

    //保存我的e宝-资金状态
    String saveState(AccountForm accountForm);

    public List queryMoneyState(AccountForm accountForm);
}
