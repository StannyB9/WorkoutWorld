package com.workoutapp.config;

import com.workoutapp.entity.User;
import com.workoutapp.enums.Role;
import com.workoutapp.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initData(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (!userRepository.existsByEmail("admin@test.com")) {
                User admin = User.builder()
                        .name("admin")
                        .email("admin@test.com")
                        .password(passwordEncoder.encode("admin123"))
                        .role(Role.ROLE_ADMIN)
                        .rating(0f)
                        .build();
                userRepository.save(admin);
                System.out.println("Admin user created successfully");
            } else {
                System.out.println("Admin user already exist");
            }
        };
    }
}
