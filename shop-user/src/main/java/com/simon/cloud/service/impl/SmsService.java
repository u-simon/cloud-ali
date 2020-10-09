package com.simon.cloud.service.impl;

import com.alibaba.fastjson.JSON;
import com.simon.cloud.model.Order;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.stereotype.Service;

/**
 * @author fengyue
 * @Date 2020/10/9 6:43 下午
 * @Describe
 */
@Slf4j
@Service
@RocketMQMessageListener(topic = "shop-order", consumerGroup = "shop-user")
public class SmsService implements RocketMQListener<Order> {

    @Override
    public void onMessage(Order order){
        log.info("收到一个订单信息:{},接下来发送短信", JSON.toJSONString(order));
    }
}
