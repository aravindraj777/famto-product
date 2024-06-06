package com.famto.backend.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUserDto {

    private String shopName;
    private String ownerName;
    private String email;
    private Long phoneNumber;
    private String password;
}
