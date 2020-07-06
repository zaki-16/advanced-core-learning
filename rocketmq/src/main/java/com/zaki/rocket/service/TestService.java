package com.zaki.rocket.service;

import com.zaki.rocket.guava.publish.EventPublisher;
import com.zaki.rocket.guava.publish.SomeEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author wangzhen
 * @date 2019-11-26
 */
@Component
public class TestService {

    @Autowired
    private EventPublisher publisher;

    //    @PostConstruct
    public void init() {
        this.publish();
    }

    public void publish() {
        SomeEvent event = new SomeEvent();
        event.setEventId(1);
        event.setContent("this is a new message!");
        publisher.publish(event, false);
    }
}
