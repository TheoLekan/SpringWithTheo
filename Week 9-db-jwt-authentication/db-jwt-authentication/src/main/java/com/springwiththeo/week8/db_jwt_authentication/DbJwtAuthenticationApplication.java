package com.springwiththeo.week8.db_jwt_authentication;

import com.springwiththeo.week8.db_jwt_authentication.repository.User;
import com.springwiththeo.week8.db_jwt_authentication.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Set;

@SpringBootApplication
@EnableScheduling
public class DbJwtAuthenticationApplication {

	public static void main(String[] args) {
		SpringApplication.run(DbJwtAuthenticationApplication.class, args);
	}

	@Bean
	CommandLineRunner init(UserRepository repository, PasswordEncoder encoder) {
		return args -> {
			if (repository.count() == 0) {
				User admin = new User();
				admin.setUsername("admin");
				admin.setPassword(encoder.encode("admin123"));
				admin.setRoles(Set.of("ROLE_ADMIN"));

				User user = new User();
				user.setUsername("user");
				user.setPassword(encoder.encode("user123"));
				user.setRoles(Set.of("ROLE_USER"));

				repository.saveAll(List.of(admin, user));
			}
		};
	}
}
