package com.famto.backend.service;

import com.famto.backend.model.User;
import com.famto.backend.repository.UserRepository;

import java.util.List;


public interface IUserService {

    List<User> allUsers();

}
