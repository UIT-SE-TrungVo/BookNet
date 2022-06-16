package com.booknet.api.feed.response;

import com.booknet.api.feed.model.BaseNewsModel;
import com.booknet.api.feed.model.CommentModel;
import com.booknet.api.guild.model.GuildSimplifiedModel;

import java.util.ArrayList;
import java.util.LinkedList;

public class NewsResponseBuilder implements INewsResponseBuilder{

    private String id;

    private String userId;

    private int type;

    private String caption;

    private LinkedList<String> likeUserIdList;

    private LinkedList<CommentModel> commentList;

    protected long createdDate;

    //region post
    ArrayList<String> imagesUrl;

    //region review
    String reviewId;

    //region guild
    GuildSimplifiedModel guildSimplified;

    @Override
    public INewsResponseBuilder setId(String id) {
        this.id = id;
        return this;
    }

    @Override
    public INewsResponseBuilder setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    @Override
    public INewsResponseBuilder setType(int type) {
        this.type = type;
        return this;
    }

    @Override
    public INewsResponseBuilder setCaption(String caption) {
        this.caption = caption;
        return this;
    }

    @Override
    public INewsResponseBuilder setUserIdLikeList(LinkedList<String> likeUserIdList) {
        this.likeUserIdList = likeUserIdList;
        return this;
    }

    @Override
    public INewsResponseBuilder setCommentList(LinkedList<CommentModel> commentList) {
        this.commentList = commentList;
        return this;
    }

    @Override
    public INewsResponseBuilder setCreatedDate(long createdDate) {
        this.createdDate = createdDate;
        return this;
    }

    @Override
    public INewsResponseBuilder setImagesUrl(ArrayList<String> imagesUrl) {
        this.imagesUrl = imagesUrl;
        return this;
    }

    @Override
    public INewsResponseBuilder setReviewId(String reviewId) {
        this.reviewId = reviewId;
        return this;
    }

    @Override
    public INewsResponseBuilder setGuildSimplified(GuildSimplifiedModel guildSimplified) {
        this.guildSimplified = guildSimplified;
        return this;
    }

    public NewsResponse build() {
        return new NewsResponse(id, userId, type, caption, likeUserIdList, commentList, createdDate, imagesUrl, reviewId, guildSimplified);
    }
}
