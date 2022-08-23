package com.challenge.supportaggregator;

import com.challenge.supportaggregator.entitymodel.AggregatedDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@ComponentScan
public class CoreTestConfiguration {

    private RestTemplate restTemplate;
    @Autowired
    public CoreTestConfiguration(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }
}


