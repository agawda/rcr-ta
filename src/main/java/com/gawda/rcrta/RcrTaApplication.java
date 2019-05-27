package com.gawda.rcrta;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class RcrTaApplication {

    public static void main(String[] args) {
        SpringApplication.run(RcrTaApplication.class, args);
    }
}