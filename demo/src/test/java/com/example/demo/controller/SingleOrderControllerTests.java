package com.example.demo.controller;

import com.example.demo.model.output.OrderOutput;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class SingleOrderControllerTests extends OrderControllerTests {

    @Test
    public void getSingleOrderDetail() throws Exception {

        // GIVEN 3 orders set on the DB

        // WHEN a single order is requested
        ResponseEntity<OrderOutput> responseEntity = this.restTemplate.getForEntity(SERVICE_URL + port + SINGLE_ORDER_URL + '/' + ORDER_UUID,
                OrderOutput.class);

        // THEN the request executes successfully
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // AND 3 orders are retrieved
        OrderOutput singleOrder = responseEntity.getBody();

        verifyFirstOrder(singleOrder);

    }

    @Test
    public void getSingleOrderDetailNotFound() throws Exception {

        // GIVEN 3 orders set on the DB

        // WHEN a single order is requested
        ResponseEntity<OrderOutput> responseEntity = this.restTemplate.getForEntity(SERVICE_URL + port + SINGLE_ORDER_URL + UUID.randomUUID(),
                OrderOutput.class);

        // THEN the request executes successfully but the order was not found
        assertEquals(404, responseEntity.getStatusCode().value());
    }

}
