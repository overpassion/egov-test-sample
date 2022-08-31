import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class QuartzTest {
	public static void main(String[] args) {
		try {

			SchedulerFactory schedulerFactory = new StdSchedulerFactory();
			Scheduler scheduler = schedulerFactory.getScheduler();

			JobDataMap jobDataMap = new JobDataMap();
			jobDataMap.put("jobSays", "Say Hello World!");
			jobDataMap.put("myFloatValue", 3.1415f);

			JobDetail jobDetail = JobBuilder.newJob(TestJob.class).withIdentity("myJob", "group1")
					.setJobData(jobDataMap).build();

			//@SuppressWarnings("deprecation")
			SimpleTrigger simpleTrigger = (SimpleTrigger) TriggerBuilder.newTrigger()
					.withIdentity("simple_trigger", "simple_trigger_group")
					.startAt(new Date(2021 - 1900, 10, 14, 13, 0))
					.withSchedule(SimpleScheduleBuilder.repeatSecondlyForTotalCount(5, 10)).forJob(jobDetail).build();
			CronTrigger cronTrigger = (CronTrigger) TriggerBuilder.newTrigger()
					.withIdentity("trggerName", "cron_trigger_group")
					.withSchedule(CronScheduleBuilder.cronSchedule("5 * * * * ?")).forJob(jobDetail).build();

			Set<Trigger> triggerSet = new HashSet<Trigger>();
			triggerSet.add(simpleTrigger);
			triggerSet.add(cronTrigger);
			scheduler.scheduleJob(jobDetail, triggerSet, false);
			scheduler.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
