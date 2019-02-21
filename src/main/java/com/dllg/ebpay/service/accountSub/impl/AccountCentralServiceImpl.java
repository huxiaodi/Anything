package com.dllg.ebpay.service.accountSub.impl;

import com.dllg.ebpay.configurer.EbpayProperties;
import com.dllg.ebpay.dao.custom.AccountCentralCuMapper;
import com.dllg.ebpay.dao.custom.AccountSubCuMapper;
import com.dllg.ebpay.dao.generator.AccountSubMapper;
import com.dllg.ebpay.dao.generator.CompanyMapper;
import com.dllg.ebpay.dao.generator.UserMapper;
import com.dllg.ebpay.model.dto.AccountSubDto;
import com.dllg.ebpay.model.form.PaymentCodeForm;
import com.dllg.ebpay.model.generator.*;
import com.dllg.ebpay.service.accountSub.AccountCentralService;
import com.dllg.ebpay.service.accountSub.AccountSubService;
import com.dllg.framework.model.AuthUser;
import com.dllg.framework.security.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class AccountCentralServiceImpl implements AccountCentralService {

    @Autowired
    private AccountCentralCuMapper accountCentralCuMapper;

    //获得当前用户信息
    @Override
    public User getAccountCentralInfo(User user) {
        return accountCentralCuMapper.getAccountCentralInfo(user);
    }

    //获得当前企业信息
    @Override
    public Company getAccountCentralEnterpriseInfo(User user) {
        return accountCentralCuMapper.getAccountCentralEnterpriseInfo(user);
    }



    @Override
    public BankCard getAccountCentralCard(User user) {
        return accountCentralCuMapper.getAccountCentralCard(user);
    }

    /**
     * 获得账户列表
     * @param relationId
     * @return
     */
    @Override
    public List<AccountSub> getAccountList(String relationId) {

        return accountCentralCuMapper.getAccountList(relationId);
    }


}