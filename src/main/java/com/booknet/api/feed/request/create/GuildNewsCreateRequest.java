package com.booknet.api.feed.request.create;

import com.booknet.api.feed.request.BaseNewsCreateRequest;

import javax.validation.constraints.NotNull;

public class GuildNewsCreateRequest extends BaseNewsCreateRequest {

    @NotNull
    String guildId;

    public GuildNewsCreateRequest(String userId, String caption, String guildId) {
        super(userId, caption);
        this.guildId = guildId;
    }

    public String getGuildId() {
        return guildId;
    }

    public void setGuildId(String guildId) {
        this.guildId = guildId;
    }
}
