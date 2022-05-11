package com.booknet.api.post.startup;

import com.booknet.api.book.service.PostService;
import com.booknet.constants.EvId;
import com.booknet.system.EventCenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class PostListener implements ApplicationRunner {
    PostService postService;

    @Autowired
    public PostListener(PostService service) {
        this.postService = service;
    }

    public void run(ApplicationArguments args) {
        EventCenter.sub(EvId.SAMPLE_EVENT, data -> onNotified());
        EventCenter.sub(EvId.SAMPLE_EVENT_WITH_ARGS, this::onNotifiedWithArguments);
    }

    void onNotified() {
        postService.onNotified();
    }

    void onNotifiedWithArguments(Object argument) {
        Integer number = (Integer)argument;
        postService.onNotifiedWithArgument(number);
    }
}
