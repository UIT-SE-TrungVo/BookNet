package com.booknet.system.token_generator;

import com.booknet.utils.Utils;

public class TokenGenerator {

    public static String getRandomizedString(long length, String[] charSet) {
        StringBuilder token = new StringBuilder();

        for (var i = 0; i < length; i++) {
            var randomizedIndex = Utils.math.randomInt(0, charSet.length - 1);
            var randomizedChar = charSet[randomizedIndex];
            token.append(randomizedChar);
        }
        return token.toString();
    }
}