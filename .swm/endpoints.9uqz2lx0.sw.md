---
title: Endpoints
---
# Introduction

This document will walk you through the implementation of various endpoints in the Spring Boot application. The purpose is to understand the input, output, and logic behind each endpoint, including the services and clients involved.

We will cover:

1. How the application is initialized.
2. The role of the <SwmToken path="/src/main/java/com/example/spring_boot/web/controller/MyUserController.java" pos="15:4:4" line-data="public class MyUserController {">`MyUserController`</SwmToken> and its endpoints.
3. The logic behind JWT token generation.
4. The interaction with the user repository and client.

# Application initialization

<SwmSnippet path="/src/main/java/com/example/spring_boot/Application.java" line="9">

---

The application is initialized using the `SpringApplication.run()` method. This is the entry point of the Spring Boot application.

```
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        System.out.println("hello");
    }
```

---

</SwmSnippet>

# <SwmToken path="/src/main/java/com/example/spring_boot/web/controller/MyUserController.java" pos="15:4:4" line-data="public class MyUserController {">`MyUserController`</SwmToken> endpoints

The <SwmToken path="/src/main/java/com/example/spring_boot/web/controller/MyUserController.java" pos="15:4:4" line-data="public class MyUserController {">`MyUserController`</SwmToken> class defines several endpoints for user management and JWT token generation.

## Index endpoint

<SwmSnippet path="/src/main/java/com/example/spring_boot/web/controller/MyUserController.java" line="28">

---

The index endpoint is a simple GET request that returns a greeting message. It serves as a basic health check for the application.

```
    @GetMapping("/")
    private String index() {
        return "Greetings from Spring Boot!";
    }
```

---

</SwmSnippet>

## JWT token generation (<SwmToken path="/src/main/java/com/example/spring_boot/web/controller/MyUserController.java" pos="33:8:8" line-data="    @PostMapping(&quot;/jwt/v1&quot;)">`v1`</SwmToken>)

This endpoint accepts a <SwmToken path="/src/main/java/com/example/spring_boot/web/controller/MyUserController.java" pos="34:13:13" line-data="    private Mono&lt;String&gt; getJwtToken(@RequestBody JwtTokenRequest jwtTokenRequest) {">`JwtTokenRequest`</SwmToken> and returns a JWT token. It uses <SwmToken path="/src/main/java/com/example/spring_boot/web/controller/MyUserController.java" pos="4:10:10" line-data="import com.example.spring_boot.service.MyJwtTokenService;">`MyJwtTokenService`</SwmToken> to generate the token.

<SwmSnippet path="/src/main/java/com/example/spring_boot/web/controller/MyUserController.java" line="33">

---

Input: <SwmToken path="/src/main/java/com/example/spring_boot/web/controller/MyUserController.java" pos="34:13:13" line-data="    private Mono&lt;String&gt; getJwtToken(@RequestBody JwtTokenRequest jwtTokenRequest) {">`JwtTokenRequest`</SwmToken> containing <SwmToken path="/src/main/java/com/example/spring_boot/service/MyJwtTokenService.java" pos="33:6:6" line-data="        claims.put(&quot;username&quot;, jwtTokenRequest.getUsername());">`username`</SwmToken> and <SwmToken path="/src/main/java/com/example/spring_boot/service/MyJwtTokenService.java" pos="34:6:6" line-data="        claims.put(&quot;role&quot;, jwtTokenRequest.getRole());">`role`</SwmToken>. Output: A JWT token as a <SwmToken path="/src/main/java/com/example/spring_boot/web/controller/MyUserController.java" pos="34:3:6" line-data="    private Mono&lt;String&gt; getJwtToken(@RequestBody JwtTokenRequest jwtTokenRequest) {">`Mono<String>`</SwmToken>.

```
    @PostMapping("/jwt/v1")
    private Mono<String> getJwtToken(@RequestBody JwtTokenRequest jwtTokenRequest) {
        return myJwtTokenService.generateToken(jwtTokenRequest);
    }
```

