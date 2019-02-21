package com.dllg.ebpay.service.pay.impl;

import com.dllg.ebpay.model.form.PaymentCodeForm;
import com.dllg.ebpay.model.generator.AccountSubTrade;
import com.dllg.ebpay.model.request.WithdrawalRequest;
import com.dllg.ebpay.model.response.WithdrawalBankCardResponse;

import java.util.List;

public interface WithdrawalService {
    List<WithdrawalBankCardResponse> getBindList(String accountNo);

    PaymentCodeForm withdrawal(WithdrawalRequest request);

    void sendTransferRequest(AccountSubTrade request, String sign);
}
