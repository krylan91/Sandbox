package me.aakrylov.sandbox.config;

import me.aakrylov.sandbox.handlers.RestTemplateErrorHandler;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder, RestTemplateErrorHandler errorHandler) {
        return restTemplateBuilder
                .errorHandler(errorHandler)
                .uriTemplateHandler(new DefaultUriBuilderFactory("http://localhost:8080"))
                .build();
    }
}
