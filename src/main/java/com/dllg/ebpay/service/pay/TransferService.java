package com.dllg.ebpay.service.pay;

import com.dllg.ebpay.model.form.PaymentCodeForm;
import com.dllg.ebpay.model.generator.AccountSubTrade;
import com.dllg.ebpay.model.generator.Dict;
import com.dllg.ebpay.model.request.TransferRequest;

public interface TransferService {

    PaymentCodeForm transfer(TransferRequest request);

    void sendTransferRequest(AccountSubTrade request, String sign);

    Dict getPercent();
}
