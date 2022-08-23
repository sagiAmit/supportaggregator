package com.challenge.supportaggregator.entitymodel;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "cases")
@IdClass(CaseKey.class)
@EqualsAndHashCode
public class Case {
    @JsonIgnore
    @Id
    private String crm;
    @JsonProperty("Case ID")
    @Id
    private int caseId;
    @JsonProperty("Customer_ID")
    private int customerID;
    @JsonProperty("Provider")
    private int provider;
    @JsonProperty("CREATED_ERROR_CODE")
    private int errorCode;
    @JsonProperty("STATUS")
    private String Status;
    @JsonProperty("TICKET_CREATION_DATE")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "M/d/yyyy H:m")
    private Timestamp creationDate;
    @JsonProperty("LAST_MODIFIED_DATE")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "M/d/yyyy H:m")
    private Timestamp modifiedDate;
    @JsonProperty("PRODUCT_NAME")
    private String productName;

    @ManyToOne( fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    @JoinColumns({
            @JoinColumn(name = "fk_errorCode", nullable = false),
            @JoinColumn(name = "fk_provider", nullable = false),
            @JoinColumn(name = "fk_status", nullable = false)
    })
    @JsonIgnore
    private AggregatedData aggregatedData;

    public String toString() {
        return String.valueOf(caseId);
    }

}
