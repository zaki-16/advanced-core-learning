package com.learning.core;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author wangzhen
 * @date 2020-07-09
 */
public class JavaVMStackSOF {
    private int stackLength = 1;

    public void stackLeak() {
        stackLength++;
        stackLeak();
    }

    public static void main(String[] args) throws Throwable {

        int i = 0;
        Field field = Unsafe.class.getDeclaredFields()[0];
        field.setAccessible(true);
        Unsafe unsafe = (Unsafe) field.get(null);
        while (true) {
            i++;
            unsafe.allocateMemory(1024 * 1024);
            System.out.println(i);
        }
    }
}
