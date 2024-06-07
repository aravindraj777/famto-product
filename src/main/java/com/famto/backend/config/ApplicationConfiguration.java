package com.famto.backend.config;

import com.famto.backend.model.Admin;
import com.famto.backend.model.Merchant;
import com.famto.backend.repository.IAdminRepository;
import com.famto.backend.repository.IMerchantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfiguration   {

    private final IMerchantRepository merchantRepository;
    private final IAdminRepository adminRepository;

    @Bean
    UserDetailsService userDetailsService(){
        return username -> {
            Admin admin = adminRepository.findByEmail(username).orElse(null);
            if (admin != null) {
                return admin;
            }

            Merchant merchant = merchantRepository.findByEmail(username).orElse(null);
            if (merchant != null){
                return merchant;
            }
            throw new UsernameNotFoundException("User not found with this email"+username);
        };
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config)throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
}
