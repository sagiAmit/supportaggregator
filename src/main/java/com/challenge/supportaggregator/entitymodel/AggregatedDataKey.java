package com.challenge.supportaggregator.entitymodel;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class AggregatedDataKey implements Serializable {

    private int errorCode;
    private int provider;
    private String status;
}
