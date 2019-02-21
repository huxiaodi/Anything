package com.dllg.ebpay.quartz;

import com.dllg.ebpay.service.accountSub.HangingAccountService;
import com.dllg.ebpay.service.accountSub.impl.HangingAccountServiceImpl;
import com.dllg.ebpay.service.pay.PayService;
import com.dllg.ebpay.service.pay.impl.PayServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.*;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

@PersistJobDataAfterExecution
@DisallowConcurrentExecution// 不允许并发执行
public class QueryTopUpBean extends QuartzJobBean {

    private static final Logger logger = LogManager.getLogger(QueryTopUpBean.class);

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        ApplicationContext applicationContext = getApplicationContext(jobExecutionContext);
        PayService payService = applicationContext.getBean("payServiceImpl", PayServiceImpl.class);
        try {
            //查询充值状态
            payService.disposeUnfinishedTrade();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ApplicationContext getApplicationContext(final JobExecutionContext jobexecutioncontext) {
        try {
            return (ApplicationContext) jobexecutioncontext.getScheduler().getContext().get("applicationContext");
        } catch (SchedulerException e) {
            logger.error("调度获取Application Context 失败 ！！", e);
            throw new RuntimeException(e);
        }
    }
}
