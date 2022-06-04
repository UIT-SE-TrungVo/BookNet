package com.booknet.api.profile.config;

import com.booknet.constants.Gender;

import java.util.Calendar;
import java.util.Date;

public class ProfileDefaultConfig {
    public static final Gender GENDER = Gender.UNKNOWN;
    public static final Date DOB = new Date(1999, Calendar.JANUARY, 1);
}
