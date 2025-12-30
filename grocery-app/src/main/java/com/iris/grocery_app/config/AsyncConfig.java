package com.iris.grocery_app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@Configuration
public class AsyncConfig
{
    public static final int CHECKOUT_POOL_SIZE = 4;

    @Bean
    public Executor checkoutExecutor()
    {
        return Executors.newFixedThreadPool(CHECKOUT_POOL_SIZE);
    }
}
