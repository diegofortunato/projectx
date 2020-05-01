package com.projectx.service;

import java.util.Optional;

import com.projectx.model.User;
import com.projectx.repository.UserRepository;
import com.projectx.utils.UserValidator;
import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@Service
public class UserService {

    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser(User user) throws Exception {
        if (UserValidator.isAValidCpf(user.getDocument())){
            userRepository.save(user);
        }else{
            throw new Exception("Dados inválidos");
        }
    }

    public User findUser(String cpf) {
        if(cpf != null) {
            return userRepository.findBycpf(cpf);
        }else {
            return null;
        }
    }
}
