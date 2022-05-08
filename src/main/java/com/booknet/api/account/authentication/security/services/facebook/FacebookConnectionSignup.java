package com.booknet.api.account.authentication.security.services.facebook;

import com.booknet.api.account.authentication.model.AppUser;
import com.booknet.api.account.authentication.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Service;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

@Service
public class FacebookConnectionSignup implements ConnectionSignUp {

    @Autowired
    private AppUserRepository userRepository;

    @Override
    public String execute(Connection<?> connection) {
        final int PASSWORD_LENGTH = 16;

        AppUser user = new AppUser();
        user.setUsername(connection.getDisplayName());
        user.setPassword(randomAlphabetic(PASSWORD_LENGTH));
        userRepository.save(user);
        return user.getUsername();
    }
}