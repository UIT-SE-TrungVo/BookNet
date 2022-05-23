package com.booknet.api.feed.request.news;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ReviewNewsCreateRequest {

    @NotNull
    private String userId;

    @NotEmpty
    private String caption;

    public ReviewNewsCreateRequest() {
    }

    public ReviewNewsCreateRequest(String userId, String caption) {
        this.userId = userId;
        this.caption = caption;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }
}
