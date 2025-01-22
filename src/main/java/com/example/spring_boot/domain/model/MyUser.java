package com.example.spring_boot.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@NoArgsConstructor
@Document
public class MyUser {

    @Indexed(unique = true) private String email;
    private String name;

    public MyUser(String email, String name) {
        this.email = email;
        this.name = name;
    }

    public String toString() {
        return "User{" + "name=" + name + ", email=" + email + "}";
    }

    public void setName(String name) {
        name = name;
    }
}
