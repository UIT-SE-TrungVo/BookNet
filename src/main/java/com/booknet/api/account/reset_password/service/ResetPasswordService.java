package com.booknet.api.account.reset_password.service;

import com.booknet.api.account.authentication.model.AppUser;
import com.booknet.api.account.authentication.repository.AppUserRepository;
import com.booknet.api.account.reset_password.config.ResetPasswordConfig;
import com.booknet.api.account.reset_password.payload.ResetPasswordSubmitTokenRequest;
import com.booknet.api.account.reset_password.repository.ResetPasswordRepository;
import com.booknet.api.account.reset_password.token.PasswordResetToken;
import com.booknet.api.account.reset_password.utils.ResetPasswordUtils;
import com.booknet.constants.ErrCode;
import com.booknet.system.mail.MailService;
import com.booknet.system.mail.model.TextEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class ResetPasswordService {
    @Autowired
    ResetPasswordRepository resetPasswordRepository;

    @Autowired
    AppUserRepository appUserRepository;

    public long handleResetRequest(String email) {
        var user = this._getUserByEmail(email);
        if (user.isEmpty()) {
            return ErrCode.USER_NOT_FOUND_WITH_EMAIL;
        }
        else {
            var token = ResetPasswordUtils.getRandomizedCode();
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

    public long handleTokenSubmit(ResetPasswordSubmitTokenRequest request) {
        var email = request.getMail();
        var token = request.getToken();
        var user = this._getUserByEmail(email);
        if (user.isEmpty()) {
            return ErrCode.USER_NOT_FOUND_WITH_EMAIL;
        }
        else {
            if (_isTokenValidForUser(user.get(), token)) {
                return ErrCode.NONE;
            }
            else {
                return ErrCode.RESET_PASSWORD_TOKEN_MISMATCH;
            }
        }
    }

    private void _createPasswordResetTokenForUser(AppUser user, String token) {
        var userToken = new PasswordResetToken(user, token);
        resetPasswordRepository.save(userToken);
    }

    private Optional<AppUser> _getUserByEmail(String email) {
        return appUserRepository.findByEmail(email);
    }

    private boolean _isTokenValidForUser(AppUser user, String token) {
        var userId = user.get_id();
        var tokenInDb = resetPasswordRepository.findBy_id(userId);
        if (tokenInDb.isEmpty()) return false;
        else {
            return Objects.equals(token, tokenInDb.get().toString());
        }
    }
}