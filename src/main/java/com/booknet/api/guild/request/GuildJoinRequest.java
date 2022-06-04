package com.booknet.api.guild.request;

import com.booknet.api.guild.model.GuildModel;

import javax.validation.constraints.NotEmpty;

public class GuildJoinRequest {

    GuildModel guildId;

    @NotEmpty
    String userId;

    public GuildJoinRequest() {
    }

    public GuildModel getGuildId() {
        return guildId;
    }

    public void setGuildId(GuildModel guildId) {
        this.guildId = guildId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}