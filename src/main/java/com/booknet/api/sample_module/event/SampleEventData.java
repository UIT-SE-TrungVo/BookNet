package com.booknet.api.sample_module.event;

public class SampleEventData {
    int number;
    String text;

    public SampleEventData(int number, String text) {
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
