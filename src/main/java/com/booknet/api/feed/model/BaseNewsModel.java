package com.booknet.api.feed.model;

import com.booknet.api.profile.model.ProfileSimplifiedModel;
import com.booknet.constants.IdPrefix;
import com.booknet.utils.IdGenerator;
import nonapi.io.github.classgraph.json.Id;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.LinkedList;

public class BaseNewsModel {
    @Id
    protected String _id;

    @NotNull
    protected String userId;

    @NotNull
    protected int type;

    @NotEmpty
    protected String caption;

    protected long createdDate;

    protected LinkedList<CommentModel> commentList;

    protected LinkedList<String> likeUserIdList;

    private BaseNewsModel() {
    }

    BaseNewsModel(String userId, int type, String caption) {
        this._id = IdGenerator.createNew(IdPrefix.NEWS);
        this.userId = userId;
        this.type = type;
        this.caption = caption;
        this.likeUserIdList = new LinkedList<>();
        this.commentList = new LinkedList<>();
        this.createdDate = Instant.now().toEpochMilli();
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public int getNumberOfLikes() {
        return likeUserIdList.size();
    }

    public LinkedList<CommentModel> getCommentList() {
        return commentList;
    }

    public CommentModel addCommentAndGet(String content, ProfileSimplifiedModel profileSimplified) {
        CommentModel comment = new CommentModel(content, profileSimplified);
        commentList.add(comment);
        return comment;
    }

    public ReplyCommentModel addReplyCommentAndGet(String commentId, String content, ProfileSimplifiedModel profileSimplified) {
        for (CommentModel comment: commentList) {
            if (comment.get_id().equals(commentId)) {
                return comment.addReplyCommentAndGet(content, profileSimplified);
            }
        }
        return null;
    }
}
