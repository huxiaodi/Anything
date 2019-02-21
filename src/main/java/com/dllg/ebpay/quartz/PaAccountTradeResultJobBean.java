package com.dllg.ebpay.quartz;

import com.dllg.ebpay.service.trade.TradeService;
import com.dllg.ebpay.service.trade.impl.TradeServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.*;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

@PersistJobDataAfterExecution
@DisallowConcurrentExecution// 不允许并发执行
public class PaAccountTradeResultJobBean extends QuartzJobBean {

    private static final Logger logger = LogManager.getLogger(PaAccountTradeResultJobBean.class);

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        ApplicationContext applicationContext = getApplicationContext(context);

        TradeService tradeService = applicationContext.getBean("tradeServiceImpl", TradeServiceImpl.class);
        tradeService.getPaAccountTradeResult();
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
