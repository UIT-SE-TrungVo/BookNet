package com.booknet.api.feed.response;

import com.booknet.api.feed.model.CommentModel;
import com.booknet.api.guild.model.GuildSimplifiedModel;

import java.util.ArrayList;
import java.util.LinkedList;

public interface INewsResponseBuilder {
    INewsResponseBuilder setId(String id);
    INewsResponseBuilder setUserId(String userId);
    INewsResponseBuilder setType(int type);
    INewsResponseBuilder setCaption(String caption);
    INewsResponseBuilder setUserIdLikeList(LinkedList<String> likeUserIdList);
    INewsResponseBuilder setCommentList(LinkedList<CommentModel> commentList);
    INewsResponseBuilder setCreatedDate(long createdDate);
    INewsResponseBuilder setImagesUrl(ArrayList<String> imagesUrl);
    INewsResponseBuilder setReviewId(String reviewId);
    INewsResponseBuilder setGuildSimplified(GuildSimplifiedModel guildSimplified);
}
