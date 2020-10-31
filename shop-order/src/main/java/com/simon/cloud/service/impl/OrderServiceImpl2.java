package com.simon.cloud.service.impl;

import com.alibaba.fastjson.JSON;
import com.simon.cloud.model.Product;
import com.simon.cloud.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;

import com.simon.cloud.dao.OrderDao;
import com.simon.cloud.model.Order;
import com.simon.cloud.service.OrderService;

/**
 * @author fengyue
 * @Date 2020/9/28 7:15 下午
 * @Describe
 */
@Slf4j
@Service
public class OrderServiceImpl2 {
	@Autowired
	OrderDao orderDao;

	@Autowired
	DiscoveryClient discoveryClient;

	@Autowired
	ProductService productService;

	@Autowired
	RocketMQTemplate rocketMQTemplate;

	public Order createOrder(Integer pid) {
		// List<ServiceInstance> instances = discoveryClient.getInstances("service-product");
		// ServiceInstance serviceInstance = instances.get(0);


		// Product product =
		// restTemplate.getForObject("http://service-product/product/" + pid, Product.class);
		Product product = productService.findById(pid);
		log.info("查询到{}号商品,内容为:{}", pid, JSON.toJSONString(product));

		Order order = new Order();
		order.setUid(1);
		order.setUsername("测试用户");
		order.setPid(pid);
		order.setPname(product.getPname());
		order.setPprice(product.getPprice());
		order.setNumber(1);


		orderDao.save(order);
		log.info("创建{}号商品的订单, 内容为:{}", pid, JSON.toJSONString(order));

		// 扣减库存
		productService.reduceInventory(pid, order.getNumber());

		// rocketMQTemplate.convertAndSend("shop-order", order);
		return order;
	}
}
