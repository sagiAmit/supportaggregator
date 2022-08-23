package com.challenge.supportaggregator.controller;

import com.challenge.supportaggregator.crmaggregator.AggregationManager;
import com.challenge.supportaggregator.entitymodel.AggregatedData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;


@Controller
@RequestMapping(path="/supportaggregator")
@Slf4j
public class AggrgatorController {


    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Autowired
    private AggregationManager aggregationManager;


    @GetMapping(path="/cases")
    public @ResponseBody Iterable<AggregatedData> getCases() {

        log.info("returning all cases");
        return aggregationManager.getCases();
    }

}
