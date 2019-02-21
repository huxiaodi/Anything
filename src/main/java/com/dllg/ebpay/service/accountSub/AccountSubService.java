package com.dllg.ebpay.service.accountSub;

import com.dllg.ebpay.model.dto.AccountSubDto;
import com.dllg.ebpay.model.form.PaymentCodeForm;
import com.dllg.ebpay.model.generator.AccountSub;
import com.dllg.ebpay.model.request.QueryParams;

import java.util.List;
import java.util.Map;

public interface AccountSubService {
    List<AccountSubDto> getAccountSubs();

    PaymentCodeForm paymentCodeManage(String orderId, String accountSubNo, String type, Object orig, String returnurl, String notifyUrl, String mobile);


    void returnBack(String orderid, String flag);

    /**
     * 管理端 账户列表
     */
    List manageList(QueryParams queryParams) throws Exception;

    AccountSub selectByPrimaryKey(String id);

    void updateByPrimaryKeySelective(AccountSub accountSub);

    String getPhone(String subAcctId);

    Map<String, Object> getAccountSubBalance() throws Exception;

    void setSendSMS(String businessTradeId);
}
