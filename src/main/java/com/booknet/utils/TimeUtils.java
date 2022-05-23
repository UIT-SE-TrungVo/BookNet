package com.booknet.utils;

import org.jetbrains.annotations.NotNull;

import java.util.Date;

public class TimeUtils {
    public long getCurrentTimestamp() {
        Date now = new Date();
        return now.getTime();
    }

    public @NotNull Date getExpiryDate(@NotNull Date creationDate, long duration) {
        return new Date(creationDate.getTime() + duration * 1000);
    }

    public @NotNull Date getExpiryDateFromNow(long duration) {
        return this.getExpiryDate(new Date(), duration);
    }
}