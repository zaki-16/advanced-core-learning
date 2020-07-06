package com.zaki.rocket.guava.publish;

import com.google.common.eventbus.Subscribe;
import org.springframework.stereotype.Component;

/**
 * @author wangzhen
 * @date 2019-11-26
 */
@Component
public class EventSubscriber extends BaseListener {


    @Subscribe
    public void subscribe(SomeEvent event) {
        System.out.println("listen before");
        System.out.println(event.getEventId());
        System.out.println(event.getContent());
    }
}
