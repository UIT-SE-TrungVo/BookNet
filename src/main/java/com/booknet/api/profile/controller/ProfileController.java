package com.booknet.api.profile.controller;

import com.booknet.api.profile.payload.ProfileUpdateRequest;
import com.booknet.api.profile.service.ProfileService;
import com.booknet.base.payload.BaseResponse;
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
    public ResponseEntity<?> getById(@PathVariable("id") String id) {
        var model = profileService.getProfile(id);
        return ResponseEntity.ok(
                new BaseResponse(model)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProfile(
            @PathVariable("id") String id,
            @Valid @RequestBody ProfileUpdateRequest req
    ) {
        var editedModel = profileService.updateProfile(id, req);
        return ResponseEntity.ok(
                new BaseResponse(editedModel)
        );
    }
}
