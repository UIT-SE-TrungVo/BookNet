package com.booknet.api.guild.request;

import javax.validation.constraints.NotEmpty;

public class GuildViewRequest {

    @NotEmpty
    String guildId;

    @NotEmpty
    String userId;

    public GuildViewRequest() {
    }

    public String getGuildId() {
        return guildId;
    }

    public void setGuildId(String guildId) {
        this.guildId = guildId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}