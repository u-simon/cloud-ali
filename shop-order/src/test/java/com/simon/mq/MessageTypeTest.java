package com.simon.mq;

import com.simon.cloud.OrderApplication;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author simon
 * @date 2020/10/9 10:54 下午
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OrderApplication.class)
public class MessageTypeTest {

    @Autowired
    RocketMQTemplate rocketMQTemplate;

    @Test
    public void testSyncSend() {
        SendResult result = rocketMQTemplate.syncSend("test-topic-1:sync", "这是一条同步消息");
        System.out.println(result);
    }

    @Test
    public void testAsyncSend() throws InterruptedException {
        rocketMQTemplate.asyncSend("test-topic-1:aysnc", "这是一条异步消息", new SendCallback() {
            @Override
            public void onSuccess(SendResult sendResult) {
                System.out.println(sendResult);
            }

            @Override
            public void onException(Throwable throwable) {

            }
        });

        System.out.println("==========");
        Thread.sleep(3000000000L);
    }

    @Test
    public void testOneWay() {
        rocketMQTemplate.sendOneWay("test-topic-1:oneway", "这是一条单项消息");
    }

    @Test
    public void testOneWayOrderly() {
        for (int i = 0; i < 10; i++) {
            rocketMQTemplate.sendOneWayOrderly("test-topic-1:oneway", "这是一条单项消息", "1234");
        }
    }
}
