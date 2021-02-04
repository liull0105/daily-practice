package com.demo.pra.web.springbean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public UserEntity myBean() {
        return new UserEntity();
    }



}
