package com.dllg.ebpay.service.bankCard.impl;

import com.dllg.ebpay.dao.custom.LocalBankCardCuMapper;
import com.dllg.ebpay.model.form.AccountForm;
import com.dllg.ebpay.model.generator.BankCard;
import com.dllg.ebpay.service.bankCard.LocalBankCardService;
import com.dllg.framework.model.AuthUser;
import com.dllg.framework.security.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by hp on 2018/3/29.
 */
@Service
@Transactional
public class LocalBankCardServiceImpl implements LocalBankCardService {

    @Autowired
    private LocalBankCardCuMapper localBankCardCuMapper;

    @Autowired
    private Auth auth;

    @Override
    public List<BankCard> getLocalBankCradList(BankCard bankCard) {
        return localBankCardCuMapper.getLocalBankCradList(bankCard);
    }

    @Override
    public List<BankCard> getBundBankCradList(BankCard bankCard) {
        AuthUser user = auth.getCurrentUser();
        if(user.getUserType()==1) { bankCard.setBankCardCreateUserId(user.getUserId());}          //用户id
        if(user.getUserType()==2) { bankCard.setBankCardCreateUserId(user.getUserCompanyId());}       //用户id
        bankCard.setBankCardAccountSubId(user.getAccountSubNo());    //通过子账户号 - 获取子账户id

        return localBankCardCuMapper.getBundBankCradList(bankCard);
    }
    @Override
    public String unLocalBankCrad(BankCard bankCard) {

        bankCard.setBankCardUnbindBankCardDate(new Date()); //解绑时间
        bankCard.setBankCardUpdateTime(new Date());         //更新时间

        localBankCardCuMapper.unLocalBankCrad(bankCard);
        return "success";
    }

    /**
     * 账户 关联银行卡
     */
    @Override
    public List getByAccountSubId(String id) {
        return localBankCardCuMapper.getByAccountSubId(id);
    }

    /**
     * 保存账户余额状态
     * @param accountForm
     * @return
     */
    @Override
    public String saveState(AccountForm accountForm) {
        localBankCardCuMapper.saveState(accountForm);
        return "success";
    }

    /**
     * 查询账户余额状态
     * @param accountForm
     * @return
     */
    public List queryMoneyState(AccountForm accountForm) {
        return localBankCardCuMapper.queryMoneyState(accountForm);
    }


}
