import java.text.SimpleDateFormat;
import java.util.Date;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class TestJob implements Job {
	private static final SimpleDateFormat TIMESTAMP_FMT = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSSS");

	@Override
	public void execute(JobExecutionContext ctx) throws JobExecutionException {
		System.out.println("Job Executed [" + new Date(System.currentTimeMillis()) + "]");

		JobDataMap dataMap = ctx.getJobDetail().getJobDataMap();
		String currentDate = TIMESTAMP_FMT.format(new Date());
		String triggerKey = ctx.getTrigger().getKey().toString();

		String jobSays = dataMap.getString("jobSays");

		float myFloatValue = dataMap.getFloat("myFloatValue");

		System.out.println(String.format("[%s][%s] %s %s", currentDate, triggerKey, jobSays, myFloatValue));
	}
}
