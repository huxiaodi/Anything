package com.dllg.ebpay.dao.custom;

import com.dllg.ebpay.dao.generator.UserMapper;
import com.dllg.ebpay.model.form.AccountForm;
import com.dllg.ebpay.model.generator.BankCard;
import com.dllg.ebpay.model.response.OutSubAcctResponse;


import java.util.List;

public interface LocalBankCardCuMapper {

    /**
     * 查询本地银行卡列表
     * @param bankCard 利用 - 用户id、子账户id
     * @return
     */
    List<BankCard> getLocalBankCradList(BankCard bankCard);

    /**
     * 查询已绑定过的银行卡
     * @param bankCard
     * @return
     */
    List<BankCard> getBundBankCradList(BankCard bankCard);

    /**
     * 解绑银行卡 - 调用接口后,更新本地数据库银行卡信息
     * @param bankCard 利用 - 用户id、子账户id、银行卡号
     * @return
     */
    void unLocalBankCrad(BankCard bankCard);
    /**
     * 账户 关联银行卡
     */
    List getByAccountSubId(String id);

    /**
     * 保存账户余额状态
     * @param accountForm
     * @return
     */
    void saveState(AccountForm accountForm);

    /**
     * 查询账户余额状态
     * @param accountForm
     * @return
     */
    List queryMoneyState(AccountForm accountForm);
}
