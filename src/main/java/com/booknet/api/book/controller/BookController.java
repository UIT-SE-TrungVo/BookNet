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
@RequestMapping("/api/book")
public class BookController {
    @Autowired
    BookService bookService;

    @GetMapping
    public ResponseEntity<?> getAllSample() {
        Collection<?> samples = bookService.getAllSamples();
        return ResponseEntity.ok(
                new BaseResponse(samples.toArray())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") String id) {
        BookModel sample = bookService.getSample(id);
        return ResponseEntity.ok(
                new BaseResponse(sample)
        );
    }

    @PostMapping
    public ResponseEntity<?> createSample(@Valid @RequestBody BookCreateRequest req) {
        BookModel newModel = bookService.createSample(req);
        return ResponseEntity.ok(
                new BaseResponse(newModel)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSample(
            @PathVariable("id") String id,
            @Valid @RequestBody BookUpdateRequest req
    ) {
        BookModel editedModel = bookService.updateSample(id, req);
        return ResponseEntity.ok(
                new BaseResponse(editedModel)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeSample(@PathVariable("id") String id) {
        BookModel deleteSample = bookService.removeSample(id);
        return ResponseEntity.ok(
                new BaseResponse(deleteSample)
        );
    }

    @PostMapping("/notify")
    public ResponseEntity<?> doNotify() {
        bookService.doNotify();
        return ResponseEntity.ok(
                new BaseResponse(null)
        );
    }

    @PostMapping("/notify-with-args")
    public ResponseEntity<?> doNotifyWithArgument(@Valid @RequestBody BookNotifyRequest req) {
        bookService.doNotifyWithArgument(req);
        return ResponseEntity.ok(
                new BaseResponse(null)
        );
    }
}
