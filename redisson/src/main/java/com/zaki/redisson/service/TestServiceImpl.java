package com.zaki.redisson.service;

import org.springframework.stereotype.Service;

/**
 * @author wangzhen
 * @date 2020-01-19
 */
@Service
public class TestServiceImpl implements TestService {
    @Override
    public void test() {
        System.out.println("test service in...>>>>>>>>>>>>>>>>>");
    }
}
