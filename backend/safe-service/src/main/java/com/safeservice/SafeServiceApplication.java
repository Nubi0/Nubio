package com.safeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SafeServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SafeServiceApplication.class, args);
	}

}
