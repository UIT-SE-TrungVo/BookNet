package com.booknet.utils;

import com.google.gson.Gson;

public class JsonUtils {
    Gson gson = new Gson();

    public JsonUtils() {

    }

    public String stringify(Object data) {
        return gson.toJson(data);
    }
}
