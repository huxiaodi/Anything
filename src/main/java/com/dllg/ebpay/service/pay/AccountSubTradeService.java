package com.dllg.ebpay.service.pay;

import com.dllg.ebpay.model.dto.AccountSubTradeDto;
import com.dllg.ebpay.model.form.ServiceChargeForm;
import com.dllg.ebpay.model.generator.AccountSubTrade;
import com.dllg.ebpay.model.generator.Dict;
import com.dllg.ebpay.model.request.QueryParams;

import java.util.List;
import java.util.Map;

/**
 * 子账户流水记录
 */
public interface AccountSubTradeService {
    /**
     * 交易记录
     * @param queryParams
     */
    List getTradeRecordList(QueryParams queryParams);

    /**
     * 交易记录  -- 后台
     */
    List getTradeRecordListAdmin(QueryParams queryParams);


    /**
     * 业务记录
     */
    List getTradeBusinessList(QueryParams queryParams);

    /**
     * 业务记录 -- 后台
     */
    List getTradeBusinessListAdmin(QueryParams queryParams);


    /**
     * 单据状态
     */
    AccountSubTrade getOrder(String orderId);
    /**
     * 日支出
     * @param queryParams
     */
    Map dayPay(QueryParams queryParams);

    /**
     * 日收入
     * @param queryParams
     */
    Map dayIncome(QueryParams queryParams);

    /**
     * 月支出
     * @param queryParams
     */
    Map monthPay(QueryParams queryParams);

    /**
     * 月收入
     * @param queryParams
     */
    Map monthIncome(QueryParams queryParams);

    /**
     * 获取 手续费信息
     * @param serviceChargeForm
     * @return
     */
    List<AccountSubTradeDto> getServiceChargeList(ServiceChargeForm serviceChargeForm);

    /**
     * 获取手续费账户管理的 交易对象
     * @return
     */
    List<Map<String ,String>> getServiceChargeAccountName();

}

