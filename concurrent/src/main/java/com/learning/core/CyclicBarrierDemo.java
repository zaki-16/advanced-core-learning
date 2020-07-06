package com.learning.core;

import java.util.concurrent.CyclicBarrier;

/**
 * @author wangzhen
 * @date 2020-07-06
 */
public class CyclicBarrierDemo {
    public static void test() throws Exception {
        // 栅栏与闭锁的区别 ，闭锁等待（所有的线程执行完）指定事件后放行，
        // 而栅栏是等待（阻塞）指定数量的线程到达栅栏位置，才能继续执行
        CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
    }

    public static void main(String[] args) throws Exception {
        test();
    }
}
