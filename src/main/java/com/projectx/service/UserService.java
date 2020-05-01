package com.projectx.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
        Optional<User> optionalUser = userRepository.findBycpf(user.getDocument());
        if (optionalUser.isPresent()) {
            updatedUserDateList(user, optionalUser);
            userRepository.save(optionalUser.get());
        } else {
            userRepository.save(user);
        }


   /*     if (UserValidator.isAValidCpf(user.getDocument())){
            userRepository.save(user);
        }else{
            throw new Exception("Dados inválidos");
        }*/
    }

    private void updatedUserDateList(User user, Optional<User> optionalUser) {
        optionalUser.get().getHintDateList().addAll(user.getHintDateList());
    }

    public User findUser(String cpf) {
        if(cpf != null) {
            return userRepository.findBycpf(cpf).get();
        }else {
            return null;
        }
    }
}
