package com.booknet.api.feed.request.create;

import com.booknet.api.feed.request.BaseNewsCreateRequest;

public class PostNewsCreateRequest extends BaseNewsCreateRequest {

    String imageUrl;

    public PostNewsCreateRequest(String userId, String caption, String imageUrl) {
        super(userId, caption);
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
