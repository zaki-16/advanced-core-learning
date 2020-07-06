package com.zaki.excutor.task;

import javax.annotation.PostConstruct;
import java.util.concurrent.CountDownLatch;

/**
 * @author wangzhen
 * @date 2019-12-16
 */
public class Task2 {

    @PostConstruct
    public void init() {
        doTask();
    }

    public void doTask() {
        CountDownLatch latch = new CountDownLatch(3);
        Thread thread = new Thread(() -> System.out.println("Task2#doTask run begin."));
        thread.start();
    }
}