---

</SwmSnippet>

## JWT token generation (<SwmToken path="/src/main/java/com/example/spring_boot/web/controller/MyUserController.java" pos="38:8:8" line-data="    @PostMapping(&quot;/jwt/v2&quot;)">`v2`</SwmToken>)

Similar to the <SwmToken path="/src/main/java/com/example/spring_boot/web/controller/MyUserController.java" pos="33:8:8" line-data="    @PostMapping(&quot;/jwt/v1&quot;)">`v1`</SwmToken> endpoint, this endpoint uses <SwmToken path="/src/main/java/com/example/spring_boot/web/controller/MyUserController.java" pos="5:10:10" line-data="import com.example.spring_boot.service.MyJwtTokenServiceV2;">`MyJwtTokenServiceV2`</SwmToken> to generate a version 2 JWT token.

<SwmSnippet path="/src/main/java/com/example/spring_boot/web/controller/MyUserController.java" line="38">

---

Input: <SwmToken path="/src/main/java/com/example/spring_boot/web/controller/MyUserController.java" pos="39:13:13" line-data="    private Mono&lt;String&gt; getJwtTokenV2(@RequestBody JwtTokenRequest jwtTokenRequest) {">`JwtTokenRequest`</SwmToken> containing <SwmToken path="/src/main/java/com/example/spring_boot/service/MyJwtTokenService.java" pos="33:6:6" line-data="        claims.put(&quot;username&quot;, jwtTokenRequest.getUsername());">`username`</SwmToken> and <SwmToken path="/src/main/java/com/example/spring_boot/service/MyJwtTokenService.java" pos="34:6:6" line-data="        claims.put(&quot;role&quot;, jwtTokenRequest.getRole());">`role`</SwmToken>. Output: A JWT token as a <SwmToken path="/src/main/java/com/example/spring_boot/web/controller/MyUserController.java" pos="39:3:6" line-data="    private Mono&lt;String&gt; getJwtTokenV2(@RequestBody JwtTokenRequest jwtTokenRequest) {">`Mono<String>`</SwmToken>.

```
    @PostMapping("/jwt/v2")
    private Mono<String> getJwtTokenV2(@RequestBody JwtTokenRequest jwtTokenRequest) {
        return myJwtTokenServiceV2.generateTokenV2(jwtTokenRequest);
    }
```

---

</SwmSnippet>

## Add user

This endpoint allows adding a new user to the system. It uses <SwmToken path="/src/main/java/com/example/spring_boot/web/controller/MyUserController.java" pos="6:10:10" line-data="import com.example.spring_boot.service.MyUserService;">`MyUserService`</SwmToken> to save the user details in the repository.

<SwmSnippet path="/src/main/java/com/example/spring_boot/web/controller/MyUserController.java" line="43">

---

Input: <SwmToken path="/src/main/java/com/example/spring_boot/web/controller/MyUserController.java" pos="44:13:13" line-data="    public Mono&lt;MyUser&gt; addUser(@RequestBody MyUserRequest myUserRequest) {">`MyUserRequest`</SwmToken> containing <SwmToken path="/src/main/java/com/example/spring_boot/web/controller/MyUserController.java" pos="54:15:15" line-data="    public Mono&lt;Void&gt; deleteUser(@RequestParam String email) {">`email`</SwmToken> and `name`. Output: The created <SwmToken path="/src/main/java/com/example/spring_boot/web/controller/MyUserController.java" pos="44:5:5" line-data="    public Mono&lt;MyUser&gt; addUser(@RequestBody MyUserRequest myUserRequest) {">`MyUser`</SwmToken> object as a <SwmToken path="/src/main/java/com/example/spring_boot/web/controller/MyUserController.java" pos="44:3:6" line-data="    public Mono&lt;MyUser&gt; addUser(@RequestBody MyUserRequest myUserRequest) {">`Mono<MyUser>`</SwmToken>.

