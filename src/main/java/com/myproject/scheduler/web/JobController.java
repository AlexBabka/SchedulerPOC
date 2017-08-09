package com.myproject.scheduler.web;

import com.myproject.scheduler.config.AppConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.IntervalTask;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ScheduledThreadPoolExecutor;

@RestController
@RequestMapping("/jobs")
public class JobController {
    private static final Logger log = LoggerFactory.getLogger(JobController.class);

    @Autowired
    private AppConfig appConfig;
    @Autowired
    private Executor executor;

    @GetMapping(path = "list")
    public List<IntervalTask> getJobList() {
        return appConfig.getScheduledTaskRegistrar().getFixedDelayTaskList();
    }

    @GetMapping(path = "cancel")
    public void removeJob() {
        ScheduledThreadPoolExecutor executor = (ScheduledThreadPoolExecutor) this.executor;

        for (Runnable job : executor.getQueue()) {
            if (job instanceof FutureTask) {
                ((FutureTask) job).cancel(true);
            }
        }
    }

}
