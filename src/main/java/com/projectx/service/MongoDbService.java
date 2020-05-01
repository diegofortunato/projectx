package com.projectx.service;

import com.projectx.model.User;
import com.projectx.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class MongoDbService {

    private final UserRepository userRepository;

    public MongoDbService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }
}