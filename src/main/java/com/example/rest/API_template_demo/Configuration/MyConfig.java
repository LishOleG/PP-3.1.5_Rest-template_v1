package com.example.rest.API_template_demo.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan("com.example.rest.API_template_demo")
public class MyConfig {

    String sessionId;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();

    }


}
