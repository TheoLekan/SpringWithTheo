package com.springwiththeo.week12.method_security;


import com.springwiththeo.week12.method_security.repository.User;
import com.springwiththeo.week12.method_security.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Set;

@SpringBootApplication
@EnableScheduling
@EnableMethodSecurity
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

				List<User> users = repository.saveAll(List.of(admin, user));
				users.forEach(System.out::println);

			}
		};
	}
}
