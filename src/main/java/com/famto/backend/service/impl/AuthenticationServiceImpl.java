package com.famto.backend.service.impl;

import com.famto.backend.dto.LoginUserDto;
import com.famto.backend.dto.RegisterUserDto;
import com.famto.backend.enums.Role;
import com.famto.backend.model.Merchant;
import com.famto.backend.repository.UserRepository;
import com.famto.backend.service.IAuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements IAuthenticationService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Merchant signup(RegisterUserDto input) {
        Merchant user = Merchant.builder()
                .firstName(input.getFirstName())
                .lastName(input.getLastName())
                .email(input.getEmail())
                .phoneNumber(input.getPhoneNumber())
                .password(passwordEncoder.encode(input.getPassword()))
                .role(Role.MERCHANT)
                .isBlocked(false)
                .build();
        return userRepository.save(user);
    }

    @Override
    public Merchant authenticate(LoginUserDto input) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );
        return userRepository.findByEmail(input.getEmail())
                .orElseThrow();
    }
}
