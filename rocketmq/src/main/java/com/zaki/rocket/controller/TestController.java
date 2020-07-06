package com.zaki.rocket.controller;

import com.zaki.rocket.guava.publish.EventPublisher;
import com.zaki.rocket.guava.publish.SomeEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangzhen
 * @date 2019-11-26
 */
@RestController
public class TestController {

    @Autowired
    EventPublisher publisher;

    @PostMapping("/testa")
    public String test() {
        SomeEvent event = new SomeEvent();
        event.setContent("测试异步");
        event.setEventId(111);
        publisher.publish(event, true);
        System.out.println("hello async");
        return "hello async";
    }

}
