package com.booknet.api.feed.service;

import com.booknet.api.feed.model.*;
import com.booknet.api.feed.repository.GuildNewsRepository;
import com.booknet.api.feed.repository.PostNewsRepository;
import com.booknet.api.feed.repository.ReviewNewsRepository;
import com.booknet.api.feed.request.create.*;
import com.booknet.api.feed.request.FeedNotifyRequest;
import com.booknet.api.feed.response.NewsResponse;
import com.booknet.api.feed.response.NewsResponseBuilder;
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
import java.util.LinkedList;
import java.util.List;

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
        saveNewsToDatabase(newsModel);

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

        saveNewsToDatabase(newsModel);

        return replyCommentModel;
    }

    public List<String> reactWithPost(ReactionCreateRequest reqData) {
        String userId = reqData.getUserId();
        String newsId = reqData.getNewsId();
        int newsType = reqData.getNewsType();

        BaseNewsModel newsModel = getNewsFromDatabase(newsId, newsType);

        if (newsModel.containUserInLikeList(userId)) {
            newsModel.removeUserFromLikeList(userId);
        } else  {
            newsModel.addUserToLikeList(userId);
        }

        saveNewsToDatabase(newsModel);

        return newsModel.getLikeUserIdList();
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

    public void saveNewsToDatabase(BaseNewsModel newsModel) {
        switch (NewsType.fromCode(newsModel.getType())) {
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

    public Collection<NewsResponse> getUserFeed(String userId) {

        LinkedList<BaseNewsModel> newsList = new LinkedList<>();
        LinkedList<NewsResponse> results = new LinkedList<>();

        newsList.addAll(getPostNewsToFeed(userId));
        newsList.addAll(getGuildNewsToFeed(userId));
        newsList.addAll(getReviewNewsToFeed(userId));

        newsList.forEach(news -> {
            results.add(mapToNewsResponse(news));
        });

        return results;
    }

    public LinkedList<BaseNewsModel> getPostNewsToFeed(String userId) {
        LinkedList<BaseNewsModel> results = new LinkedList<>();

        var userProfile = profileRepository.findBy_id(userId).get();
        userProfile.getFollowing().forEach(followingProfile -> {
            results.addAll(postNewsRepository.findPostNewsModelByUserId(followingProfile.get_id()));
        });

        return results;
    }

    public LinkedList<BaseNewsModel> getGuildNewsToFeed(String userId) {
        LinkedList<BaseNewsModel> results = new LinkedList<>();

        return  results;
    }

    public LinkedList<BaseNewsModel> getReviewNewsToFeed(String userId) {
        LinkedList<BaseNewsModel> results = new LinkedList<>();

        return  results;
    }

    public NewsResponse mapToNewsResponse(BaseNewsModel news) {
        NewsResponseBuilder builder = new NewsResponseBuilder();
        builder.setId(news.get_id())
                .setUserId(news.getUserId())
                .setType(news.getType())
                .setCaption(news.getCaption())
                .setUserIdLikeList(news.getLikeUserIdList())
                .setCreatedDate(news.getCreatedDate())
                .setCommentList(news.getCommentList());
        switch (NewsType.fromCode(news.getType())) {
            case POST:
                PostNewsModel postNews = (PostNewsModel) news;
                builder.setImagesUrl(postNews.getImagesUrl());
                break;
            case GUILD:

                break;
            case REVIEW:

                break;
        }

        return builder.build();
    }
}