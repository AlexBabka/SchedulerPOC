package com.myproject.scheduler.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class SimpleJob {
    private static final Logger log = LoggerFactory.getLogger(SimpleJob.class);

    @Scheduled(fixedRate = 5000)
    public void execute() {
        log.info("Executing job: " + LocalDateTime.now());
    }
}
