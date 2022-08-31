package egovframework.example.config;

import org.springframework.stereotype.Component;

@Component("mySchedulingJob")
public class MySchedulingJob {

	public void startJob() {
		System.out.println("===>>> Start Scheduling!,"+this.hashCode());
	}

}
