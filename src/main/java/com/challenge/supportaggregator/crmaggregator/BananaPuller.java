package com.challenge.supportaggregator.crmaggregator;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class BananaPuller extends  BasePuller{

    private static final String CRM_NAME = "banana";


    @Value("${crmaggregator.bananaurl}")
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
