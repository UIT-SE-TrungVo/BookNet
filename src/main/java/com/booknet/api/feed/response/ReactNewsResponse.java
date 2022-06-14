package com.booknet.api.feed.response;

import javax.validation.constraints.NotNull;

public class ReactNewsResponse {
    @NotNull
    int totalLike;

    @NotNull
    boolean isLike;

    public ReactNewsResponse(int totalLike, boolean isLike) {
        this.totalLike = totalLike;
        this.isLike = isLike;
    }

    public int getTotalLike() {
        return totalLike;
    }

    public void setTotalLike(int totalLike) {
        this.totalLike = totalLike;
    }

    public boolean isLike() {
        return isLike;
    }

    public void setLike(boolean like) {
        isLike = like;
    }
}
