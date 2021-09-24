package com.example.demo.repository;

import com.example.demo.model.PayOption;
import org.springframework.data.repository.CrudRepository;

public interface PayOptionRepository extends CrudRepository<PayOption, Integer> {

    PayOption findByName(String name);

}