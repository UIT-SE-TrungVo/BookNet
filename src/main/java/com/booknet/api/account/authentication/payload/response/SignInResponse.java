package com.booknet.api.account.authentication.payload.response;

import com.booknet.api.book.model.BookModel;
import com.booknet.api.guild.model.GuildSimplifiedModel;
import com.booknet.api.profile.model.ProfileSimplifiedModel;

import java.util.Date;
import java.util.List;

public class SignInResponse {
    private String token;
    private String id;
    private String username;
    private String email;
    private List<String> roles;
    private String urlImage;
    private String name;
    private Integer gender;
    private Date dob;
    private List<BookModel> bookShelf;
    private List<GuildSimplifiedModel> guilds ;
    private List<ProfileSimplifiedModel> friend;
    private Integer currentPoint;
    private Integer highestPoint;
    private Long creationDate;

    public SignInResponse() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
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

    public List<GuildSimplifiedModel> getGuilds() {
        return guilds;
    }

    public void setGuilds(List<GuildSimplifiedModel> guilds) {
        this.guilds = guilds;
    }

    public List<ProfileSimplifiedModel> getFriend() {
        return friend;
    }

    public void setFriend(List<ProfileSimplifiedModel> friend) {
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
}