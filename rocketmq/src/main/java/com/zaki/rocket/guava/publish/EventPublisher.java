package com.zaki.rocket.guava.publish;

import com.google.common.eventbus.EventBus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 事件发布器
 */
@Component
@Slf4j
public final class EventPublisher {

    @Autowired
    private EventBus syncEventBus;

    @Autowired
    private EventBus asyncEventBus;


    public void publish(Object event, boolean isAsync) {
        if (null == event) {
            log.warn("publish event is null");
            return;
        }
        EventBus eventBus = isAsync ? asyncEventBus : syncEventBus;
        eventBus.post(event);
    }
}