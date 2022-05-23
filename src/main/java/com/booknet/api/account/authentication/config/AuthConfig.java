package com.booknet.api.account.authentication.config;

public class AuthConfig {
    public static final long VERIFY_TOKEN_EXPIRATION_IN_SECONDS = 1200;
    public static final long VERIFY_TOKEN_LENGTH = 4;
    public static final String[] VERIFY_TOKEN_LENGTH_CHAR_SET = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};


    public static final String MAIL_SUBJECT = "[BookNet] Verify your new account";
    public static final String MAIL_CONTENT = "Enter the code below to verify your account: \n @code";
}