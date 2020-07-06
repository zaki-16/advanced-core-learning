package com.zaki.redisson.controller;

import com.zaki.redisson.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangzhen
 * @date 2019-11-26
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService11;

    @GetMapping("/ok")
    public String test() {
        testService11.test();
        return "ok";
    }

    @GetMapping("/hello")
    public String hello() {
        return "hello world!";
    }
}
