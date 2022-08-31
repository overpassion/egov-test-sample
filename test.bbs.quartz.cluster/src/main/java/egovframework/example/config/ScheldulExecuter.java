package egovframework.example.config;

import javax.annotation.PostConstruct;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;

import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ScheldulExecuter {        
    
    //tomcat 구동 시 메소드가 자동 실행 되도록 하는 어노테이션 	
    //@PostConstruct
    public void schedulerSet() throws Exception {

    	log.debug("===>>> INIT1~~~");
        //jobDetail 설정 		
        MethodInvokingJobDetailFactoryBean jobDetailBean = new MethodInvokingJobDetailFactoryBean();
        jobDetailBean.setTargetObject(new ScheldulExecuter());
        jobDetailBean.setTargetMethod("scheduledStart");
        jobDetailBean.setGroup("test1");
        jobDetailBean.setName("scheduledStart");
        jobDetailBean.afterPropertiesSet();
        
        //cronTriger 설정
        CronTriggerFactoryBean cronTrigger = new CronTriggerFactoryBean();
        //cronTrigger.setJobDetail((JobDetail)jobDetailBean.getObject());
        cronTrigger.setJobDetail(jobDetailBean.getObject());
        cronTrigger.setCronExpression("0/5 * * * * ?");
        cronTrigger.setName("scheduledStart");
        SchedulerFactoryBean sfb = new SchedulerFactoryBean();
        Scheduler sch = sfb.getScheduler();
        
        sch.scheduleJob((JobDetail)jobDetailBean.getObject(),(Trigger) cronTrigger);
        sch.start();
    }
    
    public void scheduledStart() throws Exception {
    	log.debug("===>>> INIT2~~~");
        log.debug("스케쥴 수행!!");
    }
    
}