package com.booknet.api.profile.service;

import com.booknet.api.account.authentication.model.AppUser;
import com.booknet.api.account.authentication.repository.AppUserRepository;
import com.booknet.api.guild.model.GuildModel;
import com.booknet.api.profile.model.ProfileModel;
import com.booknet.api.profile.payload.ProfileUpdateRequest;
import com.booknet.api.profile.repository.ProfileRepository;
import com.booknet.base.payload.BaseResponse;
import com.booknet.constants.ErrCode;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Objects;

@Service
public class ProfileService {
    private static final Logger logger = LoggerFactory.getLogger(ProfileService.class);

    @Autowired
    ProfileRepository profileRepository;

    @Autowired
    AppUserRepository appUserRepository;

    public ProfileModel getOrCreateProfile(@NotNull AppUser appUser) {
        var profile = profileRepository.findBy_id(appUser.get_id())
                .orElse(new ProfileModel(appUser, ""));
        profileRepository.save(profile);
        return profile;
    }

    public BaseResponse responseGetUserProfile(String userId) {
        var user = appUserRepository.findBy_id(userId).orElse(null);
        if (user == null) {
            logger.error("cannot update for non-existed user {}", userId);
            return BaseResponse.error(ErrCode.USER_NOT_FOUND);
        } else {
            var profile = profileRepository.findBy_id(userId).orElse(null);
            return BaseResponse.ok(profile);
        }
    }

    public BaseResponse updateProfile(@NotEmpty String userId, @NotNull ProfileUpdateRequest request) {
        var user = appUserRepository.findBy_id(userId).orElse(null);
        if (user == null) {
            logger.error("cannot update for non-existed user {}", userId);
            return BaseResponse.error(ErrCode.USER_NOT_FOUND);
        } else {
            var profile = this.getOrCreateProfile(user);
            profile.setUrlImage(request.getUrlImage());
            profile.setName(request.getName());
            profile.setDob(request.getDob());
            profile.setGender(request.getGender());
            profileRepository.save(profile);
            return BaseResponse.ok();
        }
    }

    public void addUserToGuild(String userId, GuildModel guildModel) {
        var user = appUserRepository.findBy_id(userId).orElse(null);
        if (user == null) {
            logger.error("cannot update for non-existed user {}", userId);
            return;
        }

        var profile = this.getOrCreateProfile(user);
        var listGuild = profile.getListGuild();
        for (var guild : listGuild) {
            if (Objects.equals(guild.get_id(), guildModel.get_id())) {
                logger.error("user is in guild !");
                return;
            }
        }
        listGuild.add(guildModel);
        profile.setListGuild(listGuild);
        profileRepository.save(profile);
    }

    public void removeUserFromGuild(String userId, GuildModel guildModel) {
        var user = appUserRepository.findBy_id(userId).orElse(null);
        if (user == null) {
            logger.error("cannot update for non-existed user {}", userId);
            return;
        }

        var profile = this.getOrCreateProfile(user);
        var listGuild = profile.getListGuild();
        var filtered = new ArrayList<GuildModel>();
        for (var guild : listGuild) {
            if (!Objects.equals(guild.get_id(), guildModel.get_id())) filtered.add(guild);
        }
        profile.setListGuild(filtered);
        profileRepository.save(profile);
    }
}