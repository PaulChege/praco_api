package io.chege.praco_api.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner userSetup(UserRepository userRepository) {
        return args -> {
            if (userRepository.getUserByEmail("janedoe@gmail.com").isEmpty()) {
                userRepository.save(new User("Jane", "Doe", "janedoe@gmail.com", "password", true));
            }
        };
    }
}
