package com.booknet.constants;

public class ErrCode {
    public static final long NONE = 0;
    public static final long DEMO_NUMBER_CREATION_FAILED = 1;

    //account
    public static final long USER_NOT_FOUND_WITH_EMAIL = 2;
    //register
    public static final long REGISTER_USERNAME_TAKEN = 3;
    public static final long REGISTER_EMAIL_TAKEN = 4;
    public static final long VERIFY_INVALID_EMAIL = 5;
    public static final long VERIFY_TOKEN_MISMATCH = 6;
    public static final long VERIFY_TOKEN_EXPIRED = 7;
    //reset password
    public static final long RESET_PASSWORD_TOKEN_MISMATCH = 8;
    //post
    public static final long POST_NOT_EXIST = 9;
}