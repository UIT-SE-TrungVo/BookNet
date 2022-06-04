package com.booknet.api.feed.model;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;

@Document(collection = "news_guild")
public class GuildNewsModel extends BaseNewsModel {
    @NotEmpty
    String guildId;

    @NotNull
    ArrayList<String> imagesUrl;

    public GuildNewsModel(String userId, String guildId, String caption, ArrayList<String> imagesUrl) {
        super(userId, NewsType.GUILD.getNewsCode(), caption);
        this.guildId = guildId;
        this.imagesUrl = imagesUrl;
    }

    public String getGuildId() {
        return guildId;
    }

    public void setGuildId(String guildId) {
        this.guildId = guildId;
    }

    public ArrayList<String> getImagesUrl() {
        return imagesUrl;
    }

    public void setImagesUrl(ArrayList<String> imagesUrl) {
        this.imagesUrl = imagesUrl;
    }
}
