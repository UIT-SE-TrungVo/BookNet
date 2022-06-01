package com.booknet.api.feed.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "news_post")
public class PostNewsModel extends BaseNewsModel {

    String imageUrl;

    public PostNewsModel(String userId, String caption, String imageUrl) {
        super(userId, NewsType.POST.getNewsCode(), caption);
        this.imageUrl = imageUrl;
    }

}
