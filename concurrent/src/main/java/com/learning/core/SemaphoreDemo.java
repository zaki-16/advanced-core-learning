package com.learning.core;

import java.util.concurrent.Semaphore;

/**
 * @author wangzhen
 * @date 2020-07-06
 */
public class SemaphoreDemo {
    public static void test() throws Exception {
        // 通过 Semaphore 可将任何一种容器变为有界阻塞容器
        Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < 3; i++) {
            Thread thread = new Thread(new Runnable() {
                public void run() {
                    System.out.println("running thread>" + Thread.currentThread().getName());
                }
            });
            System.out.println(semaphore.availablePermits());// 3
            // tryAcquire()时就会消耗一个permit
            semaphore.tryAcquire();// 2
            if (semaphore.tryAcquire()) {//1
                System.out.println(semaphore.availablePermits());
                // semaphore管理着一组虚拟的许可，若没有许可，则acquire()将阻塞直到获得许可
                semaphore.acquire();//0
                System.out.println(semaphore.availablePermits());//0
//                System.out.println(semaphore.drainPermits());//0
//                System.out.println(semaphore.isFair());
//                System.out.println(semaphore.getQueueLength());
//                System.out.println(semaphore.hasQueuedThreads());
                thread.start();
            }
        }
        System.out.println("before");
        // release()返回一个许可给信号量
        semaphore.release();
        System.out.println(semaphore.availablePermits());
        System.out.println("after");
    }

    /**
     * 与 CountDownLatch不同,cdl是线程run后先计数器-1（释放），再在latch上等待
     * Semaphore 是在线程run之前先阻塞获取许可，获取到了之后才释放
     */
    public static void main(String[] args) throws Exception {
        test();
    }
}
