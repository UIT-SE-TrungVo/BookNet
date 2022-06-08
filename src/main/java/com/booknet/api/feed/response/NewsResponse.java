package com.booknet.api.feed.response;

import com.booknet.api.feed.model.CommentModel;
import com.booknet.api.guild.model.GuildSimplifiedModel;
import nonapi.io.github.classgraph.json.Id;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.LinkedList;

public class NewsResponse {
    @Id
    private String _id;

    @NotNull
    private String userId;

    @NotNull
    private int type;

    @NotEmpty
    private String caption;

    @NotEmpty
    private int numberOfLikes;

    private LinkedList<CommentModel> commentList;

    @NotNull
    ArrayList<String> imagesUrl;

    @NotEmpty
    String reviewId;

    GuildSimplifiedModel guildSimplified;

    protected long createdDate;
}
