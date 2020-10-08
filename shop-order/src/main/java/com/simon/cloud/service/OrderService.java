package com.simon.cloud.service;

import com.simon.cloud.model.Order;

/**
 * @author fengyue
 * @Date 2020/9/28 7:15 下午
 * @Describe
 */
public interface OrderService {
    /**
     * 创建订单
     * @param order
     */
    void create(Order order);
}
