package com.booknet.api.feed.response;

import com.booknet.api.feed.model.CommentModel;
import com.booknet.api.guild.model.GuildSimplifiedModel;

import java.util.ArrayList;
import java.util.LinkedList;

public class NewsResponseBuilder implements INewsResponseBuilder{

    private String id;

    private String userId;

    private int type;

    private String caption;

    private int numberOfLikes;

    private LinkedList<CommentModel> commentList;

    protected long createdDate;

    //region post
    ArrayList<String> imagesUrl;

    //region review
    String reviewId;

    //region guild
    GuildSimplifiedModel guildSimplified;

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public void setType(int type) {
        this.type = type;
    }

    @Override
    public void setCaption(String caption) {
        this.caption = caption;
    }

    @Override
    public void setNumberOfLike(int numberOfLikes) {
        this.numberOfLikes = numberOfLikes;
    }

    @Override
    public void setCommentList(LinkedList<CommentModel> commentList) {
        this.commentList = commentList;
    }

    @Override
    public void setCreatedDate(long createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public void setImagesUrl(ArrayList<String> imagesUrl) {
        this.imagesUrl = imagesUrl;
    }

    @Override
    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    @Override
    public void setGuildSimplified(GuildSimplifiedModel guildSimplified) {
        this.guildSimplified = guildSimplified;
    }

    public NewsResponse build() {
        return new NewsResponse(id, userId, type,caption,numberOfLikes, commentList,createdDate, imagesUrl, reviewId, guildSimplified);
    }
}
