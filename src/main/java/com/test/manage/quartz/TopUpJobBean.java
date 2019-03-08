package com.test.manage.quartz;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.*;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

@PersistJobDataAfterExecution
@DisallowConcurrentExecution// 不允许并发执行
public class TopUpJobBean extends QuartzJobBean {

    private static final Logger logger = LogManager.getLogger(TopUpJobBean.class);

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        ApplicationContext applicationContext = getApplicationContext(jobExecutionContext);
//        HangingAccountService hangingAccountService = applicationContext.getBean("hangingAccountServiceImpl", HangingAccountServiceImpl.class);
//        try {
//            hangingAccountService.getHangingAccountListByBank();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
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