```
    @PostMapping("/users")
    public Mono<MyUser> addUser(@RequestBody MyUserRequest myUserRequest) {
        return this.myUserService.addUser(myUserRequest);
    }
```

---

</SwmSnippet>

## Get users

This endpoint retrieves all users from the repository. It returns a reactive stream of users.

<SwmSnippet path="/src/main/java/com/example/spring_boot/web/controller/MyUserController.java" line="48">

---

Output: A <SwmToken path="/src/main/java/com/example/spring_boot/web/controller/MyUserController.java" pos="49:3:6" line-data="    public Flux&lt;MyUser&gt; getUsers() {">`Flux<MyUser>`</SwmToken> containing all users.

```
    @GetMapping("/users")
    public Flux<MyUser> getUsers() {
        return myUserService.getUsers();
    }
```

---

</SwmSnippet>

## Delete user

This endpoint deletes a user based on the provided email. It uses <SwmToken path="/src/main/java/com/example/spring_boot/web/controller/MyUserController.java" pos="6:10:10" line-data="import com.example.spring_boot.service.MyUserService;">`MyUserService`</SwmToken> to perform the deletion.

<SwmSnippet path="/src/main/java/com/example/spring_boot/web/controller/MyUserController.java" line="53">

---

Input: <SwmToken path="/src/main/java/com/example/spring_boot/web/controller/MyUserController.java" pos="54:15:15" line-data="    public Mono&lt;Void&gt; deleteUser(@RequestParam String email) {">`email`</SwmToken> as a query parameter. Output: A <SwmToken path="/src/main/java/com/example/spring_boot/web/controller/MyUserController.java" pos="54:3:6" line-data="    public Mono&lt;Void&gt; deleteUser(@RequestParam String email) {">`Mono<Void>`</SwmToken> indicating completion.

```
    @DeleteMapping("/users")
    public Mono<Void> deleteUser(@RequestParam String email) {
        return this.myUserService.deleteUser(email);
    }
}
```

---

</SwmSnippet>

# JWT token services

The JWT token services are responsible for generating tokens with specific claims.

## <SwmToken path="/src/main/java/com/example/spring_boot/web/controller/MyUserController.java" pos="4:10:10" line-data="import com.example.spring_boot.service.MyJwtTokenService;">`MyJwtTokenService`</SwmToken>

<SwmSnippet path="/src/main/java/com/example/spring_boot/service/MyJwtTokenService.java" line="29">

---

This service generates version 1 JWT tokens. It logs the token claims and returns the token as a <SwmToken path="/src/main/java/com/example/spring_boot/service/MyJwtTokenService.java" pos="29:3:6" line-data="    public Mono&lt;String&gt; generateToken(JwtTokenRequest jwtTokenRequest) {">`Mono<String>`</SwmToken>.

```
    public Mono<String> generateToken(JwtTokenRequest jwtTokenRequest) {

        // Create claims from JwtTokenRequest
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", jwtTokenRequest.getUsername());
        claims.put("role", jwtTokenRequest.getRole());
        claims.put("version", "1");
        claims.put("audience", "MyUser");
        claims.put("token_type", "Bearer");
```

---

</SwmSnippet>

## <SwmToken path="/src/main/java/com/example/spring_boot/web/controller/MyUserController.java" pos="5:10:10" line-data="import com.example.spring_boot.service.MyJwtTokenServiceV2;">`MyJwtTokenServiceV2`</SwmToken>

<SwmSnippet path="/src/main/java/com/example/spring_boot/service/MyJwtTokenServiceV2.java" line="29">

---

This service generates version 2 JWT tokens with a different expiration time. It follows a similar process as the <SwmToken path="/src/main/java/com/example/spring_boot/web/controller/MyUserController.java" pos="33:8:8" line-data="    @PostMapping(&quot;/jwt/v1&quot;)">`v1`</SwmToken> service.

