package com.projectx.service;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import com.projectx.model.User;
import com.projectx.repository.UserRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@Service
public class UserService {

    UserRepository userRepository;
    SenderMailService senderMailService;

    public UserService(UserRepository userRepository, SenderMailService senderMailService) {
        this.userRepository = userRepository;
        this.senderMailService = senderMailService;
    }

    public Optional<User> validateAndSaveUser(User user) throws Exception {
        Optional<User> optionalUser = userRepository.findByEmail(user.getEmail());
        if (optionalUser.isPresent()) {
            if (optionalUser.get().getVerifiedEmail()) {
                return Optional.of(operatePresentUser(user, optionalUser));
            }else {
                /*TODO EMAIL NÃO VERIFICADO*/
                return Optional.empty();
            }
        } else {
            return Optional.of(operateNewUser(user));
        }
    }

    private User operatePresentUser(User user, Optional<User> optionalUser) throws NoSuchAlgorithmException {
        optionalUser.get().setVerifiedEmail(true);
        updatedUserDateList(user, optionalUser);
        return userRepository.save(optionalUser.get());
    }

    private User operateNewUser(User user) throws NoSuchAlgorithmException {
        String encodedhash = Encrypter.encryptEmail(user);
        user.setEmailSHA512(encodedhash);
        user.setVerifiedEmail(false);

        senderMailService.enviar(user); /*TODO ABRIR THREAD*/
        return userRepository.save(user);
    }

    private void updatedUserDateList(User user, Optional<User> optionalUser) {
        optionalUser.get().getHintDateList().addAll(user.getHintDateList());
    }

    public User findUser(String cpf) {
        if(cpf != null) {
            return userRepository.findByEmail(cpf).get();
        }else {
            return null;
        }
    }
}
