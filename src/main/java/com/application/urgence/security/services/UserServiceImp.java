package com.application.urgence.security.services;

import com.application.urgence.models.User;
import com.application.urgence.repository.UserRepository;
import com.application.urgence.security.EmailConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService{

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UserRepository userRepo;

    @Autowired
    private EmailConstructor emailConstructor;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void resetPassword(User user) {
        String password = RandomStringUtils.randomAlphanumeric(10);
        String encryptedPassword = bCryptPasswordEncoder.encode(password);
        user.setPassword(encryptedPassword);
        userRepo.save(user);
        mailSender.send(emailConstructor.constructResetPasswordEmail(user, password));

    }

    @Override
    public void updateUserPassword(User user, String newpassword) {

        String encryptedPassword = bCryptPasswordEncoder.encode(newpassword);
        System.out.println(encryptedPassword+ " je suis iciiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
        user.setPassword(encryptedPassword);
        userRepo.save(user);
        mailSender.send(emailConstructor.constructResetPasswordEmail(user, newpassword));
    }
}
