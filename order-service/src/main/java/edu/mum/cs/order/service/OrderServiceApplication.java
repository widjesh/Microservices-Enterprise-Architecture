package edu.mum.cs.order.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * The type Order service application.
 * @author nduwayofabrice
 * @version 1.0
 */
@SpringBootApplication
public class OrderServiceApplication {

    /**
     * Rest template rest template.
     *
     * @param builder the builder
     *
     * @return the rest template
     */
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }

}
