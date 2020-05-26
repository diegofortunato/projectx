package com.projectx.service;

import com.projectx.model.User;
import com.projectx.repository.UserRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Data
@Service
public class EmailService {

    UserRepository userRepository;
    SenderMailService senderMailService;

    public EmailService(UserRepository userRepository, SenderMailService senderMailService) {
        this.userRepository = userRepository;
        this.senderMailService = senderMailService;
    }

    public Optional<User> confirmEmail(String hash) throws Exception {
        Optional<User> optionalUser = userRepository.findByEmailHash(hash);
        if (optionalUser.isPresent()) {
            optionalUser.get().setVerifiedEmail(true);
            return Optional.of(userRepository.save(optionalUser.get()));
        } else {
            return Optional.empty();
        }
    }

    public Optional<User> resendEmail(User user) throws Exception {
        Optional<User> optionalUser = userRepository.findByEmail(user.getEmail());
        if (optionalUser.isPresent() && !optionalUser.get().getVerifiedEmail()) {
            senderMailService.enviar(optionalUser.get());
            return optionalUser;
        } else {
            return Optional.empty();
        }
    }
}
