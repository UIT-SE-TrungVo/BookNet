package com.booknet.api.authentication.repository;

import com.booknet.api.authentication.model.AppRole;
import com.booknet.api.authentication.model.EAppRole;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAppRoleRepository extends MongoRepository<AppRole, String> {
    Optional<AppRole> findByName(EAppRole name);
}