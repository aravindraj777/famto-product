package com.famto.backend.service;

import com.famto.backend.dto.LoginUserDto;
import com.famto.backend.dto.RegisterMerchantDto;
import com.famto.backend.model.Merchant;
import org.springframework.security.core.userdetails.UserDetails;

public interface IAuthenticationService {

    Merchant registerMerchant(RegisterMerchantDto input);

    UserDetails authenticate(LoginUserDto input);
}
