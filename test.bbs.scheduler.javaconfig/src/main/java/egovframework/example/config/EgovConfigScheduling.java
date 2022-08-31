package egovframework.example.config;

import org.quartz.JobDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

//@Configuration
public class EgovConfigScheduling {

	//@Bean
	public MethodInvokingJobDetailFactoryBean jobDetail(@Autowired MySchedulingJob mySchedulingJob) {
		System.out.println("===>>> jobDetail");
		MethodInvokingJobDetailFactoryBean mjfb = new MethodInvokingJobDetailFactoryBean();
		mjfb.setTargetObject(mySchedulingJob);
		mjfb.setTargetMethod("startJob");
		mjfb.setConcurrent(false);
		return mjfb;
	}
	
	//@Bean
	public SimpleTriggerFactoryBean simpleTrigger(@Autowired MethodInvokingJobDetailFactoryBean jobDetail) {
		System.out.println("===>>> simpleTrigger");
		SimpleTriggerFactoryBean stfb = new SimpleTriggerFactoryBean();
		stfb.setJobDetail((JobDetail) jobDetail.getObject());
		stfb.setStartDelay(10*1000); // milisecond
		stfb.setRepeatInterval(5*1000); // milisecond
		
		return stfb;
	}
	
	//@Bean
	public SchedulerFactoryBean scheduler(@Autowired SimpleTriggerFactoryBean simpleTrigger) {
		System.out.println("===>>> scheduler");
		SchedulerFactoryBean sfb = new SchedulerFactoryBean();
		sfb.setTriggers(simpleTrigger.getObject());
		return sfb;
	}
}
