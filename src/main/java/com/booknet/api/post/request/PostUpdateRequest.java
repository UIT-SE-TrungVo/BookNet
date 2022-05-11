package com.booknet.api.post.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PostUpdateRequest {
    @NotNull
    Integer number;

    @NotEmpty
    String text;

    public PostUpdateRequest() {
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