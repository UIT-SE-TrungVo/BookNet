package com.booknet.api.account.reset_password.utils;

import com.booknet.api.account.reset_password.config.ResetPasswordConfig;

import java.util.Date;

public class ResetPasswordUtils {
    public static Date getExpiryDate(Date creationDate) {
        var offset = ResetPasswordConfig.TOKEN_EXPIRATION_IN_SECOND;
        return new Date(creationDate.getTime() + offset * 1000);
    }
}