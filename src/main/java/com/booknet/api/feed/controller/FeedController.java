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
import java.util.List;

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
    @PostMapping("/post_news")
    public ResponseEntity<?> createPostNews(@Valid @RequestBody PostNewsCreateRequest req) {
        PostNewsModel postNewsModel = feedService.createPostNews(req);
        return ResponseEntity.ok(
                new BaseResponse(postNewsModel)
        );
    }

    @PostMapping("/guild_news")
    public ResponseEntity<?> createGuildNews(@Valid @RequestBody GuildNewsCreateRequest req) {
        GuildNewsModel postNewsModel = feedService.createGuildNews(req);
        return ResponseEntity.ok(
                new BaseResponse(postNewsModel)
        );
    }

    @PostMapping("/review_news")
    public ResponseEntity<?> createReviewNews(@Valid @RequestBody ReviewNewsCreateRequest req) {
        ReviewNewsModel postNewsModel = feedService.createReviewNews(req);
        return ResponseEntity.ok(
                new BaseResponse(postNewsModel)
        );
    }

    @PostMapping("/react")
    public ResponseEntity<?> reactWithNews(@Valid @RequestBody ReactionCreateRequest req) {
        List<String> userIdLikeList = feedService.reactWithPost(req);
        if (userIdLikeList != null)
            return ResponseEntity.ok(new BaseResponse(userIdLikeList));
        else
            return ResponseEntity.badRequest().body(new BaseResponse( null));
    }

    @PostMapping("/comment")
    public ResponseEntity<?> createComment(@Valid @RequestBody CommentCreateRequest req) {
        CommentModel model = feedService.createComment(req);
        if (model != null)
            return ResponseEntity.ok(new BaseResponse(model));
        else
            return ResponseEntity.badRequest().body(new BaseResponse( null));
    }

    @PostMapping("/reply_comment")
    public ResponseEntity<?> createReplyComment(@Valid @RequestBody ReplyCommentCreateRequest req) {
        ReplyCommentModel model = feedService.createReplyComment(req);
        if (model != null)
            return ResponseEntity.ok(new BaseResponse(model));
        else
            return ResponseEntity.badRequest().body(new BaseResponse( null));
    }
    // endregion

    @PostMapping
    public ResponseEntity<?> createSample(@Valid @RequestBody PostNewsCreateRequest req) {
        PostNewsModel newModel = feedService.createPostNews(req);
        return ResponseEntity.ok(
                new BaseResponse(newModel)
        );
    }
}
