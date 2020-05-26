package com.projectx.service;

import com.projectx.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


@Component
public class SenderMailService {

    @Autowired
    private JavaMailSender mailSender;
    private Encrypter encrypter;

    public void enviar(User user) throws NoSuchAlgorithmException {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(user.getEmail());
        email.setSubject("Confirmação de e-mail");

        email.setText("Clique no link: http://localhost:8080/vl/"+user.getEmailSHA512());
        mailSender.send(email);
    }

    private static String bytesToHex(byte[] hash) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
}