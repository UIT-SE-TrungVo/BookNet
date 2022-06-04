package com.booknet.api.feed.startup;

import com.booknet.api.feed.repository.PostNewsRepository;
import com.booknet.api.feed.request.create.PostNewsCreateRequest;
import com.booknet.api.feed.service.FeedService;
import com.booknet.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class FeedDataLoader implements ApplicationRunner {
    FeedService feedService;
    PostNewsRepository postNewsRepository;

    @Autowired
    public FeedDataLoader(FeedService service, PostNewsRepository repository) {
        this.feedService = service;
        this.postNewsRepository = repository;
    }

    public void run(ApplicationArguments args) {
        this.postNewsRepository.deleteAll();
//
//        final int NUM_GENERATED_MODEL = 10;
//        for (int i = 0; i < NUM_GENERATED_MODEL; i++) {
//            int number = Utils.math.randomInt(0, 10);
//            String userId = "user" + number;
//            String caption = "caption" + number;
//
//            PostNewsCreateRequest req = new PostNewsCreateRequest(userId, caption);
//            this.feedService.createPostNews(req);
//        }
    }
}
