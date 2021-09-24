package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Delivery address cannot be null")
public class DeliveryAddressNotNullException extends RuntimeException {
}
