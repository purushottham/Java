package com.isreports;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestingBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestingBootApplication.class, args);
        System.out.println("Spring boot test");
    }
}
