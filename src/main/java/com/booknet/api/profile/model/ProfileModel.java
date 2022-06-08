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
import java.util.List;

@Document(collection = "profiles")
public class ProfileModel {
    @Id
    String _id;

    String urlImage;

    String name;

    String alias;

    Integer gender;

    Date dob;

    @DocumentReference(lazy = true, collection = "books")
    List<BookModel> bookShelf = new ArrayList<>();

    @DocumentReference(lazy = true, collection = "guilds")
    List<GuildModel> guilds = new ArrayList<>();

    @DocumentReference(lazy = true, collection = "users")
    List<ProfileModel> friend = new ArrayList<>();

    Integer currentPoint = 0;

    Integer highestPoint = 0;

    Long creationDate;

    public ProfileModel() {
    }

    public ProfileModel(@NotNull AppUser appUser, String alias) {
        this._id = appUser.get_id();
        this.name = appUser.getUsername();
        this.alias = alias;

        this.dob = ProfileDefaultConfig.DOB;
        this.gender = ProfileDefaultConfig.GENDER;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<GuildModel> getListGuild() {
        return guilds;
    }

    public void setListGuild(List<GuildModel> guilds) {
        this.guilds = guilds;
    }

    public List<ProfileModel> getListFriend() {
        return friend;
    }

    public void setListFriend(List<ProfileModel> friend) {
        this.friend = friend;
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

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}