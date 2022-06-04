package com.booknet.api.feed.model;

import com.booknet.constants.IdPrefix;
import com.booknet.utils.IdGenerator;

public class ReplyCommentModel {
    private String _id;

    private String content;

    private String createdDate;

    public ReplyCommentModel(String content) {
        this._id = IdGenerator.createNew(IdPrefix.REPLY_COMMENT);
        this.content = content;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
