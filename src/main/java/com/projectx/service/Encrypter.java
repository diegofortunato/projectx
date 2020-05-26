package com.projectx.service;

import com.projectx.model.User;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
class Encrypter {

    public static String encryptEmail(User user) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-512");
        return digest.digest(user.getEmail().getBytes(StandardCharsets.UTF_8)).toString();
    }
}
