package com.famto.backend.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUserDto {

    private String firstName;
    private String lastName;
    private String email;
    private Long phoneNumber;
    private String password;
}
