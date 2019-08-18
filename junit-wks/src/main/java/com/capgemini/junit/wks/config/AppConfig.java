package com.capgemini.junit.wks.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.capgemini.junit.wks.service.OrderService;
import com.capgemini.junit.wks.service.impl.OrderServiceImpl;

@Configuration
@Import(MongoConfig.class)
public class AppConfig {

	@Bean
    public OrderService orderService() {
        return new OrderServiceImpl();
    }
}
