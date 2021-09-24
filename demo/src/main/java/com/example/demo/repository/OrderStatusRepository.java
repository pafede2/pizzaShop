package com.example.demo.repository;

import com.example.demo.model.OrderStatus;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface OrderStatusRepository extends CrudRepository<OrderStatus, Integer> {

    OrderStatus findByName(String name);

}