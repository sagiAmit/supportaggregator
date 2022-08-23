package com.challenge.supportaggregator.crmAggregator;

import com.challenge.supportaggregator.crmaggregator.BananaPuller;
import com.challenge.supportaggregator.entitymodel.Case;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@Slf4j

@ExtendWith(SpringExtension.class)
@SpringBootTest()
public class BananaPullerTests {

    @Autowired
    private BananaPuller bananaPuller;


    @Autowired
   private RestTemplate template;

    //@Autowired
    private MockRestServiceServer mockRestServiceServer;

    @BeforeEach
    public void setUp() {
        this.mockRestServiceServer= MockRestServiceServer.createServer(template);
    }


    @Test
    public void parseCaseSuccessfully() {

        String json = "{ \"data\": [{\"Case ID\": 1,\"Customer_ID\": 818591,\"Provider\": 6111,\"CREATED_ERROR_CODE\": 324,\"STATUS\": \"Open\",\"TICKET_CREATION_DATE\": \"3/14/2019 16:30\",\"LAST_MODIFIED_DATE\": \"3/17/2019 3:41\",\"PRODUCT_NAME\": \"BLUE\"}]}";

        this.mockRestServiceServer
                .expect(requestTo("http://fakeurl/homeassignment/banana"))
                .andRespond(withSuccess(json, MediaType.APPLICATION_JSON));

        List<Case> result = bananaPuller.getCases();
        assertNotNull(result);
        assertEquals(1, result.size());
        String pattern = "M/d/yyyy H:m";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        Case testCase = new Case();
        Case output = result.get(0);
        assertEquals(1, output.getCaseId());
        assertEquals(818591, output.getCustomerID());
        assertEquals(Timestamp.valueOf(LocalDateTime.from(formatter.parse("3/14/2019 16:30"))), output.getCreationDate());
        assertEquals(Timestamp.valueOf(LocalDateTime.from(formatter.parse("3/17/2019 3:41"))), output.getModifiedDate());
        assertEquals(6111, output.getProvider());
        assertEquals("Open", output.getStatus());
        assertEquals(324, output.getErrorCode());
        assertEquals("BLUE", output.getProductName());

        log.info(result.toString());

    }

    @Test
    public void badDataTest() {
        String json = "{asdad}";
        this.mockRestServiceServer
                .expect(requestTo("http://fakeurl/homeassignment/banana"))
                .andRespond(withSuccess(json, MediaType.APPLICATION_JSON));
        List<Case> result = bananaPuller.getCases();
        assertNotNull(result);
        assertEquals(0, result.size());
    }



}