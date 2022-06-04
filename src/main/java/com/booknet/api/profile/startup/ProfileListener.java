package com.booknet.api.profile.startup;

import com.booknet.api.guild.event.GuildJoinEvData;
import com.booknet.api.guild.event.GuildLeaveEvData;
import com.booknet.api.profile.service.ProfileService;
import com.booknet.constants.EvId;
import com.booknet.system.EventCenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

@Component
public class ProfileListener {
    ProfileService profileService;

    @Autowired
    public ProfileListener(ProfileService service) {
        this.profileService = service;
    }

    public void run(ApplicationArguments args) {
        EventCenter.sub(EvId.USER_JOIN_GUILD, this::onUserJoinGuild);
        EventCenter.sub(EvId.USER_LEAVE_GUILD, this::onUserLeaveGuild);
    }

    void onUserJoinGuild(Object joinGuildEv) {
        var ev = (GuildJoinEvData)joinGuildEv;
        profileService.addUserToGuild(ev.getUserId(), ev.getGuildModel());
    }

    void onUserLeaveGuild(Object leaveGuildEv) {
        var ev = (GuildLeaveEvData)leaveGuildEv;
        profileService.removeUserFromGuild(ev.getUserId(), ev.getGuildModel());
    }
}
