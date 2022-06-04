package com.booknet.api.book.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class BookCreateRequest {
    @NotNull
    Integer number;

    @NotEmpty
    String text;

    public BookCreateRequest() {
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
