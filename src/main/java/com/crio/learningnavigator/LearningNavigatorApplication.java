package com.crio.learningnavigator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;

@SpringBootApplication
public class LearningNavigatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearningNavigatorApplication.class, args);
    }

    @Bean
    RestClient restClient() {
        return RestClient.create();
    }

}
