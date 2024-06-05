package com.famto.backend.service.impl;

import com.famto.backend.dto.RegisterUserDto;
import com.famto.backend.enums.Role;
import com.famto.backend.model.User;
import com.famto.backend.repository.UserRepository;
import com.famto.backend.service.IAuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements IAuthenticationService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User signup(RegisterUserDto input) {
        User user = User.builder()
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
}
