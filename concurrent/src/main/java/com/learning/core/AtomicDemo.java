package com.learning.core;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author wangzhen
 * @date 2020-07-08
 */
public class AtomicDemo {
    //    public static AtomicInteger race = new AtomicInteger();
    public static volatile int race = 0;
    static ReentrantLock a = new ReentrantLock();
    static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    static volatile boolean flag = false;

    public static void incr() {
        race++;
    }

    private static final int THREADS_COUNT = 20;

    public static void main(String[] args) {
        Thread[] threads = new Thread[THREADS_COUNT];
        for (int i = 0; i < THREADS_COUNT; i++) {
            threads[i] = new Thread(new Runnable() {
                public void run() {
                    for (int i = 0; i < 10000; i++) {
                        incr();
                        if (i % 100 == 0) {
                            System.out.println(race);
                        }
                    }
                }
            });
            threads[i].start();
        }
        while (Thread.activeCount() > 1) {
            Thread.yield();
        }
        System.out.println(">>> " + race);
    }
}
