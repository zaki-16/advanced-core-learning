package com.zaki.rocket.mq;

import com.zaki.rocket.config.RocketMqConfig;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeOrderlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerOrderly;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author wangzhen
 * @date 2019-11-26
 */
@Component
public class ConsumerClient {

    //    @PostConstruct
    public void init() {
        this.consume();
    }

    public void consume() {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(RocketMqConfig.CONSUMER_GROUP);

        consumer.setNamesrvAddr(RocketMqConfig.NAME_SRV_ADDR);

        try {
            consumer.subscribe(RocketMqConfig.MESSAGE_TOPIC, RocketMqConfig.MESSAGE_TAG_A + " || " + RocketMqConfig.MESSAGE_TAG_B);
        } catch (MQClientException e) {
            e.printStackTrace();
        }

        consumer.registerMessageListener(new MessageListenerOrderly() {
            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs,
                                                       ConsumeOrderlyContext context) {
                //手动确认
                context.setAutoCommit(false);
                msgs.forEach(m -> {
                    System.out.print("host:" + m.getBornHost() + "--");
                    System.out.print("key:" + m.getKeys() + "--");
                    System.out.print("Topic:" + m.getTopic() + "--");
                    System.out.print("QueueId:" + m.getQueueId() + "--");
                    System.out.print("tags:" + m.getTags() + "--");
                    System.out.print("msg:" + new String(m.getBody()));
                    System.out.println();
                });
                return ConsumeOrderlyStatus.SUCCESS;

            }
        });

        try {
            consumer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }

        System.out.println("Consumer Started.%n");
    }

}
