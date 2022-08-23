package com.challenge.supportaggregator.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.TimeZone;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.challenge.supportaggregator.entitymodel"})
@EntityScan(basePackages = {"com.challenge.supportaggregator.entitymodel"})
@ComponentScan(basePackages = "com.challenge.supportaggregator.crmaggregator,com.challenge.supportaggregator.controller")
public class SupportAggregatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(SupportAggregatorApplication.class, args);
	}

	@Bean
	public Jackson2ObjectMapperBuilderCustomizer jacksonObjectMapperCustomization() {
		return jacksonObjectMapperBuilder ->
				jacksonObjectMapperBuilder.timeZone(TimeZone.getDefault());
	}

}
