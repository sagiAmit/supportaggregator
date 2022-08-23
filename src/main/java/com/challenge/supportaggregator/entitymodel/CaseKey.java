package com.challenge.supportaggregator.entitymodel;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CaseKey implements Serializable {
    private int caseId;

    private String crm;


}