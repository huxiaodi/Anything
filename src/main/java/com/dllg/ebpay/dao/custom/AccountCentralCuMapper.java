package com.dllg.ebpay.dao.custom;

import com.dllg.ebpay.model.dto.AccountSubDto;
import com.dllg.ebpay.model.dto.UserAccountInfoDto;
import com.dllg.ebpay.model.form.InSubAcctForm;
import com.dllg.ebpay.model.generator.AccountSub;
import com.dllg.ebpay.model.generator.BankCard;
import com.dllg.ebpay.model.generator.Company;
import com.dllg.ebpay.model.generator.User;

import java.util.List;

public interface AccountCentralCuMapper {

    //获取当前子账户信息(个人)
    public User getAccountCentralInfo(User user);

    //获取当前子账户信息(企业)
    public Company getAccountCentralEnterpriseInfo(User user);

    //获取当前子账户 - 帮卡信息
    public BankCard getAccountCentralCard(User user);


    //获得账号列表
    public List<AccountSub> getAccountList(String relationId);

}
