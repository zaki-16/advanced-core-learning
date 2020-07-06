package com.zaki.excutor;

import com.zaki.excutor.task.Task2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author wangzhen
 * @date 2019-12-05
 */
@SpringBootApplication
@EnableAsync
public class App {

    public static void main(String[] args) {
        Task2 task2 = new Task2();
        task2.doTask();
        SpringApplication.run(App.class, args);
    }
}
