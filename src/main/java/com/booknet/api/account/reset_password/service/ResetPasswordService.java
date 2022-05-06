package com.booknet.api.account.reset_password.service;

import com.booknet.api.account.authentication.model.AppUser;
import com.booknet.api.account.authentication.repository.IAppUserRepository;
import com.booknet.api.account.reset_password.repository.ResetPasswordRepository;
import com.booknet.api.account.reset_password.token.PasswordResetToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ResetPasswordService {
    @Autowired
    ResetPasswordRepository resetPasswordRepository;

    @Autowired
    IAppUserRepository appUserRepository;

    public void createPasswordResetTokenForUser(AppUser user, String token) {
        var userToken = new PasswordResetToken(user, token);
        resetPasswordRepository.save(userToken);
    }

    public Optional<AppUser> getUserByEmail(String email) {
        if (appUserRepository.existsByEmail(email)) {
            return appUserRepository.findByEmail(email);
        }
        else {
            return Optional.empty();
        }
    }
}