```
    public Mono<String> generateTokenV2(JwtTokenRequest jwtTokenRequest) {

        // Create claims from JwtTokenRequest
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", jwtTokenRequest.getUsername());
        claims.put("role", jwtTokenRequest.getRole());
        claims.put("version", "2");
        claims.put("audience", "MyUser");
        claims.put("token_type", "Bearer");
```

---

</SwmSnippet>

# User service and repository

The <SwmToken path="/src/main/java/com/example/spring_boot/web/controller/MyUserController.java" pos="6:10:10" line-data="import com.example.spring_boot.service.MyUserService;">`MyUserService`</SwmToken> interacts with the <SwmToken path="/src/main/java/com/example/spring_boot/domain/repository/MyUserMongoDBRepository.java" pos="10:4:4" line-data="public interface MyUserMongoDBRepository extends ReactiveMongoRepository&lt;MyUser, String&gt; {">`MyUserMongoDBRepository`</SwmToken> to manage user data.

## Get users logic

<SwmSnippet path="/src/main/java/com/example/spring_boot/service/MyUserService.java" line="26">

---

The <SwmToken path="/src/main/java/com/example/spring_boot/service/MyUserService.java" pos="26:8:8" line-data="    public Flux&lt;MyUser&gt; getUsers() {">`getUsers`</SwmToken> method retrieves all users from the repository. It also calls <SwmToken path="/src/main/java/com/example/spring_boot/service/MyUserService.java" pos="27:3:3" line-data="        myUserClient.getToken();">`getToken`</SwmToken> from <SwmToken path="/src/main/java/com/example/spring_boot/client/MyUserClient.java" pos="8:4:4" line-data="public class MyUserClient {">`MyUserClient`</SwmToken> to demonstrate token usage.

```
    public Flux<MyUser> getUsers() {
        myUserClient.getToken();
        return myUserRepository.findAll();
    }
```

---

</SwmSnippet>

## Add user logic

<SwmSnippet path="/src/main/java/com/example/spring_boot/service/MyUserService.java" line="31">

---

The <SwmToken path="/src/main/java/com/example/spring_boot/service/MyUserService.java" pos="31:8:8" line-data="    public Mono&lt;MyUser&gt; addUser(MyUserRequest myUserRequest) {">`addUser`</SwmToken> method saves a new user to the repository. It constructs a <SwmToken path="/src/main/java/com/example/spring_boot/service/MyUserService.java" pos="31:5:5" line-data="    public Mono&lt;MyUser&gt; addUser(MyUserRequest myUserRequest) {">`MyUser`</SwmToken> object from the request data.

```
    public Mono<MyUser> addUser(MyUserRequest myUserRequest) {
        return myUserRepository.save(new MyUser(myUserRequest.getEmail(), myUserRequest.getName()));
    }
```

---

</SwmSnippet>

## Delete user logic

<SwmSnippet path="/src/main/java/com/example/spring_boot/service/MyUserService.java" line="35">

---

The <SwmToken path="/src/main/java/com/example/spring_boot/service/MyUserService.java" pos="36:8:8" line-data="    public Mono&lt;Void&gt; deleteUser(String eemail) {">`deleteUser`</SwmToken> method deletes a user by email. It is annotated with <SwmToken path="/src/main/java/com/example/spring_boot/service/MyUserService.java" pos="35:1:2" line-data="    @Transactional">`@Transactional`</SwmToken> to ensure data consistency.

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

This document provided an overview of the endpoints and their underlying logic in the Spring Boot application. Each endpoint interacts with specific services and clients to perform its intended function.

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBc3ByaW5nYm9vdC1kZW1vJTNBJTNBbG9yZW56b2RlbmlzaTk3" repo-name="springboot-demo"><sup>Powered by [Swimm](https://app.swimm.io/)</sup></SwmMeta>
