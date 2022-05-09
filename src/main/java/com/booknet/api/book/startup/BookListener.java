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
    BookService bookService;

    @Autowired
    public BookListener(BookService service) {
        this.bookService = service;
    }

    public void run(ApplicationArguments args) {
        EventCenter.sub(EvId.SAMPLE_EVENT, data -> onNotified());
        EventCenter.sub(EvId.SAMPLE_EVENT_WITH_ARGS, this::onNotifiedWithArguments);
    }

    void onNotified() {
        bookService.onNotified();
    }

    void onNotifiedWithArguments(Object argument) {
        Integer number = (Integer)argument;
        bookService.onNotifiedWithArgument(number);
    }
}
