package com.duod.demoboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashMap;

@SpringBootApplication
@MapperScan(value = "com.duod.demoboot.dao")
public class DemobootApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemobootApplication.class, args);
        new HashMap<>();
    }

}
