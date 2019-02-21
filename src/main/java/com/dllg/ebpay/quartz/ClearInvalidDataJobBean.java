package com.dllg.ebpay.quartz;

import com.dllg.ebpay.service.schedule.ScheduleService;
import com.dllg.ebpay.service.schedule.impl.ScheduleServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.*;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

@PersistJobDataAfterExecution
@DisallowConcurrentExecution// 不允许并发执行
public class ClearInvalidDataJobBean extends QuartzJobBean {

    private static final Logger logger = LogManager.getLogger(ClearInvalidDataJobBean.class);

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        ApplicationContext applicationContext = getApplicationContext(jobExecutionContext);
        ScheduleService scheduleService = applicationContext.getBean("scheduleServiceImpl", ScheduleServiceImpl.class);
        scheduleService.clearInvalidDate();
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
