package com.challenge.supportaggregator.crmaggregator;

import org.quartz.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {

    private static final String PULLER_TASK = "PullerTask";

    @Bean
    public JobDetail PullerQuartzDetail(){

        return JobBuilder.newJob(PullerTask.class).withIdentity("PullerTask").storeDurably().build();

    }

    @Value("${crmaggregator.schedule}")
    private int schedule;

    @Bean
    public Trigger PullerQuartzTrigger(){

        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInSeconds(schedule)  //Set time cycle unit seconds
                .repeatForever();
        return TriggerBuilder.newTrigger().forJob(PullerQuartzDetail())

                .withIdentity(PULLER_TASK)
                .withSchedule(scheduleBuilder)
                .build();

    }

}
