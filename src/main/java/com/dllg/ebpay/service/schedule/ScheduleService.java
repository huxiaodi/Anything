package com.dllg.ebpay.service.schedule;

public interface ScheduleService {

    // 清理无效数据
    void clearInvalidDate();

    //查询退单信息
    void bankWithdrawCashBackQuery();
}
