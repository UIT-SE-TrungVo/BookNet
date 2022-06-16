package com.booknet.api.book.controller;

import com.booknet.api.book.model.BookModel;
import com.booknet.api.book.request.BookCreateRequest;
import com.booknet.api.book.request.BookNotifyRequest;
import com.booknet.api.book.request.BookUpdateRequest;
import com.booknet.api.book.service.BookService;
import com.booknet.base.payload.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/book/v1")
public class BookController {
    @Autowired
    BookService bookService;

    @GetMapping
    public ResponseEntity<?> getAllSample() {
        Collection<?> samples = bookService.getAllBooks();
        return ResponseEntity.ok(
                new BaseResponse(samples.toArray())
        );
    }
}
