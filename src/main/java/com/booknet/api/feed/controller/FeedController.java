package com.booknet.api.feed.controller;

import com.booknet.api.feed.model.*;
import com.booknet.api.feed.request.create.*;
import com.booknet.api.feed.request.FeedNotifyRequest;
import com.booknet.api.feed.service.FeedService;
import com.booknet.base.payload.BaseResponse;
import com.booknet.constants.ErrCode;
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

    // region feed
    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserFeed(@PathVariable("userId") String userId) {
        Collection<?> samples = feedService.getUserFeed(userId);
        return ResponseEntity.ok(
                new BaseResponse(samples.toArray())
        );
    }
    // endregion

    // region post
    @PostMapping("/post-news")
    public ResponseEntity<?> createPostNews(@Valid @RequestBody PostNewsCreateRequest req) {
        PostNewsModel postNewsModel = feedService.createPostNews(req);
        return ResponseEntity.ok(
                new BaseResponse(postNewsModel)
        );
    }

    @PostMapping("/guild-news")
    public ResponseEntity<?> createGuildNews(@Valid @RequestBody GuildNewsCreateRequest req) {
        GuildNewsModel postNewsModel = feedService.createGuildNews(req);
        return ResponseEntity.ok(
                new BaseResponse(postNewsModel)
        );
    }

    @PostMapping("/review-news")
    public ResponseEntity<?> createReviewNews(@Valid @RequestBody ReviewNewsCreateRequest req) {
        ReviewNewsModel postNewsModel = feedService.createReviewNews(req);
        return ResponseEntity.ok(
                new BaseResponse(postNewsModel)
        );
    }

    @PostMapping("/comment")
    public ResponseEntity<?> createComment(@Valid @RequestBody CommentCreateRequest req) {
        CommentModel model = feedService.createComment(req);
        if (model != null)
            return ResponseEntity.ok(new BaseResponse(model));
        else
            return ResponseEntity.badRequest().body(new BaseResponse( null));
    }

    @PostMapping("/reply-comment")
    public ResponseEntity<?> createReplyComment(@Valid @RequestBody ReplyCommentCreateRequest req) {
        ReplyCommentModel model = feedService.createReplyComment(req);
        if (model != null)
            return ResponseEntity.ok(new BaseResponse(model));
        else
            return ResponseEntity.badRequest().body(new BaseResponse( null));
    }

    @PostMapping("/react")
    public ResponseEntity<?> reactWithNews(@Valid @RequestBody ReactionCreateRequest req) {
        // TODO: Change total like of post to list of userId
        return null;
    }
    // endregion

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
