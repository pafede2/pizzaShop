package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="Customer do not provide all mandatory fields")
public class CustomerMalFormedException extends RuntimeException {
}
