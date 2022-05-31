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
}