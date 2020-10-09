package com.simon.mq;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

/**
 * @author fengyue
 * @Date 2020/10/9 4:09 下午
 * @Describe
 */
public class Producer {
	public static void main(String[] args)
			throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
		// 1.创建生产者，指定生产者所属的组名
		DefaultMQProducer producer = new DefaultMQProducer("producer-group");
		// 2.指定nameserver地址
		producer.setNamesrvAddr("127.0.0.1:9876");
		// 3.启动生产者
		producer.start();
		// 4.创建消息对象，指定主题，标签和消息体
		Message message = new Message("myTopic", "myTag", "RocketMq message".getBytes());
		// 5.发送消息
		SendResult send = producer.send(message, 60000);
		System.out.println(send);
		// 6.关闭生产者
		producer.shutdown();
	}
}
