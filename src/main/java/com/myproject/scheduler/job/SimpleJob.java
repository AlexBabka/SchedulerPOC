package com.myproject.scheduler.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class SimpleJob implements Runnable {
    private static final Logger log = LoggerFactory.getLogger(SimpleJob.class);

    public void run() {
        log.info("Executing job: " + LocalDateTime.now());
    }
}
