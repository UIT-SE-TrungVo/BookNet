package com.booknet.api.feed.event;

public class FeedEventData {
    int number;
    String text;

    public FeedEventData(int number, String text) {
        this.number = number;
        this.text = text;
    }

    public int getNumber() {
        return number;
    }

    public String getText() {
        return text;
    }
}
