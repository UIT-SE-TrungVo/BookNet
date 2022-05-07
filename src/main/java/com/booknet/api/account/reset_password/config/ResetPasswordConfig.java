package com.booknet.api.account.reset_password.config;

public class ResetPasswordConfig {
    public static final int TOKEN_EXPIRATION_IN_SECOND = 120;

    public static final String MAIL_SUBJECT = "[BookNet] Reset password";
    public static final String MAIL_CONTENT = "Pass the code below to reset your account's password: \n @code";
}