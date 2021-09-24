package com.example.demo.repository;


import org.springframework.data.repository.CrudRepository;
import com.example.demo.model.Customer;

import java.util.UUID;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {

    Customer findByUuid(UUID uuid);
}