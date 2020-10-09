package com.simon.mq;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @author fengyue
 * @Date 2020/10/9 4:55 下午
 * @Describe
 */
public class Consumer {

	public static void main(String[] args) throws Exception {
		// 1.创建消息消费者，指定消费者所属的组名
		DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("consumer-group");
		// 2.指定NameServer地址
		consumer.setNamesrvAddr("localhost:9876");
		// 3.指定消费者订阅的主题和标签
		consumer.subscribe("myTopic", "*");
		// 4.设置回调函数，编写处理消息的方法
		consumer.setMessageListener(new MessageListenerConcurrently() {
			@Override
			public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
				try {
					System.out.println(new String(list.get(0).getBody(), "utf-8"));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
			}
		});
		// 5.启动消息消费者
		consumer.start();
	}
}
