package com.springwiththeo.week7.password_encoding_session_p2.config;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebSecurity
public class SecurityConfig implements CommandLineRunner {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthenticationSuccessHandler successHandler, AuthenticationFailureHandler failureHandler, SessionRegistry sessionRegistry) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable) // Disable CSRF for simplicity, not recommended for production
                .authorizeHttpRequests(
                        auth -> auth
                                .requestMatchers("/api/auth/register").permitAll()
                                //.requestMatchers("/api/hello").permitAll()
                                .anyRequest().authenticated()

                )
                .formLogin(form->{
                    form.successHandler(successHandler);
                    form.failureHandler(failureHandler);
                })
                .logout(logoutConfigurer -> logoutConfigurer

                        .logoutSuccessHandler((request, response, authentication) -> {
                            response.setStatus(HttpServletResponse.SC_OK);
                            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                            response.getWriter().write("{\"message\": \"Logout successful\"}");
                            Arrays.stream(request.getCookies()).forEach(s -> sessionRegistry.removeSessionInformation(s.getValue()));

                        })
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID"))
                .sessionManagement(session -> {
                    session.maximumSessions(1).maxSessionsPreventsLogin(true);
                    session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
                })
                .build();

    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }
    @Bean
    @Primary
    public PasswordEncoder delegatingPasswordEncoder() {
        String idForEncode = "argon2"; // use argon2 for new passwords

        Map<String, PasswordEncoder> encoders = new HashMap<>();
        encoders.put("argon2", Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8());
        encoders.put("bcrypt", new BCryptPasswordEncoder());
        encoders.put("noop", NoOpPasswordEncoder.getInstance());

        return new DelegatingPasswordEncoder(idForEncode, encoders);
    }


    PasswordEncoder bcryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    PasswordEncoder bcryptPasswordEncoder(int strength) {
        return new BCryptPasswordEncoder(strength);
    }


    @Bean(name = "argonPasswordEncoder")
    PasswordEncoder argonPasswordEncoder() {
        return Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails adminUser = User.withUsername("admin")
                .password(passwordEncoder.encode("admin123"))
                .roles("ADMIN").build();

        UserDetails oldUser = User.withUsername("old")
                .password(new BCryptPasswordEncoder().encode("old123"))
                .roles("USER").build();

        // In a real application, you would load users from a database or another source.
        return new InMemoryUserDetailsManager(adminUser, oldUser);
    }


    private BCryptPasswordEncoder tunedPasswordEncoder(int timeInMillis) {
        // This method is for demonstration purposes. In a real application, you would not use this.
        // Instead, you would configure the PasswordEncoder in your security configuration.
        String password = "password123"; // Example password to tune the encoder
        int strength = 4; // Example strength, you can adjust this
        while (passwordEncoderSpeed(new BCryptPasswordEncoder(strength), password) < timeInMillis) {
            strength++;
        }
        return new BCryptPasswordEncoder(strength);
    }

    private long passwordEncoderSpeed(PasswordEncoder passwordEncoderToTune, String password) {
        // This method is for demonstration purposes. In a real application, you would not use this.
        // Instead, you would configure the PasswordEncoder in your security configuration.
        long start = System.nanoTime();
        passwordEncoderToTune.encode(password);
        long end = System.nanoTime();
        long duration = (end - start) / 1_000_000;
        return duration;// Convert to milliseconds
    }

    @Override
    public void run(String... args) throws Exception {

        long start = System.nanoTime();
        String hashed = bcryptPasswordEncoder().encode("password123");
        long end = System.nanoTime();
        String duration = (end - start) / 1_000_000 + "ms"; // Convert to milliseconds


        //Spring Security recommends tuning until password hashing takes ~1 second on your production hardware.

        System.out.println("BCrypt Hash: " + hashed);
        System.out.println("BCrypt Duration: " + duration);


        BCryptPasswordEncoder tunedEncoder = tunedPasswordEncoder(1000);
        start = System.nanoTime();
        tunedEncoder.encode("password123");
        end = System.nanoTime();
        duration = (end - start) / 1_000_000 + "ms";
        System.out.println("BCrypt Hash: " + hashed);
        System.out.println("BCrypt Duration: " + duration);

    }
}
