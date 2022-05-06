package com.booknet.api.account.reset_password.support;

import java.util.Date;

public class ResetPasswordUtils {
    public static Date getExpiryDate(Date creationDate) {
        var offset = ResetPasswordConstant.TOKEN_EXPIRATION_IN_SECOND;
        return new Date(creationDate.getTime() + offset * 1000);
    }
}