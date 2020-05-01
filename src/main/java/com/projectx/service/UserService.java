package com.projectx.service;

import com.projectx.model.User;
import com.projectx.repository.UserRepository;
import com.projectx.utils.CpfValidator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserService {

    UserRepository userRepository;

    public void saveUser(User user) throws Exception {
        if (CpfValidator.isAValidCpf(user.getDocument())){
            userRepository.save(user);
        }else{
            throw new Exception("Dados inválidos");
        }
    }
}
