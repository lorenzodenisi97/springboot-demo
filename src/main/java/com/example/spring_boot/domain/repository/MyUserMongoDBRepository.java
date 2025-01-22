package com.example.spring_boot.domain.repository;

import com.example.spring_boot.domain.model.MyUser;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MyUserMongoDBRepository extends ReactiveMongoRepository<MyUser, String> {
    Mono<MyUser> findByEmail(String email);
    Mono<Void> deleteByEmail(String email);
}

