package com.learning.core;

import java.util.concurrent.CountDownLatch;

/**
 * @author wangzhen
 * @date 2020-07-06
 */
public class CountDownLatchDemo {
    public static void test() throws Exception {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("running thread>" + Thread.currentThread().getName());

                }
            });
            // 闭锁是一次性对象，一旦终止，就不能被重置
            thread.start();
            countDownLatch.countDown();
        }

        countDownLatch.await();
    }

    public static void main(String[] args) throws Exception {
        test();
    }
}
