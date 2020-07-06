package com.zaki.excutor.controller;

import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author wangzhen
 * @date 2019-12-05
 */
@RestController
public class TesterController {


    @PostMapping("/test")
    public String test() throws InterruptedException {
        System.out.println("test before");
        testasync();
        System.out.println("test after");
        return "hello jmeter";
    }

    @Async
    public void testasync() {
        System.out.println("testasync before");

        System.out.println("testasync after");
    }


    public static void main(String[] args) {
        ConcurrentHashMap<Object, Object> objectObjectConcurrentHashMap = new ConcurrentHashMap<>();
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        list.add("s");
        list.addIfAbsent("s");
        ArrayList<String> strings = new ArrayList<>();
    }
}
