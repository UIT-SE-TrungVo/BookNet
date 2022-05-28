package com.booknet.api.feed.model;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;

@Document(collection = "news_review")
public class ReviewNewsModel extends BaseNewsModel {
    @NotEmpty
    String reviewId;

    public ReviewNewsModel(String userId, String reviewId, String caption) {
        super(userId, NewsType.GUILD.getNewsCode(), caption);
        this.reviewId = reviewId;
    }

    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }
}
