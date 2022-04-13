package com.booknet.utils;

import java.util.Date;

public class TimeUtils {
    public Long getCurrentTimeStamp() {
        Date now = new Date();
        return now.getTime();
    }
}
