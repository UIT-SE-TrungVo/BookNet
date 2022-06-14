package com.booknet.api.feed.response;

import com.booknet.api.feed.model.CommentModel;
import com.booknet.api.guild.model.GuildSimplifiedModel;
import java.util.ArrayList;
import java.util.LinkedList;

public class NewsResponse {
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

    public NewsResponse(String _id, String userId, int type, String caption, int numberOfLikes, LinkedList<CommentModel> commentList, long createdDate, ArrayList<String> imagesUrl, String reviewId, GuildSimplifiedModel guildSimplified) {
        this.id = _id;
        this.userId = userId;
        this.type = type;
        this.caption = caption;
        this.numberOfLikes = numberOfLikes;
        this.commentList = commentList;
        this.createdDate = createdDate;
        this.imagesUrl = imagesUrl;
        this.reviewId = reviewId;
        this.guildSimplified = guildSimplified;
    }

    public String getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public int getType() {
        return type;
    }

    public String getCaption() {
        return caption;
    }

    public int getNumberOfLikes() {
        return numberOfLikes;
    }

    public LinkedList<CommentModel> getCommentList() {
        return commentList;
    }

    public long getCreatedDate() {
        return createdDate;
    }

    public ArrayList<String> getImagesUrl() {
        return imagesUrl;
    }

    public String getReviewId() {
        return reviewId;
    }

    public GuildSimplifiedModel getGuildSimplified() {
        return guildSimplified;
    }
}
