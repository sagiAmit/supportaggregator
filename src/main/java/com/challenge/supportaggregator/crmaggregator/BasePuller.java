package com.challenge.supportaggregator.crmaggregator;

import com.challenge.supportaggregator.entitymodel.Case;
import com.challenge.supportaggregator.entitymodel.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Slf4j
public abstract class BasePuller implements ICRMPuller  {

    @Autowired
    private RestTemplate restTemplate;

    protected abstract String getUrl();

    @Override
    public List<Case> getCases() {
        log.info("updating from CRM: " + getName());
        try {
            return  Arrays.asList(restTemplate.getForObject(
                    getUrl(), Data.class).getData());
        }
        catch (ResourceAccessException exception) {
            log.error("failed connecting to " + getUrl() + " error:" + exception);
        }
        catch (RestClientException exception) {
            log.error("failed deserializing data error:" + exception);
        }
        return Collections.EMPTY_LIST;
    }
}
