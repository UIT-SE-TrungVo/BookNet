package com.booknet.utils;

import org.bson.types.ObjectId;

public class IdGenerator {
    public static String createNew(String prefix) {
        String objectId = ObjectId.get().toHexString();
        return prefix.concat(objectId);
    }
}
