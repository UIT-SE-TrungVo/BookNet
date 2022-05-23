package com.booknet.api.feed.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "news_post")
public class PostNewsModel extends BaseNewsModel {

    public PostNewsModel(String userId, String caption) {
        super(userId, NewsType.POST.getNewsCode(), caption);
    }

}
