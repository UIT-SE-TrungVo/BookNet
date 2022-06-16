package com.booknet.api.profile.model;

import com.booknet.api.account.authentication.model.AppUser;
import com.booknet.api.book.model.BookModel;
import com.booknet.api.guild.model.GuildModel;
import com.booknet.api.profile.config.ProfileDefaultConfig;
import com.booknet.utils.Utils;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Document(collection = "profiles")
public class ProfileModel {
    @Id
    String _id;

    String urlImage;

    String username;

    String name;

    Integer gender;

    Date dob;

    @DocumentReference(lazy = true, collection = "books")
    List<BookModel> bookShelf = new ArrayList<>();

    @DocumentReference(lazy = true, collection = "guilds")
    List<GuildModel> guilds = new ArrayList<>();

    @DocumentReference(lazy = true, collection = "users")
    List<ProfileModel> following = new ArrayList<>();

    @DocumentReference(lazy = true, collection = "users")
    List<ProfileModel> followers = new ArrayList<>();

    Integer currentPoint = 0;

    Integer highestPoint = 0;

    Long creationDate;

    public ProfileModel() {
    }

    public ProfileModel(@NotNull AppUser appUser) {
        this._id = appUser.get_id();
        this.username = appUser.getUsername();
        this.name = appUser.getName();

        this.dob = ProfileDefaultConfig.DOB;
        this.gender = ProfileDefaultConfig.GENDER;

        following = new LinkedList<>();
        followers = new LinkedList<>();

        this.creationDate = Utils.time.getCurrentTimestamp();
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public List<BookModel> getBookShelf() {
        return bookShelf;
    }

    public void setBookShelf(List<BookModel> bookShelf) {
        this.bookShelf = bookShelf;
    }

    public List<ProfileModel> getFollowing() {
        return following;
    }

    public List<ProfileSimplifiedModel> getFollowingSimplified() {
        var followingSimplifiedList = new LinkedList<ProfileSimplifiedModel>();
        getFollowing().forEach(profileModel -> {
            followingSimplifiedList.add(ProfileSimplifiedModel.getSimplified(profileModel));
        });
        return followingSimplifiedList;
    }

    public List<ProfileSimplifiedModel> getFollowersSimplified() {
        var followersSimplifiedList = new LinkedList<ProfileSimplifiedModel>();
        getFollowers().forEach(profileModel -> {
            followersSimplifiedList.add(ProfileSimplifiedModel.getSimplified(profileModel));
        });
        return followersSimplifiedList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<GuildModel> getGuilds() {
        return guilds;
    }

    public void setGuilds(List<GuildModel> guilds) {
        this.guilds = guilds;
    }

    public List<ProfileModel> getFollowers() {
        return followers;
    }

    public void setFollowing(List<ProfileModel> following) {
        this.following = following;
    }

    public void setFollowers(List<ProfileModel> followers) {
        this.followers = followers;
    }

    public List<GuildModel> getListGuild() {
        return guilds;
    }

    public void setListGuild(List<GuildModel> guilds) {
        this.guilds = guilds;
    }

    public Integer getCurrentPoint() {
        return currentPoint;
    }

    public void setCurrentPoint(Integer currentPoint) {
        this.currentPoint = currentPoint;
    }

    public Integer getHighestPoint() {
        return highestPoint;
    }

    public void setHighestPoint(Integer highestPoint) {
        this.highestPoint = highestPoint;
    }

    public Long getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Long creationDate) {
        this.creationDate = creationDate;
    }
}