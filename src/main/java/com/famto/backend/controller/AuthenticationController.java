package com.famto.backend.controller;

import com.famto.backend.dto.LoginResponse;
import com.famto.backend.dto.LoginUserDto;
import com.famto.backend.dto.RegisterMerchantDto;
import com.famto.backend.model.Merchant;
import com.famto.backend.service.IAuthenticationService;
import com.famto.backend.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final JwtService jwtService;
    private final IAuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<Merchant> registerMerchant(
            @RequestBody RegisterMerchantDto registerUserDto){

        Merchant registeredUser = authenticationService.registerMerchant(registerUserDto);
        return ResponseEntity.ok(registeredUser);
    }



    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        UserDetails authenticatedUser = authenticationService.authenticate(loginUserDto);
        String jwtToken = jwtService.generateToken(authenticatedUser);


        String email = authenticatedUser.getUsername();
        List<String> roles = authenticatedUser.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());


        LoginResponse loginResponse = LoginResponse.builder()
                .token(jwtToken)
                .expiresIn(jwtService.getExpirationTime())
                .email(email)
                .roles(roles)
                .build();

        return ResponseEntity.ok(loginResponse);
    }


}
