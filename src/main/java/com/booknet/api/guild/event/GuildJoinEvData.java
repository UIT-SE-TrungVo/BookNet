package com.booknet.api.guild.event;

import com.booknet.api.guild.model.GuildModel;

public class GuildJoinEvData {
    String userId;
    GuildModel guildId;

    public GuildJoinEvData(String userId, GuildModel guildModel) {
        this.userId = userId;
        this.guildId = guildModel;
    }

    public String getUserId() {
        return userId;
    }

    public GuildModel getGuildModel() {
        return guildId;
    }
}
