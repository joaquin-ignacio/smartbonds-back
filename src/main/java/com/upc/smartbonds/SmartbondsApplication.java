package com.upc.smartbonds;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@OpenAPIDefinition
@EnableJpaAuditing
@SpringBootApplication
public class SmartbondsApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartbondsApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
