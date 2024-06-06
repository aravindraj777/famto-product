package com.famto.backend.service.impl;

import com.famto.backend.model.Merchant;
import com.famto.backend.repository.IMerchantRepository;
import com.famto.backend.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final IMerchantRepository userRepository;

    @Override
    public List<Merchant> allUsers() {
        return userRepository.findAll();
    }
}
