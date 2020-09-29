package com.simon.cloud.controller;

import com.simon.cloud.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/prod/{pid}")
    public Order order(@PathVariable("pid") Integer pid) {
//        Product product = restTemplate.getForObject()
        return null;
    }

}
