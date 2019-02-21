package com.dllg.configurer;

import com.alibaba.druid.pool.DruidDataSource;
import com.dllg.ebpay.quartz.*;
import org.quartz.CronTrigger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

@Configuration
//@ConditionalOnBean({DruidDataSource.class})
public class QuartzConfigurer {

    /**
     * 设置属性
     * @return
     * @throws IOException
     */
    private Properties quartzProperties() throws IOException {
        Properties prop = new Properties();
        prop.put("quartz.scheduler.instanceName", "DLLGScheduler");
        prop.put("org.quartz.scheduler.instanceId", "AUTO");
        prop.put("org.quartz.scheduler.skipUpdateCheck", "true");
        prop.put("org.quartz.scheduler.jmx.export", "true");

        prop.put("org.quartz.jobStore.class", "org.quartz.impl.jdbcjobstore.JobStoreTX");
        prop.put("org.quartz.jobStore.driverDelegateClass", "org.quartz.impl.jdbcjobstore.StdJDBCDelegate");
        prop.put("org.quartz.jobStore.dataSource", "quartzDataSource");
//        prop.put("org.quartz.jobStore.tablePrefix", "QRTZ_");
        prop.put("org.quartz.jobStore.isClustered", "true");

        prop.put("org.quartz.jobStore.clusterCheckinInterval", "20000");
        prop.put("org.quartz.jobStore.dataSource", "myDS");
        prop.put("org.quartz.jobStore.maxMisfiresToHandleAtATime", "1");
        prop.put("org.quartz.jobStore.misfireThreshold", "120000");
        prop.put("org.quartz.jobStore.txIsolationLevelSerializable", "true");
//        prop.put("org.quartz.jobStore.selectWithLockSQL", "SELECT * FROM {0}LOCKS WHERE LOCK_NAME = ? FOR UPDATE");

        prop.put("org.quartz.threadPool.class", "org.quartz.simpl.SimpleThreadPool");
        prop.put("org.quartz.threadPool.threadCount", "10");
        prop.put("org.quartz.threadPool.threadPriority", "5");
        prop.put("org.quartz.threadPool.threadsInheritContextClassLoaderOfInitializingThread", "true");

//        prop.put("org.quartz.dataSource.myDS.driver", myDSDriver);
//        prop.put("org.quartz.dataSource.myDS.URL", myDSURL);
//        prop.put("org.quartz.dataSource.myDS.user", myDSUser);
//        prop.put("org.quartz.dataSource.myDS.password", myDSPassword);
//        System.out.println("myDSMaxConnections:" + myDSMaxConnections);
//        prop.put("org.quartz.dataSource.myDS.maxConnections", myDSMaxConnections);

        prop.put("org.quartz.plugin.triggHistory.class", "org.quartz.plugins.history.LoggingJobHistoryPlugin");
        prop.put("org.quartz.plugin.shutdownhook.class", "org.quartz.plugins.management.ShutdownHookPlugin");
        prop.put("org.quartz.plugin.shutdownhook.cleanShutdown", "true");
        return prop;
    }
    public JobDetailFactoryBean createJobDetail(Class jobClass){
        JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
        jobDetailFactoryBean.setJobClass(jobClass);
        jobDetailFactoryBean.setDurability(true);
        jobDetailFactoryBean.setRequestsRecovery(true);
        return jobDetailFactoryBean;
    }
    public CronTriggerFactoryBean createCronTriggerFactoryBean(JobDetailFactoryBean jobDetailFactoryBean,String corn) {
        CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
        cronTriggerFactoryBean.setJobDetail(jobDetailFactoryBean.getObject());
        cronTriggerFactoryBean.setCronExpression(corn);
        return cronTriggerFactoryBean;
    }


    @Bean()
    public  JobDetailFactoryBean paAccountTradeResult() {
        return createJobDetail(PaAccountTradeResultJobBean.class);
    }

    @Bean()
    public CronTriggerFactoryBean paAccountTradeResultCronTriggerFactoryBean() {
        return createCronTriggerFactoryBean(paAccountTradeResult(), "0 0/1 * * * ?");
    }

    @Bean()
    public JobDetailFactoryBean clearInvalidData() {
        return createJobDetail(ClearInvalidDataJobBean.class);
    }

    @Bean()
    public CronTriggerFactoryBean clearInvalidDataCronTriggerFactoryBean() {
        return createCronTriggerFactoryBean(clearInvalidData(),"0 0 22 * * ?");
    }

    //退单
    @Bean()
    public JobDetailFactoryBean bankWithdrawCashBackQuery() {
        return createJobDetail(BankWithdrawCashBackQuery.class);
    }

    @Bean()
    public CronTriggerFactoryBean bankWithdrawCashBackQueryCronTriggerFactoryBean() {
        return createCronTriggerFactoryBean(bankWithdrawCashBackQuery(),"0 */15 * * * ?");
//        return createCronTriggerFactoryBean(clearInvalidDataFactoryBean,"0 */15 * * *");
    }

    //同步充值状态
    @Bean()
    public JobDetailFactoryBean QueryTopUp() {
        return createJobDetail(QueryTopUpBean.class);
    }

    @Bean()
    public CronTriggerFactoryBean QueryTopUpCronTriggerFactoryBean() {
        return createCronTriggerFactoryBean(QueryTopUp(),"0 */5 * * * ?");
    }
    //同步充值数据
    @Bean()
    public JobDetailFactoryBean topUp() {
        return createJobDetail(TopUpJobBean.class);
    }

    @Bean()
    public CronTriggerFactoryBean topUpCronTriggerFactoryBean() {
        return createCronTriggerFactoryBean(topUp(),"0 */5 * * * ?");
    }
    //同步每个子账户充值数据
//    @Bean()
//    public JobDetailFactoryBean topUpJobBySubAccount() {
//        return createJobDetail(TopUpJobBySubAccountBean.class);
//    }

//    @Bean()
//    public CronTriggerFactoryBean topUpJobBySubAccountCronTriggerFactoryBean() {
//        return createCronTriggerFactoryBean(topUpJobBySubAccount(),"0 */10 * * * ?");
//    }
    @Bean
    public SchedulerFactoryBean getSchedulerFactoryBean(DruidDataSource dataSource,
                                                        List<CronTrigger> cronTriggerList)throws IOException{
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setOverwriteExistingJobs(true);
        schedulerFactoryBean.setStartupDelay(10);
        schedulerFactoryBean.setDataSource(dataSource);
        schedulerFactoryBean.setStartupDelay(10);
        schedulerFactoryBean.setQuartzProperties(quartzProperties());
        schedulerFactoryBean.setAutoStartup(true);
        schedulerFactoryBean.setApplicationContextSchedulerContextKey("applicationContext");
//        schedulerFactoryBean.setTriggers(paAccountTradeResultFactoryBea
        schedulerFactoryBean.setTriggers( cronTriggerList.toArray(new CronTrigger[cronTriggerList.size()]));
//        factory.setConfigLocation(new FileSystemResource(this.getClass().getResource("/quartz.properties").getPath()));
        return schedulerFactoryBean;
    }




}
