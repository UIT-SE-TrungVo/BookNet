package com.booknet.api.feed.response;

import com.booknet.api.feed.model.CommentModel;
import nonapi.io.github.classgraph.json.Id;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.LinkedList;

public class NewsResponse {
    @Id
    String _id;

    @NotNull
    String userId;

    @NotNull
    int type;

    @NotEmpty
    String caption;

    @NotEmpty
    int numberOfLikes;

    long createdDate;

    String guildId;

    protected LinkedList<CommentModel> commentList;
}
