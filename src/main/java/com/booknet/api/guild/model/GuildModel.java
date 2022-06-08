package com.booknet.api.guild.model;

import com.booknet.api.account.authentication.model.AppUser;
import com.booknet.api.feed.model.GuildNewsModel;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Document(collection = "guilds")
public class GuildModel {

    @Id
    String _id;

    @NotEmpty
    @Size(max = 20)
    String name;

    @NotEmpty
    String imageUrl;

    @NotNull
    @Size(max = 120)
    String description;

    @DocumentReference(collection = "users", lazy = true)
    List<AppUser> members = new ArrayList<>();

    @DocumentReference(collection = "news_guild", lazy = true)
    List<GuildNewsModel> news = new ArrayList<>();

    public GuildModel() {
    }

    public GuildModel(String _id, String name, String imageUrl) {
        this._id = _id;
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<AppUser> getMembers() {
        return members;
    }

    public void setMembers(List<AppUser> members) {
        this.members = members;
    }

    public List<GuildNewsModel> getNews() {
        return news;
    }

    public void setNews(List<GuildNewsModel> news) {
        this.news = news;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public boolean isContainUser(AppUser user) {
        return this.getMembers().stream().anyMatch(member -> Objects.equals(member.get_id(), user.get_id()));
    }

    public void addMember(AppUser user) {
        if (user == null) return;

        List<AppUser> members = this.getMembers();
        if (!members.contains(user)) {
            members.add(user);
        }
    }

    public void removeMember(AppUser user) {
        if (user == null) return;

        List<AppUser> members = this.getMembers();
        List<AppUser> listFiltered = new ArrayList<>();
        for (AppUser member : members)
            if (!Objects.equals(member.get_id(), user.get_id())) {
                listFiltered.add(member);
            }
        this.setMembers(listFiltered);
    }

    public boolean isContainNews(GuildNewsModel news) {
        return this.getNews().stream().anyMatch(n -> Objects.equals(n.get_id(), news.get_id()));
    }

    public void addNews(GuildNewsModel news) {
        if (news == null) return;

        List<GuildNewsModel> listNews = this.getNews();
        if (!this.isContainNews(news)) {
            listNews.add(news);
            this.setNews(listNews);
        }
    }

    public void removeNews(GuildNewsModel news) {
        if (news == null) return;

        List<GuildNewsModel> listNews = this.getNews();
        if (this.isContainNews(news)) {
            List<GuildNewsModel> listFiltered = new ArrayList<>();
            for (GuildNewsModel n : listNews)
                if (!Objects.equals(n.get_id(), news.get_id())) {
                    listFiltered.add(n);
                }
            this.setNews(listFiltered);
        }
    }
}