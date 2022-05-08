package com.booknet.api.account.reset_password.repository;

import com.booknet.api.account.reset_password.token.PasswordResetToken;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResetPasswordRepository extends MongoRepository<PasswordResetToken, String> {
    Optional<PasswordResetToken> findBy_id(String id);
}