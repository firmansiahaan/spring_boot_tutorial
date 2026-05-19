package com.tutorialspoint.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class Chapter01Application {

	public static void main(String[] args) {
		SpringApplication.run(Chapter01Application.class, args);
	}

}

@RestController
class HelloController {
	
	@GetMapping("/")
	public String hello() {
		return "<h1>Hello, Spring Boot!</h1>";
	}
}