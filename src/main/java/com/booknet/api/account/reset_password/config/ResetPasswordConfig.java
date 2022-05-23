package com.booknet.api.account.reset_password.config;

public class ResetPasswordConfig {
    public static final int TOKEN_EXPIRATION_IN_SECOND = 1200;

    public static final long RESET_CODE_LENGTH = 4;
    public static final String[] RESET_CODE_CHAR_SET = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

    public static final String MAIL_SUBJECT = "[BookNet] Reset password";
    public static final String MAIL_CONTENT = "Enter the code below to reset your account's password: \n @code";
}