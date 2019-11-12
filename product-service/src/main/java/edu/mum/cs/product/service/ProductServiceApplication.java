package edu.mum.cs.product.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * The type Product service application.
 */
@SpringBootApplication
public class ProductServiceApplication {

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
        SpringApplication.run(ProductServiceApplication.class, args);
    }

}
