package com.projectx.service;

import com.projectx.model.User;
import com.projectx.repository.UserRepository;
import com.projectx.utils.CpfValidator;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserService {

    UserRepository userRepository;

    public void saveUser(User user) {
        if (CpfValidator.isAValidCpf(user.getDocument()))
        userRepository.save(user);
    }
}
