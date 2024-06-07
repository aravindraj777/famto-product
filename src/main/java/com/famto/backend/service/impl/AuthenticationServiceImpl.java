package com.famto.backend.service.impl;

import com.famto.backend.dto.LoginUserDto;
import com.famto.backend.dto.RegisterMerchantDto;
import com.famto.backend.enums.Role;
import com.famto.backend.exception.DuplicateEntryException;
import com.famto.backend.model.Admin;
import com.famto.backend.model.Merchant;
import com.famto.backend.repository.IAdminRepository;
import com.famto.backend.repository.IMerchantRepository;
import com.famto.backend.service.IAuthenticationService;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements IAuthenticationService {

    private final IMerchantRepository merchantRepository;
    private final IAdminRepository adminRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Merchant registerMerchant(RegisterMerchantDto input) {
        try {
            Merchant merchant = Merchant.builder()
                    .shopName(input.getShopName())
                    .ownerName(input.getOwnerName())
                    .email(input.getEmail())
                    .phoneNumber(input.getPhoneNumber())
                    .password(passwordEncoder.encode(input.getPassword()))
                    .categoryName(input.getCategoryName())
                    .role(Role.MERCHANT)
                    .isBlocked(false)
                    .build();
            return merchantRepository.save(merchant);
        }
        catch (DataIntegrityViolationException e) {
            if (e.getCause() instanceof ConstraintViolationException) {
                throw new DuplicateEntryException("A merchant with this details already exists.");
            }
            throw e;
        }

    }

    @Override
    public UserDetails authenticate(LoginUserDto input) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        Admin admin = adminRepository.findByEmail(input.getEmail()).orElse(null);
        if (admin != null) {
            return admin;
        }

        Merchant merchant = merchantRepository.findByEmail(input.getEmail()).orElse(null);
        if (merchant != null) {
            return merchant;
        }
        throw new UsernameNotFoundException("User not found"+input.getEmail());
    }
}
