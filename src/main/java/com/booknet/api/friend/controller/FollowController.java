package com.booknet.api.friend.controller;

import com.booknet.api.friend.payload.FollowRequest;
import com.booknet.api.friend.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/follow")
public class FollowController {

    @Autowired
    FollowService followService;

    @GetMapping("/followers/{id}")
    public ResponseEntity<?> getFollowers(@PathVariable("id") String id) {
        var response = followService.getFollowers(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/following/{id}")
    public ResponseEntity<?> getFollowing(@PathVariable("id") String id) {
        var response = followService.getFollowing(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping()
    public ResponseEntity<?> follow(@Valid @RequestBody FollowRequest req) {
        var response = followService.follow(req);
        return ResponseEntity.ok(response);
    }

//    @PostMapping("/unfollow")
//    public ResponseEntity<?> removeFriend(@Valid @RequestBody UnfollowRequest req) {
//        var response = friendService.removeFriend(req);
//        return ResponseEntity.ok(response);
//    }
}
