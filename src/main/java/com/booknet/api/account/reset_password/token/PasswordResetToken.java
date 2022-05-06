package com.booknet.api.account.reset_password.token;

import com.booknet.api.account.authentication.model.AppUser;
import org.springframework.data.annotation.Id;

import java.util.Date;

public class PasswordResetToken {

    @Id
    String _id;

    String token;
    AppUser user;
    Date expiryDate;

    public PasswordResetToken(AppUser user, String token) {
        this.token = token;
        this.user = user;
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