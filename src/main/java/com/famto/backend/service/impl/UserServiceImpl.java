package com.famto.backend.service.impl;

import com.famto.backend.model.User;
import com.famto.backend.repository.UserRepository;
import com.famto.backend.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;

    @Override
    public List<User> allUsers() {
        return userRepository.findAll();
    }
}
