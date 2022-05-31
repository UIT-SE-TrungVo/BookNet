package com.booknet.api.guild.request;

import javax.validation.constraints.NotEmpty;

public class GuildJoinRequest {

    @NotEmpty
    String guildId;

    @NotEmpty
    String userId;

    public GuildJoinRequest() {
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