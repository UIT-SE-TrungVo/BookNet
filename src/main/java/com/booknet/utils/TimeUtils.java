package com.booknet.utils;

import java.util.Date;

public class TimeUtils {
    public long getCurrentTimestamp() {
        Date now = new Date();
        return now.getTime();
    }
}
