package com.booknet.api.feed.request.create;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ReactionCreateRequest {

    @NotEmpty
    String userId;

    @NotEmpty
    String postId;

    @NotNull
    int postType;

    public ReactionCreateRequest(String userId, String postId, int postType) {
        this.userId = userId;
        this.postId = postId;
        this.postType = postType;
    }

    public String getUserId() {
        return userId;
    }

    public String getPostId() {
        return postId;
    }

    public int getPostType() {
        return postType;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
