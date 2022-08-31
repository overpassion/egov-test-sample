package egovframework.example.config;

import java.util.Properties;

import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

import lombok.extern.slf4j.Slf4j;

//@Configuration
@Slf4j
public class EgovConfigScheduling {

	private Properties quartzProperties() {
	    Properties props = new Properties();
	    props.put("org.quartz.scheduler.instanceName", "TestScheduler");
	    props.put("org.quartz.scheduler.instanceId", "AUTO");
	    //props.put("org.quartz.jobStore.driverDelegateClass", "org.quartz.impl.jdbcjobstore.StdJDBCDelegate");
	    props.put("org.quartz.jobStore.class", "org.quartz.simpl.RAMJobStore");
	    props.put("org.quartz.threadPool.threadCount", "3");
	    //props.put("org.quartz.jobStore.useProperties", "true");
	    props.put("org.quartz.jobStore.misfireThreshold", "60000");
	    props.put("org.quartz.jobStore.isClustered", "true");
	    props.put("org.quartz.jobStore.useProperties", "false");
	    return props;
	}

	
	//@Bean("jobDetail2")
	public MethodInvokingJobDetailFactoryBean jobDetail2(@Autowired MySchedulingJob2 mySchedulingJob2) {
		log.debug("===>>> jobDetail2");
		MethodInvokingJobDetailFactoryBean mjfb = new MethodInvokingJobDetailFactoryBean();
		mjfb.setTargetObject(mySchedulingJob2);
		mjfb.setTargetMethod("startJob2");
		mjfb.setConcurrent(false);
		
		return mjfb;
	}
	
	//@Bean("simpleTrigger2")
	public SimpleTriggerFactoryBean simpleTrigger2(@Qualifier("jobDetail2") MethodInvokingJobDetailFactoryBean jobDetail2) {
		log.debug("===>>> simpleTrigger2");
		SimpleTriggerFactoryBean stfb = new SimpleTriggerFactoryBean();
		stfb.setJobDetail((JobDetail) jobDetail2.getObject());
		stfb.setStartDelay(10*1000); // milisecond
		stfb.setRepeatInterval(5*1000); // milisecond
		
		return stfb;
	}
	
	//@Bean
	public SchedulerFactoryBean scheduler2(@Qualifier("simpleTrigger2") SimpleTriggerFactoryBean simpleTrigger2) {
		log.debug("===>>> scheduler2");
		//JobDataMap jm = stfb.getJobDataMap();
		//log.debug("===>>> jm = "+jm);
		JobDataMap jm = new JobDataMap();
		jm.put("id", "test");
		simpleTrigger2.setJobDataMap(jm);
		simpleTrigger2.setJobDataAsMap(jm);
		
		SchedulerFactoryBean sfb = new SchedulerFactoryBean();
		sfb.setTriggers(simpleTrigger2.getObject());
		return sfb;
	}
	
}
