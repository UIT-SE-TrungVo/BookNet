package com.booknet.api.feed.request.create;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ReactionCreateRequest {

    @NotEmpty
    String userId;

    @NotEmpty
    String postId;

    @NotNull
    boolean isLike;

    public ReactionCreateRequest(String userId, String postId, boolean isLike) {
        this.userId = userId;
        this.postId = postId;
        this.isLike = isLike;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public boolean isLike() {
        return isLike;
    }

    public void setLike(boolean like) {
        isLike = like;
    }
}
