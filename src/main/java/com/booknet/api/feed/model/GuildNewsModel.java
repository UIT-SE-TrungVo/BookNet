package com.booknet.api.feed.model;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;

@Document(collection = "news_guild")
public class GuildNewsModel extends BaseNewsModel {
    @NotEmpty
    String guildId;

    public GuildNewsModel(String userId, String guildId, String caption) {
        super(userId, NewsType.GUILD.getNewsCode(), caption);
        this.guildId = guildId;
    }

    public String getGuildId() {
        return guildId;
    }

    public void setGuildId(String guildId) {
        this.guildId = guildId;
    }
}
