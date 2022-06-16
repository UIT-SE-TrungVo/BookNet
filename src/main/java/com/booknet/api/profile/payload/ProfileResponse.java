package com.booknet.api.profile.payload;

import com.booknet.api.book.model.BookModel;
import com.booknet.api.guild.model.GuildModel;
import com.booknet.api.profile.model.ProfileModel;
import com.booknet.api.profile.model.ProfileSimplifiedModel;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProfileResponse {
    String _id;

    String urlImage;

    String alias;

    String name;

    Integer gender;

    Date dob;

//    @DocumentReference(lazy = true, collection = "books")
//    List<BookModel> bookShelf = new ArrayList<>();

//    @DocumentReference(lazy = true, collection = "guilds")
//    List<GuildModel> guilds = new ArrayList<>();

    List<ProfileSimplifiedModel> following;

    List<ProfileSimplifiedModel> followers;

    Integer currentPoint;

    Integer highestPoint;

    Long creationDate;

    public ProfileResponse(String _id, String urlImage, String alias, String name, Integer gender, Date dob, List<ProfileSimplifiedModel> following, List<ProfileSimplifiedModel> followers, Integer currentPoint, Integer highestPoint, Long creationDate) {
        this._id = _id;
        this.urlImage = urlImage;
        this.alias = alias;
        this.name = name;
        this.gender = gender;
        this.dob = dob;
        this.following = following;
        this.followers = followers;
        this.currentPoint = currentPoint;
        this.highestPoint = highestPoint;
        this.creationDate = creationDate;
    }

    public String get_id() {
        return _id;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public String getAlias() {
        return alias;
    }

    public String getName() {
        return name;
    }

    public Integer getGender() {
        return gender;
    }

    public Date getDob() {
        return dob;
    }

    public List<ProfileSimplifiedModel> getFollowing() {
        return following;
    }

    public List<ProfileSimplifiedModel> getFollowers() {
        return followers;
    }

    public Integer getCurrentPoint() {
        return currentPoint;
    }

    public Integer getHighestPoint() {
        return highestPoint;
    }

    public Long getCreationDate() {
        return creationDate;
    }
}
