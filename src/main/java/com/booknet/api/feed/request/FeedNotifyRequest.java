package com.booknet.api.feed.request;

import javax.validation.constraints.NotNull;

public class FeedNotifyRequest {
    @NotNull
    Integer number;

    public FeedNotifyRequest() {
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
