package com.booknet.api.friend.payload;

public class FollowRequest {
    String fromId;
    String toId;
    boolean toFollow;

    public String getFromId() {
        return fromId;
    }

    public void setFromId(String fromId) {
        this.fromId = fromId;
    }

    public String getToId() {
        return toId;
    }

    public void setToId(String toId) {
        this.toId = toId;
    }

    public Boolean isToFollow() {
        return toFollow;
    }

    public void setToFollow(boolean toFollow) {
        this.toFollow = toFollow;
    }
}