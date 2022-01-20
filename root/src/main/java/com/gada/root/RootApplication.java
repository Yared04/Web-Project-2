package com.gada.root;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class RootApplication {

	public static void main(String[] args) {
		SpringApplication.run(RootApplication.class, args);
	}

// 	@Bean
// 	public CommandLineRunner dataLoader(UserRepository repo) {
// 		return args -> {
// 			repo.save();
// }
	}

