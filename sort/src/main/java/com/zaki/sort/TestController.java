package com.zaki.sort;

import com.zaki.sort.service.TestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangzhen
 * @date 2020-01-19
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestServiceImpl testService;

    @GetMapping("/ok")
    public String test() {
        testService.test();
        return "ok";
    }
}
