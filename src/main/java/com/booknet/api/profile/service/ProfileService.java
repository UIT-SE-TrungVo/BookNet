package com.booknet.api.profile.service;

import com.booknet.api.profile.model.ProfileModel;
import com.booknet.api.profile.event.ProfileCreateEventData;
import com.booknet.api.profile.payload.ProfileUpdateRequest;
import com.booknet.api.profile.repository.ProfileRepository;
import com.booknet.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {
    private static final Logger logger = LoggerFactory.getLogger(ProfileService.class);

    @Autowired
    ProfileRepository profileRepository;

    private void _createProfile(ProfileCreateEventData reqData) {
        var appUser = reqData.getUser();
        var id = appUser.get_id();
        var firstName = reqData.getFirstName();
        var lastName = reqData.getLastName();
        var newProfile = new ProfileModel(id, firstName, lastName);

        logger.info("create ProfileModel success {}", Utils.json.stringify(newProfile));
    }

    public ProfileModel updateProfile(String id, ProfileUpdateRequest reqData) {
        var dbValue = this.getProfile(id);
        var firstName = reqData.getFirstName();
        var lastName = reqData.getLastName();
        var email = reqData.getEmail();

        if (dbValue != null) {
            String jsonOldData = Utils.json.stringify(dbValue);
            dbValue.setFirstName(firstName);
            dbValue.setLastName(lastName);
            profileRepository.save(dbValue);
            logger.info("update ProfileModel success OLD: {} - NEW: {}"
                    , jsonOldData
                    , Utils.json.stringify(reqData)
            );
            return dbValue;
        } else {
            logger.info("cannot update non-exist ProfileModel");
            return null;
        }
    }

    public ProfileModel getProfile(String id) {
        var profile = profileRepository.findBy_id(id).orElse(null);
        logger.info("get ProfileModel with id {} {}", id, Utils.json.stringify(profile));
        return profile;
    }

    public void onCreateAppUser(ProfileCreateEventData req) {
        _createProfile(req);
    }
}
