package com.booknet.api.friend.controller;

import com.booknet.api.friend.payload.FriendAddRequest;
import com.booknet.api.friend.payload.FriendRemoveRequest;
import com.booknet.api.friend.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/friends")
public class FriendController {

    @Autowired
    FriendService friendService;

//    @GetMapping("/id={id}")
//    public ResponseEntity<?> getListFriend(@PathVariable("id") String userId) {
//        var response = friendService.getListFriend(userId);
//        return ResponseEntity.ok(response);
//    }

    @PostMapping("/add")
    public ResponseEntity<?> addFriend(@Valid @RequestBody FriendAddRequest req) {
        var response = friendService.addFriend(req);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/remove")
    public ResponseEntity<?> removeFriend(@Valid @RequestBody FriendRemoveRequest req) {
        var response = friendService.removeFriend(req);
        return ResponseEntity.ok(response);
    }
}
