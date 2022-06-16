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

    List<String> members = new ArrayList<>();

//    @DocumentReference(collection = "news_guild", lazy = true)
//    List<GuildNewsModel> news = new ArrayList<>();

    public GuildModel() {
    }

    public GuildModel(String _id, String name, String imageUrl, String description) {
        this._id = _id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.description = description;
        this.members = new ArrayList<>();
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

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<String> getMembers() {
        return members;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }

    //    public List<GuildNewsModel> getNews() {
//        return news;
//    }
//
//    public void setNews(List<GuildNewsModel> news) {
//        this.news = news;
//    }

    public String getImageUrl() {
        return imageUrl;
    }

    public boolean isContainUser(String userId) {
        return this.getMembers().contains(userId);
    }

    public void addMember(String userId) {
        if (!isContainUser(userId)) {
            members.add(userId);
        }
    }

    public void removeMember(String userId) {
        List<String> members = getMembers();
        for (String memberId : members)
            if (Objects.equals(userId, memberId)) {
                members.remove(userId);
            }
        this.setMembers(members);
    }

//    public boolean isContainNews(GuildNewsModel news) {
//        return this.getNews().stream().anyMatch(n -> Objects.equals(n.get_id(), news.get_id()));
//    }

//    public void addNews(GuildNewsModel news) {
//        if (news == null) return;
//
//        List<GuildNewsModel> listNews = this.getNews();
//        if (!this.isContainNews(news)) {
//            listNews.add(news);
//            this.setNews(listNews);
//        }
//    }

//    public void removeNews(GuildNewsModel news) {
//        if (news == null) return;
//
//        List<GuildNewsModel> listNews = this.getNews();
//        if (this.isContainNews(news)) {
//            List<GuildNewsModel> listFiltered = new ArrayList<>();
//            for (GuildNewsModel n : listNews)
//                if (!Objects.equals(n.get_id(), news.get_id())) {
//                    listFiltered.add(n);
//                }
//            this.setNews(listFiltered);
//        }
//    }
}