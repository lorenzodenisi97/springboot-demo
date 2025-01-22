package com.example.spring_boot.service;

import com.example.spring_boot.client.MyUserClient;
import com.example.spring_boot.domain.model.MyUser;
import com.example.spring_boot.domain.repository.MyUserMongoDBRepository;
import com.example.spring_boot.web.dto.MyUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class MyUserService {

    private final MyUserMongoDBRepository myUserRepository;
    private final MyUserClient myUserClient;

    @Autowired
    public MyUserService(MyUserMongoDBRepository myUserRepository, MyUserClient myUserClient) {
        this.myUserRepository = myUserRepository;
        this.myUserClient = myUserClient;
    }

    public Flux<MyUser> getUsers() {
        myUserClient.getToken();
        return myUserRepository.findAll();
    }

    public Mono<MyUser> addUser(MyUserRequest myUserRequest) {
        return myUserRepository.save(new MyUser(myUserRequest.getEmail(), myUserRequest.getName()));
    }

    @Transactional
    public Mono<Void> deleteUser(String eemail) {
        return myUserRepository.deleteByEmail(eemail);
    }
}
