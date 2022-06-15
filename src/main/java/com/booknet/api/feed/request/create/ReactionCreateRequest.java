package com.booknet.api.feed.request.create;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ReactionCreateRequest {

    @NotEmpty
    String userId;

    @NotEmpty
    String newsId;

    @NotNull
    int newsType;

    public ReactionCreateRequest(String userId, String newsId, int newsType) {
        this.userId = userId;
        this.newsId = newsId;
        this.newsType = newsType;
    }

    public String getUserId() {
        return userId;
    }

    public String getNewsId() {
        return newsId;
    }

    public int getNewsType() {
        return newsType;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
