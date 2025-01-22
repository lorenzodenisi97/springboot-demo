package com.example.spring_boot.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MyUserRequest {
    @JsonProperty("email")
    private String email;

    @JsonProperty("name")
    private String name;
}