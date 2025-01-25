---
title: Api endpoints
---
# Introduction

This document will walk you through the API endpoints defined in the <SwmToken path="/src/main/java/com/example/spring_boot/web/controller/MyUserController.java" pos="15:4:4" line-data="public class MyUserController {">`MyUserController`</SwmToken> class. The purpose is to provide a clear understanding of the logic behind each endpoint and the services involved. We will also generate Swagger documentation for each endpoint.

We will cover:

1. The index endpoint and its purpose.
2. The JWT token generation endpoints and their differences.
3. The user management endpoints for adding, retrieving, and deleting users.

# Index endpoint

<SwmSnippet path="/src/main/java/com/example/spring_boot/web/controller/MyUserController.java" line="28">

---

The index endpoint serves as a simple greeting message to verify that the application is running.

```
    @GetMapping("/")
    private String index() {
        return "Greetings from Spring Boot!";
    }
```

---

</SwmSnippet>

This endpoint does not involve any service logic and is primarily for testing connectivity.

# JWT token generation endpoints

## Version 1 JWT token

<SwmSnippet path="/src/main/java/com/example/spring_boot/web/controller/MyUserController.java" line="33">

---

The <SwmToken path="/src/main/java/com/example/spring_boot/web/controller/MyUserController.java" pos="33:5:8" line-data="    @PostMapping(&quot;/jwt/v1&quot;)">`/jwt/v1`</SwmToken> endpoint generates a JWT token using <SwmToken path="/src/main/java/com/example/spring_boot/web/controller/MyUserController.java" pos="4:10:10" line-data="import com.example.spring_boot.service.MyJwtTokenService;">`MyJwtTokenService`</SwmToken>. This service creates claims based on the <SwmToken path="/src/main/java/com/example/spring_boot/web/controller/MyUserController.java" pos="34:13:13" line-data="    private Mono&lt;String&gt; getJwtToken(@RequestBody JwtTokenRequest jwtTokenRequest) {">`JwtTokenRequest`</SwmToken> and logs them before generating the token.

```
    @PostMapping("/jwt/v1")
    private Mono<String> getJwtToken(@RequestBody JwtTokenRequest jwtTokenRequest) {
        return myJwtTokenService.generateToken(jwtTokenRequest);
    }
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/com/example/spring_boot/service/MyJwtTokenService.java" line="29">

---

The service logic is as follows:

```
    public Mono<String> generateToken(JwtTokenRequest jwtTokenRequest) {

        // Create claims from JwtTokenRequest
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", jwtTokenRequest.getUsername());
        claims.put("role", jwtTokenRequest.getRole());
        claims.put("version", "1");
        claims.put("audience", "MyUser");
        claims.put("token_type", "Bearer");

        logToken(claims);

        // Generate JWT Token
        return Mono.just(Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION))
                .setHeaderParam("alg", "none")
                .compact());
    }

}
```

---

</SwmSnippet>

## Version 2 JWT token

<SwmSnippet path="/src/main/java/com/example/spring_boot/web/controller/MyUserController.java" line="38">

---

The <SwmToken path="/src/main/java/com/example/spring_boot/web/controller/MyUserController.java" pos="38:5:8" line-data="    @PostMapping(&quot;/jwt/v2&quot;)">`/jwt/v2`</SwmToken> endpoint generates a JWT token using <SwmToken path="/src/main/java/com/example/spring_boot/web/controller/MyUserController.java" pos="5:10:10" line-data="import com.example.spring_boot.service.MyJwtTokenServiceV2;">`MyJwtTokenServiceV2`</SwmToken>. It follows a similar process to version 1 but uses a different expiration time and version number in the claims.

```
    @PostMapping("/jwt/v2")
    private Mono<String> getJwtTokenV2(@RequestBody JwtTokenRequest jwtTokenRequest) {
        return myJwtTokenServiceV2.generateTokenV2(jwtTokenRequest);
    }
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/com/example/spring_boot/service/MyJwtTokenServiceV2.java" line="29">

---

The service logic is as follows:

