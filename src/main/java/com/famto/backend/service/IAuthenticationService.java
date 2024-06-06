package com.famto.backend.service;

import com.famto.backend.dto.LoginUserDto;
import com.famto.backend.dto.RegisterUserDto;
import com.famto.backend.model.Merchant;
import org.springframework.security.core.userdetails.UserDetails;

public interface IAuthenticationService {

    Merchant signup(RegisterUserDto input);

    UserDetails authenticate(LoginUserDto input);
}
