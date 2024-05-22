package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

// @ComponentScan(basePackages = {"com.example.demo.config", "com.example.demo.controller", })
@SpringBootApplication
public class DemoGradleApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoGradleApplication.class, args);
	}

}
