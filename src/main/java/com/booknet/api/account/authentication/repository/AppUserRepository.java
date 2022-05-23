package com.booknet.api.account.authentication.repository;

import com.booknet.api.account.authentication.model.AppUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository extends MongoRepository<AppUser, String> {
    Optional<AppUser> findByUsername(String username);

    Optional<AppUser> findByEmail(String email);

    Boolean existsBy_id(String id);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}