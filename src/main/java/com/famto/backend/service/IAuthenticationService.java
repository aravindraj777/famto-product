package com.famto.backend.service;

import com.famto.backend.dto.LoginUserDto;
import com.famto.backend.dto.RegisterUserDto;
import com.famto.backend.model.Merchant;

public interface IAuthenticationService {

    Merchant signup(RegisterUserDto input);

    Merchant authenticate(LoginUserDto input);
}
