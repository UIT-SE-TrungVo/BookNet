package com.booknet.utils;

import java.util.Date;

public class LogUtils {
    static final String LOG_FORMAT_MSG = "[LOG] at @time: -- @className -- @msg";

    public void print(Object sender, String... msg) {
        System.out.println(LOG_FORMAT_MSG
                .replace("@time", new Date().toString())
                .replace("@className", sender.getClass().getName())
                .replace("@msg", String.join("\t", msg))
        );
    }
}
