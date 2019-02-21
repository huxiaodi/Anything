package com.dllg.ebpay.service.callback;


public interface PACallbackService {
    void executeTrade(String orderid, String flag, String sign);
}
