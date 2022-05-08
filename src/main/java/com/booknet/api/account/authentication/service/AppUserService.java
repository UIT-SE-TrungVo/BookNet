package com.booknet.api.account.authentication.service;

import com.booknet.api.account.authentication.model.AppUser;
import com.booknet.api.account.authentication.repository.AppUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserService {
    private static final Logger logger = LoggerFactory.getLogger(AppUserService.class);

    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    PasswordEncoder encoder;

    public Optional<AppUser> getUserByEmail(String email) {
        return appUserRepository.findByEmail(email);
    }

    public void updateNewPassword(AppUser user, String newPassword) {
        var userId = user.get_id();
        if (appUserRepository.existsBy_id(userId)) {
            user.setPassword(encoder.encode(newPassword));
            appUserRepository.save(user);
            logger.info("Successfully update new password for user {}", userId);
        } else {
            logger.error("Cannot update password for user {}, USER NOT EXIST IN DATA", userId);
        }
    }
}