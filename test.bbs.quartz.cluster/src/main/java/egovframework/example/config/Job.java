package egovframework.example.config;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Job {

    public void start() throws Exception {
    	System.out.println("===>>> INIT Job~~~");
        log.debug("JOB 수행!!");
    }

}
