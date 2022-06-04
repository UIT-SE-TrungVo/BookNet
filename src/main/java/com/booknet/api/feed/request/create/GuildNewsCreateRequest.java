package com.booknet.api.feed.request.create;

import com.booknet.api.feed.request.BaseNewsCreateRequest;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;

public class GuildNewsCreateRequest extends BaseNewsCreateRequest {

    @NotNull
    String guildId;

    @NotNull
    ArrayList<String> imagesUrl;

    public GuildNewsCreateRequest(String userId, String caption, String guildId, ArrayList<String> imagesUrl) {
        super(userId, caption);
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
