package com.projectx.service;

import com.projectx.model.User;
import com.projectx.repository.UserRepository;
import com.projectx.utils.UserValidator;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;

@Data
@Service
public class UserService {

    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void validateAndSaveUser(User user) throws Exception {
        if (UserValidator.isAValidCpf(user.getDocument())){
            userRepository.save(user);
        }else{
            throw new Exception("Dados inválidos");
        }
    }
}
