package com.booknet.api.guild.response;

import com.booknet.api.feed.model.GuildNewsModel;
import com.booknet.api.guild.model.GuildModel;

import java.util.List;

public class GuiViewResponse {

    GuildModel id;

    String name;

    String description;

    int numMembers;

    List<GuildNewsModel> news;

    boolean isJoined;

    public GuiViewResponse(
            GuildModel id
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

    public GuildModel getId() {
        return id;
    }

    public void setId(GuildModel id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumMembers() {
        return numMembers;
    }

    public void setNumMembers(int numMembers) {
        this.numMembers = numMembers;
    }

    public List<GuildNewsModel> getNews() {
        return news;
    }

    public void setNews(List<GuildNewsModel> news) {
        this.news = news;
    }

    public boolean isJoined() {
        return isJoined;
    }

    public void setJoined(boolean joined) {
        isJoined = joined;
    }
}