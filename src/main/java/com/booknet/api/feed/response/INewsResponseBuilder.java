package com.booknet.api.feed.response;

import com.booknet.api.feed.model.CommentModel;
import com.booknet.api.guild.model.GuildSimplifiedModel;

import java.util.ArrayList;
import java.util.LinkedList;

public interface INewsResponseBuilder {
    void setId(String id);
    void setUserId(String userId);
    void setType(int type);
    void setCaption(String caption);
    void setNumberOfLike(int numberOfLikes);
    void setCommentList(LinkedList<CommentModel> commentList);
    void setCreatedDate(long createdDate);
    void setImagesUrl(ArrayList<String> imagesUrl);
    void setReviewId(String reviewId);
    void setGuildSimplified(GuildSimplifiedModel guildSimplified);
}
