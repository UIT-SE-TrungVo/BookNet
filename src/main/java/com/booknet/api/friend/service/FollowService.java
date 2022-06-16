package com.booknet.api.friend.service;

import com.booknet.api.account.authentication.repository.AppUserRepository;
import com.booknet.api.friend.payload.FollowRequest;
import com.booknet.api.profile.model.ProfileModel;
import com.booknet.api.profile.model.ProfileSimplifiedModel;
import com.booknet.api.profile.repository.ProfileRepository;
import com.booknet.base.payload.BaseResponse;
import com.booknet.constants.ErrCode;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class FollowService {
    private static final Logger logger = LoggerFactory.getLogger(FollowService.class);

    @Autowired
    ProfileRepository profileRepository;

    @Autowired
    AppUserRepository appUserRepository;

    public BaseResponse getFollowers(String userId) {
        var user = profileRepository.findBy_id(userId).orElse(null);
        if (user == null) {
            return BaseResponse.error(ErrCode.USER_NOT_FOUND);
        }

        List<ProfileSimplifiedModel> listFriend = user.getFollowersSimplified();
        return BaseResponse.ok(listFriend);
    }

    public BaseResponse getFollowing(String userId) {
        var user = profileRepository.findBy_id(userId).orElse(null);
        if (user == null) {
            logger.error("Cannot get list friend of un-existed {}", userId);
            return BaseResponse.error(ErrCode.USER_NOT_FOUND);
        }

        List<ProfileSimplifiedModel> listFriend = user.getFollowingSimplified();
        return BaseResponse.ok(listFriend);
    }

    public BaseResponse follow(FollowRequest req) {
        String fromId = req.getFromId();
        String toId = req.getToId();
        boolean isToFollow = req.isToFollow();

        ProfileModel userFrom = profileRepository.findBy_id(fromId).orElse(null);
        ProfileModel userTo = profileRepository.findBy_id(toId).orElse(null);

        assert userFrom != null;
        assert userTo != null;

        var followersOfUserTo = userTo.getFollowers();
        var followingOfUserFrom = userFrom.getFollowing();

        if (isToFollow) {
            followingOfUserFrom.add(userTo);
            followersOfUserTo.add(userFrom);
        }
        else {
            followingOfUserFrom.removeIf(model -> model.get_id().equals(userTo.get_id()));
            followersOfUserTo.removeIf(model -> model.get_id().equals(userFrom.get_id()));
        }

        userTo.setFollowers(followersOfUserTo);
        userFrom.setFollowing(followingOfUserFrom);

        profileRepository.save(userFrom);
        profileRepository.save(userTo);

        return BaseResponse.ok();
    }

//    public BaseResponse addFriend(FollowRequest req) {
//        var fromId = req.getFromId();
//        var toId = req.getToId();
//
//        var verifyError = this.verifyFriendStatus(fromId, toId, true, false);
//        if (verifyError != ErrCode.NONE) {
//            logger.error("Error happened in remove friend process between {} and {}", fromId, toId);
//            return BaseResponse.error(verifyError);
//        }
//
//        var userFrom = profileRepository.findBy_id(fromId).orElse(null);
//        var userTo = profileRepository.findBy_id(toId).orElse(null);
//
//        assert userFrom != null;
//        var listFriendFrom = userFrom.getListFriend();
//        assert userTo != null;
//        var listFriendTo = userTo.getListFriend();
//
//        listFriendFrom.add(userTo);
//        userFrom.setListFriend(listFriendFrom);
//        profileRepository.save(userFrom);
//
//        listFriendTo.add(userFrom);
//        userTo.setListFriend(listFriendTo);
//        profileRepository.save(userTo);
//
//        logger.info("Friends matched successfully! {} {}", fromId, toId);
//        return BaseResponse.ok();
//    }

//    public BaseResponse removeFriend(UnfollowRequest req) {
//        var fromId = req.getFromId();
//        var toId = req.getToId();
//
//        var verifyError = this.verifyFriendStatus(fromId, toId, false, true);
//        if (verifyError != ErrCode.NONE) {
//            logger.error("Error happened in remove friend process between {} and {}", fromId, toId);
//            return BaseResponse.error(verifyError);
//        }
//
//        var userFrom = profileRepository.findBy_id(fromId).orElse(null);
//        var userTo = profileRepository.findBy_id(toId).orElse(null);
//
//        assert userFrom != null;
//        var listFriendFrom = userFrom.getListFriend();
//        assert userTo != null;
//        var listFriendTo = userTo.getListFriend();
//
//        listFriendFrom = this.filterFriendList(listFriendFrom, toId);
//        userFrom.setListFriend(listFriendFrom);
//        profileRepository.save(userFrom);
//
//        listFriendTo = this.filterFriendList(listFriendTo, fromId);
//        userTo.setListFriend(listFriendTo);
//        profileRepository.save(userTo);
//
//        logger.info("Friends un-matched successfully! {} {}", fromId, toId);
//        return BaseResponse.ok();
//    }

    private boolean isInFriendList(List<ProfileModel> listFriend, String userId) {
        for (ProfileModel friend : listFriend) {
            if (Objects.equals(friend.get_id(), userId)) return true;
        }
        return false;
    }

    private @NotNull List<ProfileModel> filterFriendList(List<ProfileModel> listFriend, String userId) {
        List<ProfileModel> list = new ArrayList<>();
        for (ProfileModel friend : listFriend) {
            if (!Objects.equals(friend.get_id(), userId)) {
                list.add(friend);
            }
        }
        return list;
    }

//    private long verifyFriendStatus(String fromId, String toId, boolean requiredMatched, boolean requireUnmatched) {
//        var userFrom = profileRepository.findBy_id(fromId).orElse(null);
//        if (userFrom == null) {
//            logger.error("Cannot get list friend of un-existed {} {}", fromId, toId);
//            return ErrCode.USER_NOT_FOUND;
//        }
//
//        var userTo = profileRepository.findBy_id(toId).orElse(null);
//        if (userTo == null) {
//            logger.error("Cannot add friend non-existed user {} {}", fromId, toId);
//            return ErrCode.FRIEND_ALREADY_MATCHED;
//        }
//
//        List<ProfileModel> listFriendFrom = userFrom.getListFriend();
//        List<ProfileModel> listFriendTo = userTo.getListFriend();
//        if (requiredMatched) {
//            if (this.isInFriendList(listFriendFrom, toId) || this.isInFriendList(listFriendTo, fromId)) {
//                logger.error("Users are friends before {} {}", fromId, toId);
//                return ErrCode.FRIEND_ALREADY_MATCHED;
//            }
//        }
//        else if (requireUnmatched) {
//            if (!this.isInFriendList(listFriendFrom, toId) && !this.isInFriendList(listFriendTo, fromId)) {
//                logger.error("Users are not friends before {} {}", fromId, toId);
//                return ErrCode.FRIEND_ALREADY_UNMATCHED;
//            }
//        }
//
//        return ErrCode.NONE;
//    }
}