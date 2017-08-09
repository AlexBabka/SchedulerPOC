package com.myproject.scheduler.config;

import com.myproject.scheduler.job.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.IntervalTask;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Configuration
public class AppConfig implements SchedulingConfigurer {
    @Autowired
    private List<Job> jobList;

    private ScheduledTaskRegistrar scheduledTaskRegistrar;

    public ScheduledTaskRegistrar getScheduledTaskRegistrar() {
        return scheduledTaskRegistrar;
    }

    @Bean(destroyMethod = "shutdown")
    public Executor taskExecutor() {
        return Executors.newScheduledThreadPool(100);
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        this.scheduledTaskRegistrar = taskRegistrar;
        taskRegistrar.setScheduler(taskExecutor());

        for (Job job : jobList) {
            taskRegistrar.addFixedDelayTask(new IntervalTask(job, 5000L));
        }
    }
}
