package com.gada.root;

// import java.time.LocalDateTime;

// import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import org.springframework.context.annotation.Bean;

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

}
