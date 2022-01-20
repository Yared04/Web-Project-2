package com.gada.root;

<<<<<<< HEAD
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
=======
// import java.time.LocalDateTime;

// import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.context.annotation.Bean;
>>>>>>> 4630ea08f13bd7d904524b688a683648380bcb97

@SpringBootApplication
public class RootApplication {

	public static void main(String[] args) {
		SpringApplication.run(RootApplication.class, args);
	}
	// @Bean
	// public CommandLineRunner dataRunner(PostsRepositary repositary){
	// 	LocalDateTime ldt = LocalDateTime.now();
	// 	return args -> {
	// 		repositary.save(new Posts(Long.valueOf(1),"The GERD","Fund for the Great Ethiopian Renesence dam",ldt));
	// 	};
	// }

// 	@Bean
// 	public CommandLineRunner dataLoader(UserRepository repo) {
// 		return args -> {
// 			repo.save();
// }
	}

