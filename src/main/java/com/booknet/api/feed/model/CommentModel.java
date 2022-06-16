package com.booknet.api.feed.model;

import com.booknet.api.profile.model.ProfileSimplifiedModel;
import com.booknet.constants.IdPrefix;
import com.booknet.utils.IdGenerator;

import java.time.Instant;
import java.util.LinkedList;

public class CommentModel {

    private String id;

    private ProfileSimplifiedModel profileSimplified;

    private String content;

    private LinkedList<ReplyCommentModel> replyList;

    private long createdDate;

    public CommentModel(String content, ProfileSimplifiedModel profileSimplified) {
        this.id = IdGenerator.createNew(IdPrefix.COMMENT);
        this.content = content;
        this.replyList = new LinkedList<>();
        this.profileSimplified = profileSimplified;
        this.createdDate = Instant.now().toEpochMilli();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(long createdDate) {
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

    public ReplyCommentModel addReplyCommentAndGet(String content, ProfileSimplifiedModel profileSimplified) {
        ReplyCommentModel replyComment = new ReplyCommentModel(content, profileSimplified);
        replyList.add(replyComment);
        return replyComment;
    }
}
