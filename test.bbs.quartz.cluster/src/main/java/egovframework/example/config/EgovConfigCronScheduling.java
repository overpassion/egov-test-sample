package egovframework.example.config;

import org.quartz.JobDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class EgovConfigCronScheduling {

	@Bean
	public MethodInvokingJobDetailFactoryBean jobDetail(@Autowired MySchedulingJob mySchedulingJob) {
		log.debug("===>>> cron jobDetail");
		MethodInvokingJobDetailFactoryBean mjfb = new MethodInvokingJobDetailFactoryBean();
		mjfb.setTargetObject(mySchedulingJob);
		mjfb.setTargetMethod("startJob");
		mjfb.setConcurrent(false);
		return mjfb;
	}
	
	@Bean
	public CronTriggerFactoryBean simpleTrigger(@Qualifier("jobDetail") MethodInvokingJobDetailFactoryBean jobDetail) {
		log.debug("===>>> cron simpleTrigger");
		CronTriggerFactoryBean stfb = new CronTriggerFactoryBean();
		stfb.setName("Cron Job - 1 !!!");
		stfb.setJobDetail((JobDetail) jobDetail.getObject());
		stfb.setCronExpression("0/10 * * * * ?"); // /5 초마다

		log.debug("jobDataMap ===>>> "+stfb.getJobDataMap());
		
		return stfb;
	}
	
	/*@Bean
	public SchedulerFactoryBean scheduler(@Qualifier("simpleTrigger") CronTriggerFactoryBean simpleTrigger) {
		log.debug("===>>> cron scheduler");
		log.debug("Setting SchedulerFactoryBean");

		SchedulerFactoryBean sfb = new SchedulerFactoryBean();
	    //sfb.setDataSource(dataSource);
	    //sfb.setSchedulerContextAsMap(null);
	    //sfb.setConfigLocation(null);
		//sfb.setQuartzProperties(quartzProperties());
		sfb.setWaitForJobsToCompleteOnShutdown(true);
		//sfb.setOverwriteExistingJobs(true);

		//sfb.setTriggers(simpleTrigger.getObject());
		return sfb;
	}*/
	
}
