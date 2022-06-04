package com.booknet.api.profile.controller;

import com.booknet.api.profile.payload.ProfileUpdateRequest;
import com.booknet.api.profile.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/profiles")
public class ProfileController {

    @Autowired
    ProfileService profileService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserProfile(@PathVariable("id") String id) {
        var response = profileService.responseGetUserProfile(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> updateUserProfile(
            @PathVariable("id") String id
            , @Valid @RequestBody ProfileUpdateRequest request
    ) {
        var response = profileService.updateProfile(id, request);
        return ResponseEntity.ok(response);
    }
}
