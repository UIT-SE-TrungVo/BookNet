package com.booknet.api.post.startup;

import com.booknet.api.book.request.BookCreateRequest;
import com.booknet.api.book.service.PostService;
import com.booknet.api.post.repository.PostRepository;
import com.booknet.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class BookDataLoader implements ApplicationRunner {
    PostService postService;
    PostRepository postRepository;

    @Autowired
    public BookDataLoader(PostService service, PostRepository repository) {
        this.postService = service;
        this.postRepository = repository;
    }

    public void run(ApplicationArguments args) {
        this.postRepository.deleteAll();

        final int NUM_GENERATED_MODEL = 10;
        for (int i = 0; i < NUM_GENERATED_MODEL; i++) {
            Integer number = Utils.math.randomInt(0, 10);
            String text = "abcdef";

            BookCreateRequest req = new BookCreateRequest();
            req.setNumber(number);
            req.setText(text);
            this.postService.createSample(req);
        }
    }
}
