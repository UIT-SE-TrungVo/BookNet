package com.booknet.api.post.controller;

import com.booknet.api.book.model.BookModel;
import com.booknet.api.book.request.BookCreateRequest;
import com.booknet.api.book.request.BookNotifyRequest;
import com.booknet.api.book.request.BookUpdateRequest;
import com.booknet.api.book.service.PostService;
import com.booknet.base.payload.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/post")
public class PostController {
    @Autowired
    PostService postService;

    @GetMapping
    public ResponseEntity<?> getAllSample() {
        Collection<?> samples = postService.getAllSamples();
        return ResponseEntity.ok(
                new BaseResponse(samples.toArray())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") String id) {
        BookModel sample = postService.getSample(id);
        return ResponseEntity.ok(
                new BaseResponse(sample)
        );
    }

    @PostMapping
    public ResponseEntity<?> createSample(@Valid @RequestBody BookCreateRequest req) {
        BookModel newModel = postService.createSample(req);
        return ResponseEntity.ok(
                new BaseResponse(newModel)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSample(
            @PathVariable("id") String id,
            @Valid @RequestBody BookUpdateRequest req
    ) {
        BookModel editedModel = postService.updateSample(id, req);
        return ResponseEntity.ok(
                new BaseResponse(editedModel)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removeSample(@PathVariable("id") String id) {
        BookModel deleteSample = postService.removeSample(id);
        return ResponseEntity.ok(
                new BaseResponse(deleteSample)
        );
    }

    @PostMapping("/notify")
    public ResponseEntity<?> doNotify() {
        postService.doNotify();
        return ResponseEntity.ok(
                new BaseResponse(null)
        );
    }

    @PostMapping("/notify-with-args")
    public ResponseEntity<?> doNotifyWithArgument(@Valid @RequestBody BookNotifyRequest req) {
        postService.doNotifyWithArgument(req);
        return ResponseEntity.ok(
                new BaseResponse(null)
        );
    }
}
