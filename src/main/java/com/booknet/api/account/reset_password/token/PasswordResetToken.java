package com.booknet.api.account.reset_password.token;

import com.booknet.api.account.authentication.model.AppUser;
import com.booknet.api.account.reset_password.config.ResetPasswordConfig;
import com.booknet.utils.Utils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "password_reset_tokens")
public class PasswordResetToken {

    @Id
    String _id;

    String token;
    AppUser user;
    Date expiryDate;

    public PasswordResetToken(AppUser user, String token) {
        this.token = token;
        this.user = user;

        var userId = user.get_id();
        this.set_id(userId);

        var lifespan = ResetPasswordConfig.TOKEN_EXPIRATION_IN_SECOND;
        var expiryDate = Utils.time.getExpiryDateFromNow(lifespan);
        this.setExpiryDate(expiryDate);
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }
}