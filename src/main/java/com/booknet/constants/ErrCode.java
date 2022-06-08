package com.booknet.constants;

public class ErrCode {
    public static final long NONE = 0;
    public static final long DEMO_NUMBER_CREATION_FAILED = 1;

    //account
    public static final long USER_NOT_FOUND = 10;
    public static final long USER_NOT_FOUND_WITH_EMAIL = 11;

    //register
    public static final long REGISTER_USERNAME_TAKEN = 20;
    public static final long REGISTER_EMAIL_TAKEN = 21;
    public static final long VERIFY_INVALID_EMAIL = 22;
    public static final long VERIFY_TOKEN_MISMATCH = 23;
    public static final long VERIFY_TOKEN_EXPIRED = 24;

    //reset password
    public static final long RESET_PASSWORD_TOKEN_MISMATCH = 30;

    //post
    public static final long POST_NOT_EXIST = 40;

    //guild
    public static final long GUILD_NOT_FOUND = 50;
    public static final long GUILD_ALREADY_JOINED = 51;
    public static final long GUILD_NOT_A_MEMBER = 52;

    //friend
    public static final long FRIEND_NOT_FOUND = 60;
    public static final long FRIEND_ALREADY_MATCHED = 60;
    public static final long FRIEND_ALREADY_UNMATCHED = 60;
}