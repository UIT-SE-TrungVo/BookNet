package com.booknet.api.account.authentication.repository;

import com.booknet.api.account.authentication.model.AppRole;
import com.booknet.api.account.authentication.model.EAppRole;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppRoleRepository extends MongoRepository<AppRole, String> {
    Optional<AppRole> findByName(EAppRole name);
}