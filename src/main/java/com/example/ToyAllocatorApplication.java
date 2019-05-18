package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync(proxyTargetClass = true)
@EnableCaching
@SpringBootApplication
public class ToyAllocatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ToyAllocatorApplication.class, args);
    }
}
