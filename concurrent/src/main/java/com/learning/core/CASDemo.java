package com.learning.core;

import java.util.HashMap;
import java.util.Hashtable;

/**
 * @author wangzhen
 * @date 2020-08-20
 */
public class CASDemo {

    static int c = 0;

    private static void casInc(int old) {
        for (int i = 0; i < 10; i++) {
            if (c == old) {
                c++;
            } else {

                System.out.println("s");
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                casInc(c);
            }).start();
        }
        System.out.println(c);
        new HashMap<>().put(null, null);
        new Hashtable<>().put(null, "s");
    }

}
