package com.challenge.supportaggregator.crmaggregator;

import com.challenge.supportaggregator.entitymodel.Case;
import com.challenge.supportaggregator.entitymodel.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Component
public class StrawberryPuller  extends  BasePuller {

    private static final String CRM_NAME = "strawberry";
    @Autowired
    private RestTemplate restTemplate;

    @Value("${crmaggregator.strawberryurl}")
    private String url;


    @Override
    public String getName() {
        return CRM_NAME;
    }

    @Override
    public String getUrl() {
        return url;
    }
}
