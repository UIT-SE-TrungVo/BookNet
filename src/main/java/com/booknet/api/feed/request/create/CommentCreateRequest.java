package com.booknet.api.feed.request.create;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CommentCreateRequest {

    @NotNull
    String newsId;

    @NotNull
    String userId;

    @NotNull
    int newsType;

    @NotEmpty
    String content;

    public CommentCreateRequest(String newsId, int newsType, String content, String userId) {
        this.newsId = newsId;
        this.newsType = newsType;
        this.userId = userId;
        this.content = content;
    }

    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }

    public int getNewsType() {
        return newsType;
    }

    public void setNewsType(int newsType) {
        this.newsType = newsType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
