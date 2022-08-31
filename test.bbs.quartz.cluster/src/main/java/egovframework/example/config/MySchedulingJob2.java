package egovframework.example.config;

import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

@Repository("mySchedulingJob2")
@Slf4j
public class MySchedulingJob2 {

	public void startJob2() {
		log.debug("===>>> Run Scheduling2!,"+this.hashCode());
	}

}
