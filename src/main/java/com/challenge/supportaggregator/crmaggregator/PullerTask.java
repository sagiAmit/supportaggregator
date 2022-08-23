package com.challenge.supportaggregator.crmaggregator;

import com.challenge.supportaggregator.entitymodel.*;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.List;

@Slf4j
public class PullerTask extends QuartzJobBean {

    @Autowired
    private List<ICRMPuller> pullers;

    @Autowired
    private AggregationManager aggregationManager;



    /**
     * Perform timed tasks
     * @param jobExecutionContext
     * @throws JobExecutionException
     */

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        pullers.parallelStream().forEach(icrmPuller -> {
             runPuller(icrmPuller);});
    }



    public void runPuller(ICRMPuller puller) {
        List<Case> cases = puller.getCases();
        cases.forEach(cCase -> {
            cCase.setCrm(puller.getName());
            aggregationManager.updateAggregation(cCase);
        });

    }


}
