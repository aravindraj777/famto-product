package com.famto.backend.service;

import com.famto.backend.dto.LoginUserDto;
import com.famto.backend.dto.RegisterUserDto;
import com.famto.backend.model.User;

public interface IAuthenticationService {

    User signup(RegisterUserDto input);

    User authenticate(LoginUserDto input);
}
