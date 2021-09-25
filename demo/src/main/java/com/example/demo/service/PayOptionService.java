package com.example.demo.service;

import com.example.demo.model.PayOption;
import com.example.demo.repository.PayOptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class PayOptionService {

    @Autowired
    PayOptionRepository payOptionRepository;

    public List<String> getAllPayOptions()
    {
        List<String> payOptions = new ArrayList<String>();
        payOptionRepository.findAll().forEach(payOption -> payOptions.add(payOption.getName()));
        return payOptions;
    }


}
