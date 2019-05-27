package com.gawda.rcrta.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Anna Gawda
 * 27/05/2019
 */
@Configuration
public class ExecutorsConfiguration {

    @Value("${executor.thread.pool.size}")
    private int threadsNumber;

    @Bean("fixedThreadPool")
    public ExecutorService fixedThreadPool() {
        return Executors.newFixedThreadPool(threadsNumber);
    }
}
