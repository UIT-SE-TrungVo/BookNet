package com.booknet.api.account.reset_password.payload;

import javax.validation.constraints.NotEmpty;

public class ResetPasswordSubmitTokenRequest {
    @NotEmpty
    String mail;

    @NotEmpty
    String token;

    public ResetPasswordSubmitTokenRequest() {
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
}