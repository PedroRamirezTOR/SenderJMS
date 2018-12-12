package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@EnableJms
@SpringBootApplication
public class SenderApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(SenderApplication.class, args);
	}

}