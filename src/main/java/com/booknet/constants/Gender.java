package com.booknet.constants;

public enum Gender {
    MALE(0),
    FEMALE(1),
    UNKNOWN(2);

    private final int number;

    Gender(int number) {
        this.number = number;
    }

    public int toNumber() {
        return number;
    }
}
