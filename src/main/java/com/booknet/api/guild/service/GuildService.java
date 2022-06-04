package com.booknet.api.guild.service;

import com.booknet.api.account.authentication.model.AppUser;
import com.booknet.api.account.authentication.service.AppUserService;
import com.booknet.api.guild.event.GuildJoinEvData;
import com.booknet.api.guild.event.GuildLeaveEvData;
import com.booknet.api.guild.model.GuildModel;
import com.booknet.api.guild.repository.GuildRepository;
import com.booknet.api.guild.request.GuildJoinRequest;
import com.booknet.api.guild.request.GuildLeaveRequest;
import com.booknet.api.guild.request.GuildViewRequest;
import com.booknet.api.guild.response.GuiViewAllResponse;
import com.booknet.api.guild.response.GuiViewResponse;
import com.booknet.base.payload.BaseResponse;
import com.booknet.constants.ErrCode;
import com.booknet.constants.EvId;
import com.booknet.system.EventCenter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class GuildService {
    private static final Logger logger = LoggerFactory.getLogger(GuildService.class);

    @Autowired
    GuildRepository guildRepository;

    //#region INTEGRATED SERVICES
    @Autowired
    AppUserService appUserService;
    //endregion

    public BaseResponse getAllGuildInfo() {
        var listGuild = guildRepository.findAll();
        logger.info("get all Guilds, guild numbers: {}", listGuild.size());

        var listIds = new ArrayList<String>();
        var listNames = new ArrayList<String>();
        var listDescription = new ArrayList<String>();

        listGuild.forEach(guild -> {
            listIds.add(guild.get_id());
            listNames.add(guild.getName());
            listDescription.add(guild.getDescription());
        });

        return BaseResponse.ok(
                new GuiViewAllResponse(listIds, listNames, listDescription));
    }

    public BaseResponse getSpecificGuildInfo(GuildViewRequest req) {
        GuildModel guildId = req.getGuildId();
        var userId = req.getUserId();

        logger.info("user {} attempts view guild {}", userId, guildId);

        var validation = new UserAndGuildValidation(userId, guildId);
        if (validation.isError()) {
            return BaseResponse.error(validation.getErrCode());
        }
        var user = validation.getUser();
        var guild = validation.getGuild();

        var name = guild.getName();
        var description = guild.getDescription();
        var numMember = guild.getMembers().size();
        var news = guild.getNews();
        var isMember = guild.isContainUser(user);

        logger.info("user {} is {} a member of guild {}"
                , guildId
                , isMember ? "" : "not"
                , guild.getName());

        return BaseResponse.ok(new GuiViewResponse(
                guildId
                , name
                , description
                , numMember
                , isMember ? news : null
                , isMember
        ));
    }

    public BaseResponse requestJoinGuild(GuildJoinRequest req) {
        GuildModel guildId = req.getGuildId();
        var userId = req.getUserId();

        logger.info("user {} attempts join guild {}", userId, guildId);

        var validation = new UserAndGuildValidation(userId, guildId);
        if (validation.isError()) {
            return BaseResponse.error(validation.getErrCode());
        }

        var user = validation.getUser();
        var guild = validation.getGuild();
        var isMember = guild.isContainUser(user);
        if (isMember) {
            logger.error("user {} is already a member of guild {}", userId, guildId);
            return BaseResponse.error(ErrCode.GUILD_ALREADY_JOINED);
        } else {
            guild.addMember(user);
            guildRepository.save(guild);
            logger.info("user {} JOIN guild {} SUCCESS", userId, guildId);
            EventCenter.pub(EvId.USER_JOIN_GUILD, new GuildJoinEvData(userId, guild));
            return BaseResponse.ok();
        }
    }

    public BaseResponse requestLeaveGuild(GuildLeaveRequest req) {
        GuildModel guildId = req.getGuildId();
        var userId = req.getUserId();

        logger.info("user {} attempts leave guild {}", userId, guildId);

        var validation = new UserAndGuildValidation(userId, guildId);
        if (validation.isError()) {
            return BaseResponse.error(validation.getErrCode());
        }

        var user = validation.getUser();
        var guild = validation.getGuild();
        var isMember = guild.isContainUser(user);
        if (!isMember) {
            logger.error("user {} is not a member of guild {}", userId, guildId);
            return BaseResponse.error(ErrCode.GUILD_NOT_A_MEMBER);
        } else {
            guild.removeMember(user);
            guildRepository.save(guild);
            EventCenter.pub(EvId.USER_LEAVE_GUILD, new GuildLeaveEvData(userId, guild));
            logger.info("user {} LEAVE guild {} SUCCESS", userId, guildId);
            return BaseResponse.ok();
        }
    }

    class UserAndGuildValidation {
        Optional<AppUser> user;
        Optional<GuildModel> guild;
        long errCode;

        public UserAndGuildValidation(String userId, GuildModel guildId) {
            errCode = ErrCode.NONE;

            user = appUserService.getUserById(userId);
            if (user.isEmpty()) {
                logger.error("user {} not found for view guild {} --> stop", userId, guildId);
                errCode = ErrCode.USER_NOT_FOUND;
            }

            guild = guildRepository.findBy_id(guildId);
            if (guild.isEmpty()) {
                logger.error("no guild with id {} found in database", guildId);
                errCode = ErrCode.GUILD_NOT_FOUND;
            }
        }

        public boolean isError() {
            return errCode != ErrCode.NONE;
        }

        public AppUser getUser() {
            return user.orElse(null);
        }

        public GuildModel getGuild() {
            return guild.orElse(null);
        }

        public long getErrCode() {
            return errCode;
        }
    }
}