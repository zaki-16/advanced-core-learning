package com.learning.core;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * @author wangzhen
 * @date 2020-08-18
 */
public class FibonacciTest {

    static class FibonacciTask extends RecursiveTask<Integer> {

        int n;

        public FibonacciTask(int n) {
            this.n = n;
        }

        // 主要的实现逻辑都在compute()里
        @Override
        protected Integer compute() {
            // 这里先假设 n >= 0
            if (n <= 1) {
                return n;
            } else {
                // f(n-1)
                FibonacciTask f1 = new FibonacciTask(n - 1);
                f1.fork();
                // f(n-2)
                FibonacciTask f2 = new FibonacciTask(n - 2);
                f2.fork();
                // f(n) = f(n-1) + f(n-2)
                return f1.join() + f2.join();
            }
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        System.out.println("CPU核数：" + Runtime.getRuntime().availableProcessors());
        long start = System.currentTimeMillis();
        FibonacciTask fibonacciTask = new FibonacciTask(40);
        Future<Integer> future = forkJoinPool.submit(fibonacciTask);
        System.out.println(future.get());
        long end = System.currentTimeMillis();
        System.out.println(String.format("耗时：%d millis", end - start));
    }
}
