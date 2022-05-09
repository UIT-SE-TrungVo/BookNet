package com.booknet.api.book.request;

import javax.validation.constraints.NotNull;

public class BookNotifyRequest {
    @NotNull
    Integer number;

    public BookNotifyRequest() {
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
