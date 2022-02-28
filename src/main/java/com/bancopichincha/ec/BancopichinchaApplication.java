package com.bancopichincha.ec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;



@SpringBootApplication
@ComponentScan(basePackages = "com.bancopichincha.ec.*")
@EnableJpaRepositories(basePackages = "com.bancopichincha.ec.*")
@EntityScan("com.bancopichincha.ec.entities")
public class BancopichinchaApplication {

	public static void main(String[] args) {
		SpringApplication.run(BancopichinchaApplication.class, args);
	}

}
