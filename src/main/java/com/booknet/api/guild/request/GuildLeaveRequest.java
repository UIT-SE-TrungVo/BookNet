package com.booknet.api.guild.request;

public class GuildLeaveRequest {
    private String guildId;
    private String userId;

    public GuildLeaveRequest() {
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