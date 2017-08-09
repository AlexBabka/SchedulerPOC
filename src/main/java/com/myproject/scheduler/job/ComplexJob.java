package com.myproject.scheduler.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ComplexJob implements Job {
    private static final Logger log = LoggerFactory.getLogger(ComplexJob.class);

    public void run() {
        log.info("Executing complex job: " + LocalDateTime.now());
    }

    @Override
    public String getName() {
        return "Complex Job";
    }
}
