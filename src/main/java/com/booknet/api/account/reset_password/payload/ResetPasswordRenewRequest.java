package com.booknet.api.account.reset_password.payload;

import javax.validation.constraints.NotEmpty;

public class ResetPasswordRenewRequest {
    @NotEmpty
    String email;

    @NotEmpty
    String token;

    @NotEmpty
    String newPassword;

    public ResetPasswordRenewRequest() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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