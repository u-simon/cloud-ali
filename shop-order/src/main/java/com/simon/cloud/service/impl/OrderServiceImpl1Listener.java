package com.simon.cloud.service.impl;

import com.simon.cloud.model.TxLog;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import com.simon.cloud.dao.TxLogDao;
import com.simon.cloud.model.Order;

import java.util.Optional;

/**
 * @author fengyue
 * @Date 2020/9/28 7:15 下午
 * @Describe
 */
@Service
@RocketMQTransactionListener(txProducerGroup = "tx_trans_group")
public class OrderServiceImpl1Listener implements RocketMQLocalTransactionListener {
	@Autowired
	OrderServiceImpl1 orderServiceImpl1;

	@Autowired
	RocketMQTemplate rocketMQTemplate;

	@Autowired
	TxLogDao txLogDao;

	@Override
	public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object o) {
		try {
			String txId = (String) message.getHeaders().get("txId");
			Order order = (Order) o;
			orderServiceImpl1.createOrder(order, txId);
			return RocketMQLocalTransactionState.COMMIT;
		} catch (Exception e){

		}
		return RocketMQLocalTransactionState.ROLLBACK;
	}

	@Override
	public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
		String txId = (String) message.getHeaders().get("txId");
		Optional<TxLog> txLog = txLogDao.findById(txId);
		return txLog.isPresent() ? RocketMQLocalTransactionState.COMMIT : RocketMQLocalTransactionState.ROLLBACK;
	}
}
