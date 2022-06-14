package com.booknet.api.feed.service;

import com.booknet.api.feed.model.*;
import com.booknet.api.feed.repository.GuildNewsRepository;
import com.booknet.api.feed.repository.PostNewsRepository;
import com.booknet.api.feed.repository.ReviewNewsRepository;
import com.booknet.api.feed.request.create.*;
import com.booknet.api.feed.request.FeedNotifyRequest;
import com.booknet.api.feed.response.ReactNewsResponse;
import com.booknet.api.profile.model.ProfileSimplifiedModel;
import com.booknet.api.profile.repository.ProfileRepository;
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

    @Autowired
    ProfileRepository profileRepository;

    public PostNewsModel createPostNews(PostNewsCreateRequest reqData) {
        String userId = reqData.getUserId();
        String caption = reqData.getCaption();
        ArrayList<String> imageUrl = reqData.getImagesUrl();

        PostNewsModel news = new PostNewsModel(userId, caption, imageUrl);

        postNewsRepository.insert(news);

        logger.info("create post news success {}", Utils.json.stringify(news));
        return news;
    }

    public GuildNewsModel createGuildNews(GuildNewsCreateRequest reqData) {
        String userId = reqData.getUserId();
        String caption = reqData.getCaption();
        String guildId = reqData.getGuildId();
        ArrayList<String> imagesUrl = reqData.getImagesUrl();

        GuildNewsModel news = new GuildNewsModel(userId, guildId, caption, imagesUrl);

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
        String newsId = reqData.getNewsId();
        int newsType = reqData.getNewsType();
        String content = reqData.getContent();
        String userId = reqData.getUserId();

        ProfileSimplifiedModel profileSimplified = ProfileSimplifiedModel.getSimplified(profileRepository.findBy_id(userId).get());

        CommentModel commentModel = null;

        BaseNewsModel newsModel = getNewsFromDatabase(newsId, newsType);
        commentModel = newsModel.addCommentAndGet(content, profileSimplified);
        saveNewsToDatabase(newsModel, newsType);

        return commentModel;
    }

    public ReplyCommentModel createReplyComment(ReplyCommentCreateRequest reqData){
        String newsId = reqData.getNewsId();
        String commentId = reqData.getCommentId();
        int newsType = reqData.getNewsType();
        String content = reqData.getContent();
        String userId = reqData.getUserId();

        ReplyCommentModel replyCommentModel = null;
        ProfileSimplifiedModel profileSimplified = ProfileSimplifiedModel.getSimplified(profileRepository.findBy_id(userId).get());

        BaseNewsModel newsModel = getNewsFromDatabase(newsId, newsType);
        replyCommentModel = newsModel.addReplyCommentAndGet(commentId, content, profileSimplified);

        saveNewsToDatabase(newsModel, newsType);

        return replyCommentModel;
    }

    public ReactNewsResponse reactWithPost(ReactionCreateRequest reqData) {
        String userId = reqData.getUserId();
        String postId = reqData.getPostId();
        int postType = reqData.getPostType();

        ReactNewsResponse response = null;



        return response;
    }

    public BaseNewsModel getNewsFromDatabase(String newsId, int newsType) {
        BaseNewsModel model = null;

        switch (NewsType.fromCode(newsType)) {
            case POST:
                model = postNewsRepository.findBy_id(newsId).orElse(null);
                break;
            case GUILD:
                model = guildNewsRepository.findBy_id(newsId).orElse(null);
                break;
            case REVIEW:
                model = reviewNewsRepository.findBy_id(newsId).orElse(null);
                break;
        }

        return model;
    }

    public void saveNewsToDatabase(BaseNewsModel newsModel, int newsType) {
        switch (NewsType.fromCode(newsType)) {
            case POST:
                postNewsRepository.save((PostNewsModel) newsModel);
                break;
            case GUILD:
                guildNewsRepository.save((GuildNewsModel) newsModel);
                break;
            case REVIEW:
                reviewNewsRepository.save((ReviewNewsModel) newsModel);
                break;
        }
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