package com.booknet.api.account.reset_password.service;

import com.booknet.api.account.authentication.model.AppUser;
import com.booknet.api.account.authentication.repository.IAppUserRepository;
import com.booknet.api.account.reset_password.config.ResetPasswordConfig;
import com.booknet.api.account.reset_password.repository.ResetPasswordRepository;
import com.booknet.api.account.reset_password.token.PasswordResetToken;
import com.booknet.constants.ErrCode;
import com.booknet.system.mail.MailService;
import com.booknet.system.mail.model.TextEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ResetPasswordService {
    @Autowired
    ResetPasswordRepository resetPasswordRepository;

    @Autowired
    IAppUserRepository appUserRepository;

    public long handlePasswordReset(String email) {
        var user = this._getUserByEmail(email);
        if (user.isEmpty()) {
            return ErrCode.USER_NOT_FOUND_WITH_EMAIL;
        }
        else {
            var token = UUID.randomUUID().toString();
            this._createPasswordResetTokenForUser(user.get(), token);

            var subject = ResetPasswordConfig.MAIL_SUBJECT;
            var content = ResetPasswordConfig.MAIL_CONTENT
                    .replace("@code", token);

            var validationEmail = new TextEmail();
            validationEmail.addRecipient(email);
            validationEmail.setSubject(subject);
            validationEmail.setContent(content);
            MailService.sendTextMail(validationEmail);
            return ErrCode.NONE;
        }
    }

    private void _createPasswordResetTokenForUser(AppUser user, String token) {
        var userToken = new PasswordResetToken(user, token);
        resetPasswordRepository.save(userToken);
    }

    public Optional<AppUser> _getUserByEmail(String email) {
        return appUserRepository.findByEmail(email);
    }
}