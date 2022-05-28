package com.booknet.api.feed.service;

import com.booknet.api.feed.model.*;
import com.booknet.api.feed.repository.GuildNewsRepository;
import com.booknet.api.feed.repository.PostNewsRepository;
import com.booknet.api.feed.repository.ReviewNewsRepository;
import com.booknet.api.feed.request.create.*;
import com.booknet.api.feed.request.FeedNotifyRequest;
import com.booknet.constants.ErrCode;
import com.booknet.constants.EvId;
import com.booknet.system.EventCenter;
import com.booknet.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;

@Service
public class FeedService {
    private static final Logger logger = LoggerFactory.getLogger(FeedService.class);

    @Autowired
    PostNewsRepository postNewsRepository;

    @Autowired
    GuildNewsRepository guildNewsRepository;

    @Autowired
    ReviewNewsRepository reviewNewsRepository;

    public PostNewsModel createPostNews(PostNewsCreateRequest reqData) {
        String userId = reqData.getUserId();
        String caption = reqData.getCaption();

        PostNewsModel news = new PostNewsModel(userId, caption);

        postNewsRepository.insert(news);

        logger.info("create post news success {}", Utils.json.stringify(news));
        return news;
    }

    public GuildNewsModel createGuildNews(GuildNewsCreateRequest reqData) {
        String userId = reqData.getUserId();
        String caption = reqData.getCaption();
        String guildId = reqData.getGuildId();

        GuildNewsModel news = new GuildNewsModel(userId, guildId, caption);

        guildNewsRepository.insert(news);

        logger.info("create guild news success {}", Utils.json.stringify(news));
        return news;
    }

    public ReviewNewsModel createReviewNews(ReviewNewsCreateRequest reqData) {
        String userId = reqData.getUserId();
        String caption = reqData.getCaption();
        String reviewId = reqData.getReviewId();

        ReviewNewsModel news = new ReviewNewsModel(userId, reviewId, caption);

        reviewNewsRepository.insert(news);

        logger.info("create review news success {}", Utils.json.stringify(news));
        return news;
    }

    public CommentModel createComment(CommentCreateRequest reqData){
        String postId = reqData.getPostId();
        int postType = reqData.getPostType();
        String content = reqData.getContent();

        CommentModel commentModel = null;

        switch (NewsType.fromCode(postType)) {
            case POST:
                PostNewsModel postNewsModel = postNewsRepository.findBy_id(postId).orElse(null);
                if (postNewsModel == null) return null;
                commentModel = postNewsModel.addCommentAndGet(content);
                postNewsRepository.save(postNewsModel);
                break;
            case GUILD:
                GuildNewsModel guildNewsModel = guildNewsRepository.findBy_id(postId).orElse(null);
                if (guildNewsModel == null) return null;
                commentModel = guildNewsModel.addCommentAndGet(content);
                guildNewsRepository.save(guildNewsModel);
                break;
            case REVIEW:
                ReviewNewsModel reviewNewsModel = reviewNewsRepository.findBy_id(postId).orElse(null);
                if (reviewNewsModel == null) return null;
                commentModel = reviewNewsModel.addCommentAndGet(content);
                reviewNewsRepository.save(reviewNewsModel);
                break;
        }

        return commentModel;
    }

    public ReplyCommentModel createReplyComment(ReplyCommentCreateRequest reqData){
        String postId = reqData.getPostId();
        String commentId = reqData.getCommentId();
        int postType = reqData.getPostType();
        String content = reqData.getContent();

        ReplyCommentModel replyCommentModel = null;

        switch (NewsType.fromCode(postType)) {
            case POST:
                PostNewsModel postNewsModel = postNewsRepository.findBy_id(postId).orElse(null);
                if (postNewsModel == null) return null;
                replyCommentModel = postNewsModel.addReplyCommentAndGet(commentId, content);
                postNewsRepository.save(postNewsModel);
                break;
            case GUILD:
                GuildNewsModel guildNewsModel = guildNewsRepository.findBy_id(postId).orElse(null);
                if (guildNewsModel == null) return null;
                replyCommentModel = guildNewsModel.addReplyCommentAndGet(commentId, content);
                guildNewsRepository.save(guildNewsModel);
                break;
            case REVIEW:
                ReviewNewsModel reviewNewsModel = reviewNewsRepository.findBy_id(postId).orElse(null);
                if (reviewNewsModel == null) return null;
                replyCommentModel = reviewNewsModel.addReplyCommentAndGet(commentId, content);
                reviewNewsRepository.save(reviewNewsModel);
                break;
        }

        return replyCommentModel;
    }

    public Collection<BaseNewsModel> getUserFeed(String userId) {
//        ArrayList newsCollection = (ArrayList) postNewsRepository.findBaseNewsByUserId(userId);

        ArrayList<BaseNewsModel> results = new ArrayList<>();

//        newsCollection.forEach(result -> {
//            int typeInt = ((BaseNews)result).getType();
//            NewsType typeEnum = NewsType.fromCode(typeInt);
//            switch (Objects.requireNonNull(typeEnum)) {
//                case POST:
//                    results.add((PostNews)result);
//                    break;
//                case GUILD:
//                    results.add((GuildNews)result);
//                    break;
//                case REVIEW:
//                    results.add((ReviewNews)result);
//                    break;
//                default:
//                    break;
//            }
//        });

        logger.info("get all post for user {}", Utils.json.stringify(results));
        return results;
    }

    public BaseNewsModel getSample(String id) {
        BaseNewsModel sample = postNewsRepository.findBy_id(id).orElse(null);
        logger.info("get SampleModel with id {} {}", id, Utils.json.stringify(sample));
        return sample;
    }


    public void doNotify() {
        EventCenter.pub(EvId.SAMPLE_EVENT);
    }

    public void doNotifyWithArgument(@NotNull FeedNotifyRequest req) {
        EventCenter.pub(EvId.SAMPLE_EVENT_WITH_ARGS, req.getNumber());
    }

    public void onNotified() {
        logger.info("has been notified");
    }

    public void onNotifiedWithArgument(Integer number) {
        logger.info("has been notified with argument {}", number);
    }
}