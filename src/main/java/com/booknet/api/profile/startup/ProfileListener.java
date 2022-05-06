package com.booknet.api.profile.startup;

import com.booknet.api.profile.event.ProfileCreateEventData;
import com.booknet.api.profile.service.ProfileService;
import com.booknet.constants.EvId;
import com.booknet.system.EventCenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class ProfileListener implements ApplicationRunner {
    ProfileService profileService;

    @Autowired
    public ProfileListener(ProfileService service) {
        profileService = service;
    }

    public void run(ApplicationArguments args) {
        EventCenter.sub(EvId.USER_SIGNUP_SUCCESS, this::onCreateAppUser);
    }

    void onCreateAppUser(Object evData) {
        var req = (ProfileCreateEventData) evData;
        profileService.onCreateAppUser(req);
    }
}
