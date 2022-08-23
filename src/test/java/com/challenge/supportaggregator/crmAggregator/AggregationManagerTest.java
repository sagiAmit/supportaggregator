package com.challenge.supportaggregator.crmAggregator;

import com.challenge.supportaggregator.crmaggregator.AggregationManager;
import com.challenge.supportaggregator.entitymodel.AggregatedData;
import com.challenge.supportaggregator.entitymodel.AggregatedDataRepository;
import com.challenge.supportaggregator.entitymodel.Case;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest()
class AggregationManagerTest {

    @Autowired
    private AggregationManager aggregationManager;

    @MockBean
    private AggregatedDataRepository aggregatedDataRepository;




    @Test
    public void testNewEntry() {
        String pattern = "M/d/yyyy H:m";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        Case testCase = new Case();
        testCase.setCaseId(1);
        testCase.setCrm("banana");
        testCase.setCustomerID(3);
        testCase.setCreationDate(Timestamp.valueOf(LocalDateTime.from(formatter.parse("3/14/2019 16:30"))));
        testCase.setModifiedDate(Timestamp.valueOf(LocalDateTime.from(formatter.parse("3/17/2019 3:41"))));
        testCase.setProvider(1);
        testCase.setStatus("OPEN");
        testCase.setErrorCode(0);
        testCase.setProductName("BLUE");
        aggregationManager.updateAggregation(testCase);

        verify(aggregatedDataRepository, times(2)).save(any(AggregatedData.class));



    }

    @Test
    public void testAdditionalEntry() {
        List<Case>  cases = new ArrayList<>();
        cases.add((new Case()));
        AggregatedData curData = new AggregatedData(0, 1, "OPEN", cases);
        when(aggregatedDataRepository.findById(any())).thenReturn(Optional.of(curData));
        String pattern = "M/d/yyyy H:m";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        Case testCase = new Case();
        testCase.setCaseId(1);
        testCase.setCrm("banana");
        testCase.setCustomerID(3);
        testCase.setCreationDate(Timestamp.valueOf(LocalDateTime.from(formatter.parse("3/14/2019 16:30"))));
        testCase.setModifiedDate(Timestamp.valueOf(LocalDateTime.from(formatter.parse("3/17/2019 3:41"))));
        testCase.setProvider(1);
        testCase.setStatus("OPEN");
        testCase.setErrorCode(0);
        testCase.setProductName("BLUE");
        aggregationManager.updateAggregation(testCase);
        assertEquals(2, curData.getCases().size());
        assert(curData.getCases().contains(testCase));
        assert(curData.getCases().contains(testCase));
        verify(aggregatedDataRepository, times(1)).save(curData);



    }
}