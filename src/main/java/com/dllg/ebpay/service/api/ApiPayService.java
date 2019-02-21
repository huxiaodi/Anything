package com.dllg.ebpay.service.api;

import com.dllg.ebpay.model.request.ApiPaymentApplicationDisposeRequest;
import com.dllg.ebpay.model.request.ApiPaymentDisposeRequest;
import com.dllg.ebpay.model.request.ApiQueryDataResquest;
import com.dllg.ebpay.model.response.ApiPaymentCallBackJsonResponse;

/**
 * @Description：
 * @Author:hudi
 * @Date: Created in 15:03 2018/4/2
 */
public interface ApiPayService {

    /**
     * 业务平台支付请求
     * @param apiPaymentDisposeRequest
     * @return
     */
    String paymentDispose(ApiPaymentDisposeRequest apiPaymentDisposeRequest);

    /**
     * 业务平台支付申请请求
     * @param apiPaymentApplicationDisposeRequest
     * @return
     */
    void  paymentApplicationDispose(ApiPaymentApplicationDisposeRequest apiPaymentApplicationDisposeRequest);


    /**
     * 发送支付请求给平安，并且将交易成败返回给业务平台
     * @param orderid
     * @param flag
     * @param sign
     */
    void paymentCallBack(String orderid, String flag, String sign);

    /**
     * 业务平台查询接口
     * @param apiQueryDataResquest
     * @return
     */
    ApiPaymentCallBackJsonResponse queryData(ApiQueryDataResquest apiQueryDataResquest);

}
