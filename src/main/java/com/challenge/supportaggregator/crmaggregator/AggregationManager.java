package com.challenge.supportaggregator.crmaggregator;

import com.challenge.supportaggregator.entitymodel.AggregatedData;
import com.challenge.supportaggregator.entitymodel.AggregatedDataKey;
import com.challenge.supportaggregator.entitymodel.AggregatedDataRepository;
import com.challenge.supportaggregator.entitymodel.Case;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;

@Component
public class AggregationManager {

    @Autowired
    private AggregatedDataRepository aggregatedDataRepository;


    public void updateAggregation(Case curCase) {
        Optional<AggregatedData> aggregatedDataOptional = aggregatedDataRepository.findById(new AggregatedDataKey(curCase.getErrorCode(), curCase.getProvider(), curCase.getStatus()));
        if(aggregatedDataOptional.isEmpty()) {

            AggregatedData aggregatedData = new AggregatedData();
            aggregatedData.setStatus(curCase.getStatus());
            aggregatedData.setProvider(curCase.getProvider());
            aggregatedData.setErrorCode(curCase.getErrorCode());
            aggregatedData.setCases(new ArrayList<>());
            curCase.setAggregatedData(aggregatedData);
            aggregatedDataRepository.save(curCase.getAggregatedData());
        }
        else {
            curCase.setAggregatedData(aggregatedDataOptional.get());

        }
        curCase.getAggregatedData().getCases().add(curCase);
        aggregatedDataRepository.save(curCase.getAggregatedData());

    }

    public Iterable<AggregatedData> getCases() {
        return aggregatedDataRepository.findAll();
    }
}
