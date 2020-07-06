package com.learning.core;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @author wangzhen
 * @date 2020-07-06
 */
public class FutureTaskDemo {
    public static void test() throws Exception {
        FutureTask futureTask = new FutureTask(new Callable<String>() {
            public String call() throws Exception {
                return "test future";
            }
        });
        new Thread(futureTask).start();
        // futureTask.get();会阻塞直到任务完成
        // futureTask.fun() while (state == INTERRUPTING) > Thread.yield();谦让掉当前cpu的时间片
        System.out.println(futureTask.get());
    }

    public static void main(String[] args) throws Exception {
        test();
    }
}
