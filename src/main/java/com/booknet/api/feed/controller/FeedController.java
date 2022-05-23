package com.booknet.api.feed.controller;

import com.booknet.api.feed.model.BaseNewsModel;
import com.booknet.api.feed.model.PostNewsModel;
import com.booknet.api.feed.request.news.PostNewsCreateRequest;
import com.booknet.api.feed.request.FeedNotifyRequest;
import com.booknet.api.feed.service.FeedService;
import com.booknet.base.payload.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/feed")
public class FeedController {
    @Autowired
    FeedService feedService;

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserFeed(@PathVariable("userId") String userId) {
        Collection<?> samples = feedService.getUserFeed(userId);
        return ResponseEntity.ok(
                new BaseResponse(samples.toArray())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") String id) {
        BaseNewsModel sample = feedService.getSample(id);
        return ResponseEntity.ok(
                new BaseResponse(sample)
        );
    }

    @PostMapping
    public ResponseEntity<?> createSample(@Valid @RequestBody PostNewsCreateRequest req) {
        PostNewsModel newModel = feedService.createPostNews(req);
        return ResponseEntity.ok(
                new BaseResponse(newModel)
        );
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<?> updateSample(
//            @PathVariable("id") String id,
//            @Valid @RequestBody FeedUpdateRequest req
//    ) {
//        BaseNews editedModel = feedService.updateSample(id, req);
//        return ResponseEntity.ok(
//                new BaseResponse(editedModel)
//        );
//    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<?> removeSample(@PathVariable("id") String id) {
//        BaseNews deleteSample = feedService.removeSample(id);
//        return ResponseEntity.ok(
//                new BaseResponse(deleteSample)
//        );
//    }

    @PostMapping("/notify")
    public ResponseEntity<?> doNotify() {
        feedService.doNotify();
        return ResponseEntity.ok(
                new BaseResponse(null)
        );
    }

    @PostMapping("/notify-with-args")
    public ResponseEntity<?> doNotifyWithArgument(@Valid @RequestBody FeedNotifyRequest req) {
        feedService.doNotifyWithArgument(req);
        return ResponseEntity.ok(
                new BaseResponse(null)
        );
    }
}
