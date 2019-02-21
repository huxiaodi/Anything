package com.dllg.ebpay.dao.custom;

import com.dllg.ebpay.dao.generator.AccountSubTradeMapper;
import com.dllg.ebpay.model.dto.AccountSubTradeDto;
import com.dllg.ebpay.model.dto.InHandTradeDto;
import com.dllg.ebpay.model.form.ServiceChargeForm;
import com.dllg.ebpay.model.generator.AccountSubTrade;
import com.dllg.ebpay.model.request.QueryParams;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 子账户流水记录
 */
@Repository
public interface AccountSubTradeCuMapper extends AccountSubTradeMapper {
    /**
     * 交易记录
     * @param queryParams
     */
    List getTradeRecordList(QueryParams queryParams);

    /**
     * 交易记录 -- 后台
     * @param queryParams
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



    List<InHandTradeDto> getInHandTrade();

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

    List<AccountSubTradeDto> getServiceChargeList(ServiceChargeForm serviceChargeForm);

    List<Map<String, String>> getServiceChargeAccountName();

    Integer deleteInvalidDate();

    /**
     * 总账户日支出
     * @param queryParams
     */
    Map totalAccountDayPay(QueryParams queryParams);

    /**
     * 总账户日收入
     * @param queryParams
     */
    Map totalAccountDayIncome(QueryParams queryParams);

    /**
     * 总账户月支出
     * @param queryParams
     */
    Map totalAccountMonthPay(QueryParams queryParams);

    /**
     * 总账户月收入
     * @param queryParams
     */
    Map totalAccountMonthIncome(QueryParams queryParams);

    /**
     * 获取未结束的充值记录
     * @return
     */
    List<AccountSubTrade> getUnfinishedTrade();
}
