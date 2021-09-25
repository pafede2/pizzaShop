package com.example.demo.controller;

import com.example.demo.model.PayOption;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class AllPayOptionsControllerTests extends OrderControllerTests {

    @Test
    public void getAllThePayOptions() throws Exception {

        // GIVEN 3 customers set on the DB

        // WHEN all the customers are requested
        ResponseEntity<String[]> responseEntity = this.restTemplate.getForEntity( SERVICE_URL + port + ALL_PAY_OPTIONS_URL,
                String[].class);

        // THEN the request executes successfully
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // AND 3 customers are retrieved
        String[] payOptions = responseEntity.getBody();
        assertEquals(2, payOptions.length);

        // AND the details of first order are correct
        String payOption = payOptions[0];
        assertEquals("ONLINE", payOption);

        payOption = payOptions[1];
        assertEquals("OFFLINE", payOption);

    }

}
