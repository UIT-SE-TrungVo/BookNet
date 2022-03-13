package com.booknet.apis.authentication.repositories;

import com.booknet.apis.authentication.models.AppRole;
import com.booknet.apis.authentication.models.EAppRole;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface IAppRoleRepository extends MongoRepository<AppRole, String> {
    Optional<AppRole> findByRole(EAppRole role);
}
