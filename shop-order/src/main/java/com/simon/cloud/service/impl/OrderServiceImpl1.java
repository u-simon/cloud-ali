package com.simon.cloud.service.impl;

import com.simon.cloud.dao.TxLogDao;
import com.simon.cloud.model.TxLog;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.simon.cloud.dao.OrderDao;
import com.simon.cloud.model.Order;
import com.simon.cloud.service.OrderService;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

/**
 * @author fengyue
 * @Date 2020/9/28 7:15 下午
 * @Describe
 */
@Service
public class OrderServiceImpl1 {
	@Autowired
	OrderDao orderDao;

	@Autowired
	RocketMQTemplate rocketMQTemplate;

	@Autowired
	TxLogDao txLogDao;



	public void createOrderBefore(Order order) {
		// 发送半事务消息
		rocketMQTemplate.sendMessageInTransaction("tx_trans_group", "tx_topic", MessageBuilder
				.withPayload(order).setHeader("txId", UUID.randomUUID().toString()).build(), order);
	}



	// 执行本地事务
	@Transactional(rollbackFor = Exception.class)
	public void createOrder(Order order, String txId) {
		orderDao.save(order);

		TxLog txLog = new TxLog();
		txLog.setTxId(txId);
		txLog.setDate(new Date());
		txLogDao.save(txLog);
	}
}
