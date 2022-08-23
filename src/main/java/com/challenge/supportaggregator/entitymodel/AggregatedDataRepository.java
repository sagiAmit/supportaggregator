package com.challenge.supportaggregator.entitymodel;

import org.springframework.data.repository.CrudRepository;

public interface AggregatedDataRepository extends CrudRepository<AggregatedData, AggregatedDataKey> {

}