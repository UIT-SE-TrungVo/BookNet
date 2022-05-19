package com.booknet.api.account.authentication.repository;

import com.booknet.api.account.authentication.model.VerifyingUser;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VerifyingUserRepository extends MongoRepository<VerifyingUser, String> {
    VerifyingUser findByEmail(String email);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}