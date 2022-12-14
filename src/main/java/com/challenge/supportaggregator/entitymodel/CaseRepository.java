package com.challenge.supportaggregator.entitymodel;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CaseRepository extends CrudRepository<Case, CaseKey> {

}
