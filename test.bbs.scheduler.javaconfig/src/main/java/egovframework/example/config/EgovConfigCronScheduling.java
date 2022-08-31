package egovframework.example.config;

import org.quartz.JobDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

@Configuration
public class EgovConfigCronScheduling {

	@Bean
	public MethodInvokingJobDetailFactoryBean jobDetail(@Autowired MySchedulingJob mySchedulingJob) {
		System.out.println("===>>> cron jobDetail");
		MethodInvokingJobDetailFactoryBean mjfb = new MethodInvokingJobDetailFactoryBean();
		mjfb.setTargetObject(mySchedulingJob);
		mjfb.setTargetMethod("startJob");
		mjfb.setConcurrent(false);
		return mjfb;
	}
	
	@Bean
	public CronTriggerFactoryBean simpleTrigger(@Autowired MethodInvokingJobDetailFactoryBean jobDetail) {
		System.out.println("===>>> cron simpleTrigger");
		CronTriggerFactoryBean stfb = new CronTriggerFactoryBean();
		stfb.setJobDetail((JobDetail) jobDetail.getObject());
		stfb.setCronExpression("0/5 * * * * ?"); // /5 초마다
		
		return stfb;
	}
	
	@Bean
	public SchedulerFactoryBean scheduler(@Autowired CronTriggerFactoryBean simpleTrigger) {
		System.out.println("===>>> cron scheduler");
		SchedulerFactoryBean sfb = new SchedulerFactoryBean();
		sfb.setTriggers(simpleTrigger.getObject());
		return sfb;
	}
}
