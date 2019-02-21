package com.dllg.ebpay.service.pay.impl;

import com.dllg.ebpay.dao.custom.AccountSubTradeCuMapper;
import com.dllg.ebpay.model.dto.AccountSubTradeDto;
import com.dllg.ebpay.model.form.ServiceChargeForm;
import com.dllg.ebpay.model.generator.AccountSubTrade;
import com.dllg.ebpay.model.request.QueryParams;
import com.dllg.ebpay.service.pay.AccountSubTradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class AccountSubTradeServiceImpl implements AccountSubTradeService {

    @Autowired
    private AccountSubTradeCuMapper accountSubTradeCuMapper;

    /**
     * 交易记录
     *
     * @param queryParams
     */
    @Override
    public List getTradeRecordList(QueryParams queryParams) {
        return accountSubTradeCuMapper.getTradeRecordList(queryParams);
    }

    /**
     * 交易记录 -- 后台
     *
     * @param queryParams
     */
    @Override
    public List getTradeRecordListAdmin(QueryParams queryParams) {
        return accountSubTradeCuMapper.getTradeRecordListAdmin(queryParams);
    }

    /**
     * 业务记录
     */
    @Override
    public List getTradeBusinessList(QueryParams queryParams) {
        return accountSubTradeCuMapper.getTradeBusinessList(queryParams);
    }

    /**
     * 业务记录 -- 后台
     */
    @Override
    public List getTradeBusinessListAdmin(QueryParams queryParams) {
        return accountSubTradeCuMapper.getTradeBusinessListAdmin(queryParams);
    }

    @Override
    public AccountSubTrade getOrder(String orderId) {
        return accountSubTradeCuMapper.selectByPrimaryKey(orderId);
    }

    /**
     * 日支出
     *
     * @param queryParams
     */
    @Override
    public Map dayPay(QueryParams queryParams) {
        return accountSubTradeCuMapper.dayPay(queryParams);
    }

    /**
     * 日收入
     */
    @Override
    public Map dayIncome(QueryParams queryParams) {
        return accountSubTradeCuMapper.dayIncome(queryParams);
    }

    /**
     * 月支出
     */
    @Override
    public Map monthPay(QueryParams queryParams) {
        return accountSubTradeCuMapper.monthPay(queryParams);
    }

    /**
     * 月收入
     */
    @Override
    public Map monthIncome(QueryParams queryParams) {
        return accountSubTradeCuMapper.monthIncome(queryParams);
    }

    /**
     * 获取 手续费信息
     * @param serviceChargeForm
     * @return
     */
    @Override
    public List<AccountSubTradeDto> getServiceChargeList(ServiceChargeForm serviceChargeForm) {
        serviceChargeForm.startPage();
        return accountSubTradeCuMapper.getServiceChargeList(serviceChargeForm);
    }

    @Override
    public List<Map<String, String>> getServiceChargeAccountName() {
        return accountSubTradeCuMapper.getServiceChargeAccountName();
    }
}
