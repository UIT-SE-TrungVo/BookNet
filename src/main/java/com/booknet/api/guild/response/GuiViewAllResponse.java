package com.booknet.api.guild.response;

import java.util.List;

public class GuiViewAllResponse {

    List<String> guildIds;

    List<String> guildNames;

    List<String> guildDescription;

    public GuiViewAllResponse(List<String> guildIds, List<String> guildNames, List<String> guildDescription) {
        this.guildIds = guildIds;
        this.guildNames = guildNames;
        this.guildDescription = guildDescription;
    }

    public List<String> getGuildIds() {
        return guildIds;
    }

    public void setGuildIds(List<String> guildIds) {
        this.guildIds = guildIds;
    }

    public List<String> getGuildNames() {
        return guildNames;
    }

    public void setGuildNames(List<String> guildNames) {
        this.guildNames = guildNames;
    }

    public List<String> getGuildDescription() {
        return guildDescription;
    }

    public void setGuildDescription(List<String> guildDescription) {
        this.guildDescription = guildDescription;
    }
}