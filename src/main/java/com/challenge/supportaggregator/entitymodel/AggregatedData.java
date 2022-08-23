package com.challenge.supportaggregator.entitymodel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@IdClass(AggregatedDataKey.class)
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class AggregatedData {
    @Id
    private int errorCode;
    @Id
    private int provider;
    @Id
    private String status;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "aggregatedData"
    )
    private List<Case> cases;

}
