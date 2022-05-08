package com.booknet.api.account.reset_password.payload;

import javax.validation.constraints.NotEmpty;

public class ResetPasswordSubmitTokenRequest {
    @NotEmpty
    String email;

    @NotEmpty
    String token;

    public ResetPasswordSubmitTokenRequest() {
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
}