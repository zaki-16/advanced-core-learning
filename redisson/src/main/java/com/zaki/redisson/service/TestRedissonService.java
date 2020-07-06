package com.zaki.redisson.service;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;

import java.util.concurrent.TimeUnit;

/**
 * @author wangzhen
 * @date 2019-11-26
 */
public class TestRedissonService {

    @Autowired
    private RedissonClient redisson;


    public void execute() throws InterruptedException {
        RLock lock = redisson.getLock("TestService.execute");
        boolean tryLock = lock.tryLock(2, TimeUnit.MINUTES);
        if (tryLock) {

            try {
                // spring的计时器
                StopWatch watcher = new StopWatch();
                watcher.start();
                // do sth
                watcher.stop();
                System.out.println(watcher.prettyPrint());
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } finally {
                if (lock.isHeldByCurrentThread()) {
                    lock.unlock();
                }
            }
        }

    }
}
