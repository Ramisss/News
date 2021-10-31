package com.example.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private UUID userId;

    private String firstName;

    private String userName;

    private String email;

    private String phoneNumber;

    private String password;

    private String age;

    public UserDto(String firstName, String userName, String email, String phoneNumber, String password, String age) {
        this.firstName = firstName;
        this.userName = userName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.age = age;
    }
}
