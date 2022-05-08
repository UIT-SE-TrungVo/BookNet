package com.booknet.api.account.reset_password.utils;

import com.booknet.api.account.reset_password.config.ResetPasswordConfig;
import com.booknet.utils.Utils;

import java.util.Date;

public class ResetPasswordUtils {
    public static Date getExpiryDate(Date creationDate) {
        var offset = ResetPasswordConfig.TOKEN_EXPIRATION_IN_SECOND;
        return new Date(creationDate.getTime() + offset * 1000);
    }

    public static String getRandomizedCode() {
        var length = ResetPasswordConfig.RESET_CODE_LENGTH;
        var charSet = ResetPasswordConfig.RESET_CODE_CHAR_SET;
        StringBuilder token = new StringBuilder();

        for (var i = 0; i < length; i++) {
            var randomizedIndex = Utils.math.randomInt(0, charSet.length);
            var randomizedChar = charSet[randomizedIndex];
            token.append(randomizedChar);
        }
        return token.toString();
    }
}