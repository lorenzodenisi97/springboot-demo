package com.example.spring_boot.config;

import com.example.spring_boot.domain.model.MyUser;
import com.example.spring_boot.domain.repository.MyUserMongoDBRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;

@Configuration
public class MyUserConfig {

    @Bean
    CommandLineRunner initUsers(MyUserMongoDBRepository repository) {
        return args -> {
            repository
                    .findAll()
                    .flatMap(user -> {
                        System.out.println(user);
                        return Flux.just(user);
                    }).subscribe();
        };
    }
}
