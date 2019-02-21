package com.dllg.ebpay.service.accountSub.impl;

import com.dllg.ebpay.configurer.EbpayProperties;
import com.dllg.ebpay.dao.custom.AccountSubCuMapper;
import com.dllg.ebpay.dao.custom.AccountSubTradeCuMapper;
import com.dllg.ebpay.dao.custom.ModifyAccountPhoneCuMapper;
import com.dllg.ebpay.dao.generator.*;
import com.dllg.ebpay.model.dto.AccountSubDto;
import com.dllg.ebpay.model.form.PaymentCodeForm;
import com.dllg.ebpay.model.generator.*;
import com.dllg.ebpay.model.request.QueryParams;
import com.dllg.ebpay.service.accountSub.AccountSubService;
import com.dllg.ebpay.service.accountSub.ModifyAccountPhoneService;
import com.dllg.ebpay.service.pay.AccountSubTradeService;
import com.dllg.framework.exception.AnyException;
import com.dllg.framework.model.AuthUser;
import com.dllg.framework.security.Auth;
import com.dllg.pabank.model.request.CustAcctIdBalanceQueryRequest;
import com.dllg.pabank.model.request.SupAcctIdBalanceQueryRequest;
import com.dllg.pabank.model.response.CustAcctIdBalanceQueryResponse;
import com.dllg.pabank.model.response.CustAcctIdBalanceQuerySubResponse;
import com.dllg.pabank.model.response.SupAcctIdBalanceQueryResponse;
import com.dllg.pabank.service.PABankService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ModifyAccountPhoneServiceImpl implements ModifyAccountPhoneService {

    @Autowired
    private ModifyAccountPhoneCuMapper modifyAccountPhoneCuMapper;


    @Override
    public List<AccountSub> getAccountSub(String accountSubNo) {

        return modifyAccountPhoneCuMapper.getAccountSub(accountSubNo);
    }


    @Override
    public String setAccountSubPhone(AccountSub accountSub) {

        modifyAccountPhoneCuMapper.setAccountSubPhone(accountSub);

        return "success";
    }


}
