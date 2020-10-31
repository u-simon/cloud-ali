package com.simon.cloud.controller;

import com.simon.cloud.service.impl.OrderServiceImpl2;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.simon.cloud.model.Order;
import com.simon.cloud.model.Product;
import com.simon.cloud.service.OrderService;
import com.simon.cloud.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController2 {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    OrderServiceImpl2 orderService;


    @RequestMapping("/prod/{pid}")
    public Order order(@PathVariable("pid") Integer pid) {
        return orderService.createOrder(pid);
    }

}
