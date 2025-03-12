package com.br.bood.boodApi.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan(basePackages = "com.br.bood.boodApi")
@EntityScan(basePackages = "com.br.bood.boodApi.domain")
@EnableJpaRepositories(basePackages = "com.br.bood.boodApi.domain")
public class BoodApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(BoodApiApplication.class, args);
	}
}