```
    public Mono<String> generateTokenV2(JwtTokenRequest jwtTokenRequest) {

        // Create claims from JwtTokenRequest
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", jwtTokenRequest.getUsername());
        claims.put("role", jwtTokenRequest.getRole());
        claims.put("version", "2");
        claims.put("audience", "MyUser");
        claims.put("token_type", "Bearer");

        logToken(claims);

        // Generate JWT Token
        return Mono.just(Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION))
                .setHeaderParam("alg", "none")
                .compact());
    }

}
```

---

</SwmSnippet>

# User management endpoints

## Add user

<SwmSnippet path="/src/main/java/com/example/spring_boot/web/controller/MyUserController.java" line="43">

---

The <SwmToken path="/src/main/java/com/example/spring_boot/web/controller/MyUserController.java" pos="43:5:6" line-data="    @PostMapping(&quot;/users&quot;)">`/users`</SwmToken> endpoint allows adding a new user. It uses <SwmToken path="/src/main/java/com/example/spring_boot/web/controller/MyUserController.java" pos="6:10:10" line-data="import com.example.spring_boot.service.MyUserService;">`MyUserService`</SwmToken> to save the user details in the database.

```
    @PostMapping("/users")
    public Mono<MyUser> addUser(@RequestBody MyUserRequest myUserRequest) {
        return this.myUserService.addUser(myUserRequest);
    }
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/com/example/spring_boot/service/MyUserService.java" line="31">

---

The service logic is as follows:

```
    public Mono<MyUser> addUser(MyUserRequest myUserRequest) {
        return myUserRepository.save(new MyUser(myUserRequest.getEmail(), myUserRequest.getName()));
    }
```

---

</SwmSnippet>

## Get users

<SwmSnippet path="/src/main/java/com/example/spring_boot/web/controller/MyUserController.java" line="48">

---

The <SwmToken path="/src/main/java/com/example/spring_boot/web/controller/MyUserController.java" pos="48:5:6" line-data="    @GetMapping(&quot;/users&quot;)">`/users`</SwmToken> endpoint retrieves all users. It uses <SwmToken path="/src/main/java/com/example/spring_boot/web/controller/MyUserController.java" pos="6:10:10" line-data="import com.example.spring_boot.service.MyUserService;">`MyUserService`</SwmToken> to fetch user data from the database and logs the API token used.

```
    @GetMapping("/users")
    public Flux<MyUser> getUsers() {
        return myUserService.getUsers();
    }
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/com/example/spring_boot/service/MyUserService.java" line="26">

---

The service logic is as follows:

```
    public Flux<MyUser> getUsers() {
        myUserClient.getToken();
        return myUserRepository.findAll();
    }
```

---

</SwmSnippet>

## Delete user

<SwmSnippet path="/src/main/java/com/example/spring_boot/web/controller/MyUserController.java" line="53">

---

The <SwmToken path="/src/main/java/com/example/spring_boot/web/controller/MyUserController.java" pos="53:5:6" line-data="    @DeleteMapping(&quot;/users&quot;)">`/users`</SwmToken> endpoint deletes a user by email. It uses <SwmToken path="/src/main/java/com/example/spring_boot/web/controller/MyUserController.java" pos="6:10:10" line-data="import com.example.spring_boot.service.MyUserService;">`MyUserService`</SwmToken> to remove the user from the database.

```
    @DeleteMapping("/users")
    public Mono<Void> deleteUser(@RequestParam String email) {
        return this.myUserService.deleteUser(email);
    }
}
```

---

</SwmSnippet>

<SwmSnippet path="/src/main/java/com/example/spring_boot/service/MyUserService.java" line="35">

---

The service logic is as follows:

```
    @Transactional
    public Mono<Void> deleteUser(String eemail) {
        return myUserRepository.deleteByEmail(eemail);
    }
}
```

---

</SwmSnippet>

# Conclusion

This document has outlined the API endpoints in <SwmToken path="/src/main/java/com/example/spring_boot/web/controller/MyUserController.java" pos="15:4:4" line-data="public class MyUserController {">`MyUserController`</SwmToken>, detailing the logic within the services and clients involved. Each endpoint serves a specific purpose, from greeting users to managing JWT tokens and user data.

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBc3ByaW5nYm9vdC1kZW1vJTNBJTNBbG9yZW56b2RlbmlzaTk3" repo-name="springboot-demo"><sup>Powered by [Swimm](https://app.swimm.io/)</sup></SwmMeta>
