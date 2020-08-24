package com.learning.core;

import java.util.concurrent.Exchanger;

/**
 * @author wangzhen
 * @date 2020-08-18
 */
public class ExchangerDemo {
    public static void main(String[] args) throws InterruptedException {
        Exchanger<String> exchanger = new Exchanger<>();
        new Thread(() -> {
            try {
                System.out.println("这是线程A，得到了另一个线程的数据："
                        + exchanger.exchange("这是来自线程A的数据"));

                System.out.println(exchanger.exchange("2222222"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        System.out.println("这个时候线程A是阻塞的，在等待线程B的数据");
        Thread.sleep(3000);

        new Thread(() -> {
            try {
                System.out.println("这是线程B，得到了另一个线程的数据："
                        + exchanger.exchange("这是来自线程B的数据"));
                System.out.println(exchanger.exchange("111111111"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
