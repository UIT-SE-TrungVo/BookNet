package com.booknet.api.feed.model;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;

@Document(collection = "news_post")
public class PostNewsModel extends BaseNewsModel {

    @NotNull
    ArrayList<String> imagesUrl;

    public PostNewsModel(String userId, String caption, ArrayList<String> imagesUrl) {
        super(userId, NewsType.POST.getNewsCode(), caption);
        this.imagesUrl = imagesUrl;
    }

}
