package com.booknet.api.book.startup;

import com.booknet.api.book.service.BookService;
import com.booknet.constants.EvId;
import com.booknet.system.EventCenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class BookListener implements ApplicationRunner {
    BookService postService;

    @Autowired
    public BookListener(BookService service) {
        this.postService = service;
    }

    public void run(ApplicationArguments args) {
    }
}
