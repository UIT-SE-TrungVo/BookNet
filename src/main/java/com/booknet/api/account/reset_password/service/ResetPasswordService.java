package com.booknet.api.account.reset_password.service;

import com.booknet.api.account.authentication.model.AppUser;
import com.booknet.api.account.authentication.service.AppUserService;
import com.booknet.api.account.reset_password.config.ResetPasswordConfig;
import com.booknet.api.account.reset_password.payload.ResetPasswordRenewRequest;
import com.booknet.api.account.reset_password.payload.ResetPasswordSubmitTokenRequest;
import com.booknet.api.account.reset_password.repository.ResetPasswordRepository;
import com.booknet.api.account.reset_password.token.PasswordResetToken;
import com.booknet.constants.ErrCode;
import com.booknet.system.mail.MailService;
import com.booknet.system.mail.model.TextEmail;
import com.booknet.system.token_generator.TokenGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

@Service
public class ResetPasswordService {
    private static final Logger logger = LoggerFactory.getLogger(ResetPasswordService.class);

    @Autowired
    ResetPasswordRepository resetPasswordRepository;

    @Autowired
    AppUserService appUserService;

    public long handleRequestToken(String email) {
        var user = appUserService.getUserByEmail(email);
        if (user.isEmpty()) {
            logger.error("No user found with email {}", email);
            return ErrCode.USER_NOT_FOUND_WITH_EMAIL;
        } else {
            var tokenLength = ResetPasswordConfig.RESET_CODE_LENGTH;
            var tokenCharSet = ResetPasswordConfig.RESET_CODE_CHAR_SET;

            var token = TokenGenerator.getRandomizedString(tokenLength, tokenCharSet);
            this._createPasswordResetTokenForUser(user.get(), token);

            var subject = ResetPasswordConfig.MAIL_SUBJECT;
            var content = ResetPasswordConfig.MAIL_CONTENT
                    .replace("@code", token);

            var validationEmail = new TextEmail();
            validationEmail.addRecipient(email);
            validationEmail.setSubject(subject);
            validationEmail.setContent(content);
            MailService.sendTextMail(validationEmail);

            logger.info("Created token for password renew {} of user {}", token, email);
            return ErrCode.NONE;
        }
    }

    public long handleSubmitToken(ResetPasswordSubmitTokenRequest request) {
        var email = request.getEmail();
        var token = request.getToken();
        var user = appUserService.getUserByEmail(email);
        if (user.isEmpty()) {
            logger.error("No user found with email {}", email);
            return ErrCode.USER_NOT_FOUND_WITH_EMAIL;
        } else {
            if (_isTokenValidForUser(user.get(), token)) {
                logger.info("Token match {} {}", token, email);
                return ErrCode.NONE;
            } else {
                logger.error("Token mismatch {} {}", token, email);
                return ErrCode.RESET_PASSWORD_TOKEN_MISMATCH;
            }
        }
    }

    public long handleChangePassword(ResetPasswordRenewRequest request) {
        var email = request.getEmail();
        var token = request.getToken();
        var user = appUserService.getUserByEmail(email);
        if (user.isEmpty()) {
            return ErrCode.USER_NOT_FOUND_WITH_EMAIL;
        } else {
            if (_isTokenValidForUser(user.get(), token)) {
                var newPassword = request.getNewPassword();
                appUserService.updateNewPassword(user.get(), newPassword);
                logger.info("change password for user {} {}", user.get().get_id(), newPassword);
                return ErrCode.NONE;
            } else {
                logger.error("cannot change password of user {} due to mismatch token {}", user.get().get_id(), token);
                return ErrCode.RESET_PASSWORD_TOKEN_MISMATCH;
            }
        }
    }

    private void _createPasswordResetTokenForUser(AppUser user, String token) {
        var userToken = new PasswordResetToken(user, token);
        resetPasswordRepository.save(userToken);
    }

    private boolean _isTokenValidForUser(AppUser user, String token) {
        var userId = user.get_id();
        var tokenDataInDb = resetPasswordRepository.findBy_id(userId);
        if (tokenDataInDb.isEmpty()) {
            logger.error("Validation failed: no relevant data {} {}", user.get_id(), token);
            return false;
        } else {
            var expiryDate = tokenDataInDb.get().getExpiryDate();
            var tokenString = tokenDataInDb.get().getToken();

            var tokenMatched = Objects.equals(token, tokenString);
            var tokenNotExpired = expiryDate.after(new Date());

            if (!tokenMatched) {
                logger.error("Validation failed: token not match {} {}", user.get_id(), token);
                return false;
            } else if (!tokenNotExpired) {
                logger.error("Validation failed: token expired {} {}", user.get_id(), token);
                return false;
            } else {
                logger.info("Validation success! {} {}", user.get_id(), token);
                return true;
            }
        }
    }
}