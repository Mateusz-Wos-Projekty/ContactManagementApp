package com.example.CMA;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
@EnableWebMvc
@SpringBootApplication
@EnableJpaRepositories("src/main/java/com/example/CMA/api/repository")
public class ContactManagementApplication {
	public static void main(String[] args) {
		SpringApplication.run(ContactManagementApplication.class, args);
	}
}