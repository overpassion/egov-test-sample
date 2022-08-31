package egovframework.example.config;

import javax.annotation.PostConstruct;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
//@EnableScheduling
public class MyTest {

	//@PostConstruct
	public void init() throws Exception {
		log.debug("===>>> Init OK!!!");
	}

	//@Scheduled(fixedRateString = "5", initialDelay = 3000)
	private void scheduleTest() {
		log.debug("hello scheduled~~~");
	}

}
