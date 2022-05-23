package com.booknet.api.feed.model;

public enum NewsType {
    POST(1),
    GUILD(2),
    REVIEW(3);

    private final int newsCode;

    NewsType(int typeInt) {
        newsCode = typeInt;
    }

    public int getNewsCode() {
        return newsCode;
    }

    public static NewsType fromCode(int code) {
        for (NewsType type : NewsType.values()) {
            if (type.newsCode == code)
                return type;
        }
        return null;
    }
}
