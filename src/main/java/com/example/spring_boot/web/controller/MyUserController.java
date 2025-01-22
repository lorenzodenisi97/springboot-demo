package com.example.spring_boot.web.controller;

import com.example.spring_boot.domain.model.MyUser;
import com.example.spring_boot.service.MyJwtTokenService;
import com.example.spring_boot.service.MyJwtTokenServiceV2;
import com.example.spring_boot.service.MyUserService;
import com.example.spring_boot.web.dto.JwtTokenRequest;
import com.example.spring_boot.web.dto.MyUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class MyUserController {

    private final MyUserService myUserService;
    private final MyJwtTokenService myJwtTokenService;
    private final MyJwtTokenServiceV2 myJwtTokenServiceV2;

    @Autowired
    public MyUserController(MyUserService myUserService, MyJwtTokenService myJwtTokenService, MyJwtTokenServiceV2 myJwtTokenServiceV2) {
        this.myUserService = myUserService;
        this.myJwtTokenService = myJwtTokenService;
        this.myJwtTokenServiceV2 = myJwtTokenServiceV2;
    }

    @GetMapping("/")
    private String index() {
        return "Greetings from Spring Boot!";
    }

    @PostMapping("/jwt/v1")
    private Mono<String> getJwtToken(@RequestBody JwtTokenRequest jwtTokenRequest) {
        return myJwtTokenService.generateToken(jwtTokenRequest);
    }

    @PostMapping("/jwt/v2")
    private Mono<String> getJwtTokenV2(@RequestBody JwtTokenRequest jwtTokenRequest) {
        return myJwtTokenServiceV2.generateTokenV2(jwtTokenRequest);
    }

    @PostMapping("/users")
    public Mono<MyUser> addUser(@RequestBody MyUserRequest myUserRequest) {
        return this.myUserService.addUser(myUserRequest);
    }

    @GetMapping("/users")
    public Flux<MyUser> getUsers() {
        return myUserService.getUsers();
    }

    @DeleteMapping("/users")
    public Mono<Void> deleteUser(@RequestParam String email) {
        return this.myUserService.deleteUser(email);
    }
}