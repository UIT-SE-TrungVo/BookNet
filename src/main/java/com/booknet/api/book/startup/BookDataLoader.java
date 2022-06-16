package com.booknet.api.book.startup;

import com.booknet.api.book.repository.BookRepository;
import com.booknet.api.book.request.BookCreateRequest;
import com.booknet.api.book.service.BookService;
import com.booknet.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class BookDataLoader implements ApplicationRunner {
    BookService postService;
    BookRepository bookRepository;

    @Autowired
    public BookDataLoader(BookService service, BookRepository repository) {
        this.postService = service;
        this.bookRepository = repository;
    }

    public void run(ApplicationArguments args) {

    }
}
