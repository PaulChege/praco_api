package io.chege.praco_api.instrument;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InstrumentConfig {

    @Bean
    CommandLineRunner instrumentSetup(InstrumentRepository instrumentRepository){

        return args -> {
            if (!instrumentRepository.findByName("Violin").isPresent()) {
                instrumentRepository.save(new Instrument("Violin", true));
            }
        };
    }
}
