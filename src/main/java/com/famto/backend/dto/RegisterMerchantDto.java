package com.famto.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterMerchantDto {

    private String shopName;
    private String ownerName;
    private String email;
    private Long phoneNumber;
    private String categoryName;
    private String password;
}
