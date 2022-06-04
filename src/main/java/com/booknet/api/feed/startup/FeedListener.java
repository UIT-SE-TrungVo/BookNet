package com.booknet.api.feed.startup;

import com.booknet.api.feed.service.FeedService;
import com.booknet.constants.EvId;
import com.booknet.system.EventCenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class FeedListener implements ApplicationRunner {
    FeedService feedService;

    @Autowired
    public FeedListener(FeedService service) {
        this.feedService = service;
    }

    public void run(ApplicationArguments args) {
        EventCenter.sub(EvId.SAMPLE_EVENT, data -> onNotified());
        EventCenter.sub(EvId.SAMPLE_EVENT_WITH_ARGS, this::onNotifiedWithArguments);
    }

    void onNotified() {
        feedService.onNotified();
    }

    void onNotifiedWithArguments(Object argument) {
        Integer number = (Integer)argument;
        feedService.onNotifiedWithArgument(number);
    }
}
