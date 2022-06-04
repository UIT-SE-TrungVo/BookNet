package com.booknet.api.feed.request.create;

import com.booknet.api.feed.request.BaseNewsCreateRequest;

import java.util.ArrayList;

public class PostNewsCreateRequest extends BaseNewsCreateRequest {

    ArrayList<String> imagesUrl;

    public PostNewsCreateRequest(String userId, String caption, ArrayList<String> imagesUrl) {
        super(userId, caption);
        this.imagesUrl = imagesUrl;
    }

    public ArrayList<String> getImagesUrl() {
        return imagesUrl;
    }

    public void setImagesUrl(ArrayList<String> imagesUrl) {
        this.imagesUrl = imagesUrl;
    }
}
