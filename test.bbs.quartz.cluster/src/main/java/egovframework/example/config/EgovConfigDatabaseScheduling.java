package egovframework.example.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.quartz.JobDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;
import org.springframework.stereotype.Component;

import egovframework.example.job.CronJob;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class EgovConfigDatabaseScheduling {

	@Autowired
	private ApplicationContext applicationContext;
	
	@Autowired
	private TriggersListener triggersListener;

	@Autowired
	private JobsListener jobsListener;
	
	@Autowired
	private DataSource dataSource;
	
	private Properties quartzProperties() {
	    Properties props = new Properties();
	    props.put("org.quartz.scheduler.instanceName", "TestScheduler");
	    props.put("org.quartz.scheduler.instanceId", "AUTO");
	    props.put("org.quartz.jobStore.driverDelegateClass", "org.quartz.impl.jdbcjobstore.StdJDBCDelegate");
	    //props.put("org.quartz.jobStore.class", "org.quartz.simpl.RAMJobStore");
	    props.put("org.quartz.jobStore.class", "org.quartz.impl.jdbcjobstore.JobStoreTX");
	    props.put("org.quartz.jobStore.dataSource","dataSource");
	    props.put("org.quartz.jobStore.tablePrefix","QRTZ_");
	    
	    props.put("org.quartz.threadPool.threadCount", "3");
	    //props.put("org.quartz.jobStore.useProperties", "true");
	    props.put("org.quartz.jobStore.misfireThreshold", "60000");
	    props.put("org.quartz.jobStore.isClustered", "true");
	    props.put("org.quartz.jobStore.useProperties", "false");
	    props.put("org.quartz.jobStore.clusterCheckinInterval","1000");
	    //props.put("org.quartz.scheduler.idleWaitTime", "1000");
	    
	    return props;
	}

	@Bean("jobDetail3")
	public JobDetailFactoryBean jobDetail3() {

		log.debug("===>>> jobDetail3");
		JobDetailFactoryBean mjfb = new JobDetailFactoryBean();
		mjfb.setJobClass(CronJob.class);
		mjfb.setDurability(true);
		mjfb.setGroup("TEST_GROUP");
		
		return mjfb;
	}

	@Bean("simpleTrigger3")
	public SimpleTriggerFactoryBean simpleTrigger3(@Qualifier("jobDetail3") JobDetailFactoryBean jobDetail3) {
		log.debug("===>>> simpleTrigger3");
		SimpleTriggerFactoryBean stfb = new SimpleTriggerFactoryBean();
		stfb.setName("Cron Job - DB !!!");
		stfb.setJobDetail((JobDetail) jobDetail3.getObject());
		stfb.setStartDelay(10*1000); // milisecond
		stfb.setRepeatInterval(10*1000); // milisecond
		
		return stfb;
	}
	
	/*@Bean("simpleTrigger3")
	public CronTriggerFactoryBean simpleTrigger3(@Qualifier("jobDetail3") JobDetailFactoryBean jobDetail3) {
		log.debug("===>>> cron simpleTrigger");
		CronTriggerFactoryBean stfb = new CronTriggerFactoryBean();
		stfb.setName("Cron Job - 1 !!!");
		stfb.setJobDetail((JobDetail) jobDetail3.getObject());
		stfb.setCronExpression("0/10 * * * * ?"); // /5 초마다

		log.debug("jobDataMap ===>>> "+stfb.getJobDataMap());
		
		return stfb;
	}*/

	
	/**
	 * Quartz 관련 설정
	 *
	 * @param applicationContext the applicationContext
	 * @return SchedulerFactoryBean
	 */
	@Bean
	public SchedulerFactoryBean schedulerFactoryBean3(@Qualifier("simpleTrigger3") SimpleTriggerFactoryBean simpleTrigger3) {
		
		log.debug("===>>> cron scheduler3");
		log.debug("===>>> getJobDataMap = "+simpleTrigger3.getJobDataMap());
		
		SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
		schedulerFactoryBean.setApplicationContext(applicationContext);
		
		SpringBeanJobFactory jobFactory = new SpringBeanJobFactory();
		jobFactory.setApplicationContext(applicationContext);
		schedulerFactoryBean.setJobFactory(jobFactory);
		
		schedulerFactoryBean.setTriggers(simpleTrigger3.getObject());

		//schedulerFactoryBean.setApplicationContext(applicationContext);
		//schedulerFactoryBean.setGlobalTriggerListeners(triggersListener);
		//schedulerFactoryBean.setGlobalJobListeners(jobsListener);
		
		schedulerFactoryBean.setAutoStartup(true);
		schedulerFactoryBean.setOverwriteExistingJobs(true);
		schedulerFactoryBean.setDataSource(dataSource);
		schedulerFactoryBean.setQuartzProperties(quartzProperties());
		schedulerFactoryBean.setWaitForJobsToCompleteOnShutdown(true);
		
		return schedulerFactoryBean;
	}

}
