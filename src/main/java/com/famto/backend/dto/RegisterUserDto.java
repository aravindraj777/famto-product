package com.famto.backend.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class RegisterUserDto {

    private String firstName;
    private String lastName;
    private String email;
    private Long phoneNumber;
    private String password;
}
