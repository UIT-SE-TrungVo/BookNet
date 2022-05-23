package com.booknet.api.feed.model;

import com.booknet.constants.IdPrefix;
import com.booknet.utils.IdGenerator;

import java.util.LinkedList;

public class CommentModel {

    private String _id;

    private String content;

    private LinkedList<ReplyCommentModel> replyList;

    private String createdDate;

    public CommentModel(String content) {
        this._id = IdGenerator.createNew(IdPrefix.COMMENT);
        this.content = content;
        this.replyList = new LinkedList<>();
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

    public LinkedList<ReplyCommentModel> getReplyList() {
        return replyList;
    }

    public void setReplyList(LinkedList<ReplyCommentModel> replyList) {
        this.replyList = replyList;
    }

    public void addReplyComment(String content) {
        ReplyCommentModel replyComment = new ReplyCommentModel(content);
        replyList.add(replyComment);
    }
}
