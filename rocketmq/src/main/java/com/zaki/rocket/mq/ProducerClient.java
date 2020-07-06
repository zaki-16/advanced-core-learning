package com.zaki.rocket.mq;

import com.zaki.rocket.config.RocketMqConfig;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

/**
 * @author wangzhen
 * @date 2019-11-26
 */
@Component
public class ProducerClient {

    //    @PostConstruct
    public void init() {
        this.produce();
    }

    public void produce() {
        DefaultMQProducer producer = new DefaultMQProducer(RocketMqConfig.BROKER_GROUP);

        producer.setNamesrvAddr(RocketMqConfig.NAME_SRV_ADDR);
        try {
            producer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }

        String[] tags = new String[]{RocketMqConfig.MESSAGE_TAG_A, RocketMqConfig.MESSAGE_TAG_B};

        for (int i = 0; i < 100; i++) {
            int orderId = i % 10;

            System.out.println("orderId={}" + orderId);

            //创建消息
            Message msg = null;
            try {
                msg = new Message(RocketMqConfig.MESSAGE_TOPIC, tags[i % tags.length], "KEY" + i,
                        ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            //发送消息，重写选择MessageQueue 方法，把消息写到对应的ConsumerQueue 中
            // orderId 参数传递到内部方法 select arg 参数
            SendResult sendResult = null;
            try {
                sendResult = producer.send(msg, (mqs, msg1, arg) -> {
                    System.out.println("arg{}" + arg);
                    Integer id = (Integer) arg;
                    int index = id % mqs.size();
                    //返回选中的队列
                    return mqs.get(index);
                }, orderId);
            } catch (MQClientException | InterruptedException | MQBrokerException | RemotingException e) {
                e.printStackTrace();
            }
            System.out.printf("%s%n", sendResult);
        }
        //server shutdown
        producer.shutdown();
    }
}
