package com.example.hanebaapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@SpringBootApplication
@EnableJpaAuditing
public class HanebaApiApplication {
	public static void main(String[] args) {
		//System.out.println("Test");
		SpringApplication.run(HanebaApiApplication.class, args);
	}
}
