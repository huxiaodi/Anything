package com.dllg.ebpay.service.pay;

import com.dllg.ebpay.model.form.BusinessPayForm;
import com.dllg.ebpay.model.form.PayApplicationForm;
import com.dllg.ebpay.model.generator.AccountSubTrade;
import com.dllg.ebpay.model.generator.ApplicationTrade;
import com.dllg.ebpay.model.generator.Refund;
import com.dllg.ebpay.model.response.PayResponse;
import com.ecc.emp.data.KeyedCollection;

import java.util.List;
import java.util.Map;

public interface PayService {

    PayResponse payMoney(String money, String fee, String remark) throws Exception;

    PayResponse payMoneyForBusiness(BusinessPayForm businessPayForm) throws Exception;

    void returnbackToBusiness(String orderId, String status);

    void returnback(String orderId, String status);

    KeyedCollection parsing(String orig) throws Exception;

    Map<String, String> getOrderStatus(String orderId) throws Exception;

    Map<String, String> reconciliation() throws Exception;

    void sf0005(Refund refund, AccountSubTrade accountSubTrade) throws Exception;

    void sf0006() throws Exception;

    Map<String, Object> sf0009(String orderNo) throws Exception;

    void disposeUnfinishedTrade() throws Exception;

    /**
     * 获取付款申请列表
     *
     * @param payApplicationForm
     * @return
     */
    List<Map<String, String>> getPayApplicationList(PayApplicationForm payApplicationForm);

    /**
     * 获取付款申请详情
     *
     * @param applicationTradeId
     * @return
     */
    ApplicationTrade getPayApplicationDetail(String applicationTradeId);

    /**
     * 付款申请审核
     *
     * @param map
     */
    void payApplicationAudit(Map<String, String> map);


    /**
     * 付款申请付款
     *
     * @param map
     */
    String payApplicationPay(Map<String, String> map);

    List<String>getObjecSource();

}
