package com.remainder.remainderApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RemainderAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(RemainderAppApplication.class, args);
	}

}
