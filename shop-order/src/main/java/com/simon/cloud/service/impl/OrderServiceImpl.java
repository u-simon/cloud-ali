package com.simon.cloud.service.impl;

import com.simon.cloud.dao.OrderDao;
import com.simon.cloud.model.Order;
import com.simon.cloud.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author fengyue
 * @Date 2020/9/28 7:15 下午
 * @Describe
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderDao orderDao;

    @Override
    public void create(Order order) {
        orderDao.save(order);
    }
}
