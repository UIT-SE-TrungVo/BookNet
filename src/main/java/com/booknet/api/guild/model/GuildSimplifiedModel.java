package com.booknet.api.guild.model;

import com.booknet.api.account.authentication.model.AppUser;
import com.booknet.api.feed.model.GuildNewsModel;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GuildSimplifiedModel {
    String _id;
    String name;
    String description;
    String imageUrl;

    public GuildSimplifiedModel(String _id, String name, String description, String imageUrl) {
        this._id = _id;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public String get_id() {
        return _id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public static GuildSimplifiedModel getSimplified(GuildModel guildModel) {
        return new GuildSimplifiedModel(
                guildModel.get_id()
                , guildModel.getName()
                , guildModel.getDescription()
                , guildModel.getImageUrl()
        );
    }
}