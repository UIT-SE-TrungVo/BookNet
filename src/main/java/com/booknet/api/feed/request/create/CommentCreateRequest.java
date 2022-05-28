package com.booknet.api.feed.request.create;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CommentCreateRequest {

    @NotNull
    String postId;

    @NotNull
    int postType;

    @NotEmpty
    String content;

    public CommentCreateRequest(String postId, int postType, String content) {
        this.postId = postId;
        this.postType = postType;
        this.content = content;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public int getPostType() {
        return postType;
    }

    public void setPostType(int postType) {
        this.postType = postType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
