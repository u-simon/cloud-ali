package com.simon.cloud.controller;

import com.alibaba.fastjson.JSON;
import com.simon.cloud.model.Order;
import com.simon.cloud.model.Product;
import com.simon.cloud.service.OrderService;
import com.simon.cloud.service.ProductService;
import com.simon.cloud.service.impl.OrderServiceImpl1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController1 {


    @Autowired
    OrderServiceImpl1 orderService;


    @Autowired
    ProductService productService;

    @RequestMapping("/message")
    public String message1(){
        return "message1";
    }


    @RequestMapping("/tx/message/{pid}")
    public Order txMessage(@PathVariable("pid") Integer pid){
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

        orderService.createOrderBefore(order);
        log.info("创建{}号商品的订单, 内容为:{}", pid, JSON.toJSONString(order));

        return order;
    }
}
