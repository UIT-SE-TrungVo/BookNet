package com.booknet.api.feed.service;

import com.booknet.api.feed.model.*;
import com.booknet.api.feed.repository.PostNewsRepository;
import com.booknet.api.feed.request.news.PostNewsCreateRequest;
import com.booknet.api.feed.request.FeedNotifyRequest;
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

    public PostNewsModel createPostNews(PostNewsCreateRequest reqData) {
        String userId = reqData.getUserId();
        String caption = reqData.getCaption();

        PostNewsModel news = new PostNewsModel(userId, caption);

        postNewsRepository.insert(news);

        logger.info("create SampleModel success {}", Utils.json.stringify(news));
        return news;
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