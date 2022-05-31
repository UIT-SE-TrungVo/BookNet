package com.booknet.api.guild.response;

import com.booknet.api.feed.model.GuildNewsModel;

import java.util.List;

public class GuiViewResponse {

    String id;

    String name;

    String description;

    int numMembers;

    List<GuildNewsModel> news;

    boolean isJoined;

    public GuiViewResponse(
            String id
            , String name
            , String description
            , int numMembers
            , List<GuildNewsModel> news
            , boolean isJoined
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.numMembers = numMembers;
        this.news = news;
        this.isJoined = isJoined;
    }
}