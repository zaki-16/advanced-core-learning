package com.zaki.rocket.config;

import com.google.common.eventbus.EventBus;
import com.zaki.rocket.guava.publish.BaseListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author wangzhen
 * @date 2019-11-26
 */
@Configuration
public class EventConfig {

    @Bean
    public EventBus eventBus(List<BaseListener> listeners) {
        EventBus eventBus = new EventBus();


        listeners.forEach(eventBus::register);
        return eventBus;
    }
}
