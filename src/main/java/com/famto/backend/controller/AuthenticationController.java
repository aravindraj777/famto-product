package com.famto.backend.controller;

import com.famto.backend.dto.LoginResponse;
import com.famto.backend.dto.LoginUserDto;
import com.famto.backend.dto.RegisterUserDto;
import com.famto.backend.model.Merchant;
import com.famto.backend.service.IAuthenticationService;
import com.famto.backend.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final JwtService jwtService;
    private final IAuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<Merchant> register(
            @RequestBody RegisterUserDto registerUserDto){

        Merchant registeredUser = authenticationService.signup(registerUserDto);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(
            @RequestBody LoginUserDto loginUserDto){

        Merchant authenticatedUser = authenticationService.authenticate(loginUserDto);
        String jwtToken = jwtService.generateToken(authenticatedUser);
        LoginResponse loginResponse = LoginResponse
                                      .builder()
                                      .token(jwtToken)
                                      .expiresIn(jwtService.getExpirationTime())
                                      .build();
        return ResponseEntity.ok(loginResponse);
    }
}
