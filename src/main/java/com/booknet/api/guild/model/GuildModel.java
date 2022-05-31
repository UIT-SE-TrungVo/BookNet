package com.booknet.api.guild.model;

import com.booknet.api.account.authentication.model.AppUser;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import java.util.concurrent.CopyOnWriteArrayList;

@Document(collection = "guilds")
public class GuildModel {

    @Id
    String _id;

    @NotEmpty
    String name;

    @DBRef
    CopyOnWriteArrayList<AppUser> members;

}