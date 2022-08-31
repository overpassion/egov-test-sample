package egovframework.example.config;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

@Repository("mySchedulingJob")
@Slf4j
public class MySchedulingJob implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2794733280116084664L;

	public void startJob() {
		log.debug("===>>> Run Scheduling!,"+this.hashCode());
	}

}
