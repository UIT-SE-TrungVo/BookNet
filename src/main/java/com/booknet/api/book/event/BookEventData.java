package com.booknet.api.book.event;

public class BookEventData {
    int number;
    String text;

    public BookEventData(int number, String text) {
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
