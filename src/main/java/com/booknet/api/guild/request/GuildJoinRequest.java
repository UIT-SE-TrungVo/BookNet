package com.booknet.api.guild.request;

public class GuildJoinRequest {
    private String guildId;
    private String userId;

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