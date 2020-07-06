package com.zaki.excutor.task;

import javax.annotation.PostConstruct;

/**
 * @author wangzhen
 * @date 2019-12-16
 */
public class Task1 {

    @PostConstruct
    public void init() {
        doTask();
    }

    public void doTask() {
        Thread thread = new Thread(() -> System.out.println("Task1#doTask run begin."));
        thread.start();
    }
}
