package com.booknet.api.feed.request.create;

import com.booknet.api.feed.request.BaseNewsCreateRequest;

import javax.validation.constraints.NotNull;

public class ReviewNewsCreateRequest extends BaseNewsCreateRequest {

    @NotNull
    String reviewId;

    public ReviewNewsCreateRequest(String userId, String caption, String reviewId) {
        super(userId, caption);
        this.reviewId = reviewId;
    }

    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }
}
