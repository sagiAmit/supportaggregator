package com.challenge.supportaggregator.crmaggregator;

import com.challenge.supportaggregator.entitymodel.Case;

import java.util.List;

public interface ICRMPuller {

    public List<Case> getCases();

    public String getName();
}
