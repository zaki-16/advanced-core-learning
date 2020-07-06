package com.zaki.excutor.threads;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author wangzhen
 * @date 2019-12-04
 */
public class Threader {

    private volatile Long volatileLong = 32L;
    private long long64 = 64L;

    public void tests() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 50, 10, TimeUnit.SECONDS, null);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("volatileLong == " + volatileLong);
                System.out.println("long64 == " + long64);
            }
        });
        thread.start();
    }

    public static void main(String[] args) {
        Thread.currentThread().interrupt();
    }


}
