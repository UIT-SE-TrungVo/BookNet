package com.booknet.api.account.reset_password.payload;

import javax.validation.constraints.NotEmpty;

public class ResetPasswordRenewRequest {
    @NotEmpty
    String mail;

    @NotEmpty
    String token;

    @NotEmpty
    String newPassword;

    public ResetPasswordRenewRequest() {
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}