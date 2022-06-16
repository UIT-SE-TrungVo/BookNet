package com.booknet.api.friend.controller;

import com.booknet.api.friend.payload.FollowRequest;
import com.booknet.api.friend.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/follow")
public class FriendController {

    @Autowired
    FriendService friendService;

    @GetMapping("/followers")
    public ResponseEntity<?> getFollowers(@Valid @RequestBody String userId) {
        var response = friendService.getFollowers(userId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/following")
    public ResponseEntity<?> getFollowing(@Valid @RequestBody String userId) {
        var response = friendService.getFollowing(userId);
        return ResponseEntity.ok(response);
    }

    @PostMapping()
    public ResponseEntity<?> follow(@Valid @RequestBody FollowRequest req) {
        var response = friendService.follow(req);
        return ResponseEntity.ok(response);
    }

//    @PostMapping("/unfollow")
//    public ResponseEntity<?> removeFriend(@Valid @RequestBody UnfollowRequest req) {
//        var response = friendService.removeFriend(req);
//        return ResponseEntity.ok(response);
//    }
}
