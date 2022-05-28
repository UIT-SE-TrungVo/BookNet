package com.booknet.api.feed.request.create;

import com.booknet.api.feed.request.BaseNewsCreateRequest;

public class PostNewsCreateRequest extends BaseNewsCreateRequest {

    public PostNewsCreateRequest(String userId, String caption) {
        super(userId, caption);
    }

}
