package com.booknet.api.profile.repository;

import com.booknet.api.profile.model.ProfileModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ProfileRepository extends MongoRepository<ProfileModel, String> {
    Optional<ProfileModel> findBy_id(String id);

    boolean existsBy_id(String id);
}
