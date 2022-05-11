package com.booknet.api.post.request;

import javax.validation.constraints.NotNull;

public class PostNotifyRequest {
    @NotNull
    Integer number;

    public PostNotifyRequest() {
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
