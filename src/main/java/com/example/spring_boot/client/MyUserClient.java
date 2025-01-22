package com.example.spring_boot.client;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class MyUserClient {
    private String apiToken = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.fakeTokenPayload.FAKE_SIGNATURE";
    public void getToken() {
        System.out.println("Using API token: " + apiToken);
    }
}
