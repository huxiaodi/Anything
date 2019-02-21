package com.dllg.ebpay.service.accountSub;

import com.dllg.ebpay.model.generator.AccountSub;
import com.dllg.ebpay.model.generator.User;

import java.util.List;

/**
 * Created by hp on 2018/6/5.
 */
public interface ModifyAccountPhoneService {

    public List<AccountSub> getAccountSub(String accountSubNo);

    public String setAccountSubPhone(AccountSub accountSub);

}

