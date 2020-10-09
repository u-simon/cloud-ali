package com.simon.cloud.controller;

import com.alibaba.fastjson.JSON;
import com.simon.cloud.model.Order;
import com.simon.cloud.model.Product;
import com.simon.cloud.service.OrderService;
import com.simon.cloud.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    OrderService orderService;

    @Autowired
    DiscoveryClient discoveryClient;

    @Autowired
    ProductService productService;

    @Autowired
    RocketMQTemplate rocketMQTemplate;

    @RequestMapping("/prod/{pid}")
    public Order order(@PathVariable("pid") Integer pid) {

//        List<ServiceInstance> instances = discoveryClient.getInstances("service-product");
//        ServiceInstance serviceInstance = instances.get(0);


//        Product product =
//                restTemplate.getForObject("http://service-product/product/" + pid, Product.class);
        Product product = productService.findById(pid);
        if (product.getPid() == -100){

            Order order = new Order();
            order.setPid(-100);
            order.setPname("下单失败");
            return order;
        }
        log.info("查询到{}号商品,内容为:{}", pid, JSON.toJSONString(product));

        Order order = new Order();
        order.setUid(1);
        order.setUsername("测试用户");
        order.setPid(pid);
        order.setPname(product.getPname());
        order.setPprice(product.getPprice());
        order.setNumber(1);


        orderService.create(order);
        log.info("创建{}号商品的订单, 内容为:{}", pid, JSON.toJSONString(order));

        rocketMQTemplate.convertAndSend("shop-order", order);
        return order;
    }

}
