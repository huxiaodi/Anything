package com.dllg.ebpay.service.accountSub;

import com.dllg.ebpay.model.dto.AccountSubDto;
import com.dllg.ebpay.model.form.PaymentCodeForm;
import com.dllg.ebpay.model.generator.AccountSub;
import com.dllg.ebpay.model.generator.BankCard;
import com.dllg.ebpay.model.generator.Company;
import com.dllg.ebpay.model.generator.User;

import java.util.List;

/**
 * 账户中心
 */
public interface AccountCentralService {

    //获取当前子账户信息(企业)
    public User getAccountCentralInfo(User user);

    //获取当前子账户信息(个人)
    public Company getAccountCentralEnterpriseInfo(User user);

    //获取当前子账户 - 帮卡信息
    public BankCard getAccountCentralCard(User user);

    //获得账号列表
    public List<AccountSub> getAccountList(String relationId);


}
