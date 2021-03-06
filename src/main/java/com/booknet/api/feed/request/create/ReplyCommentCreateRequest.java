package com.booknet.api.feed.request.create;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ReplyCommentCreateRequest extends CommentCreateRequest{

    @NotEmpty
    String commentId;

    public ReplyCommentCreateRequest(String postId, int postType, String content, String commentId, String userId) {
        super(postId, postType, content, userId);
        this.commentId = commentId;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }
}
