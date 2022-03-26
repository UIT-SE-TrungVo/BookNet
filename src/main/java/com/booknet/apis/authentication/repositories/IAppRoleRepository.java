package com.booknet.apis.authentication.repositories;

import com.booknet.apis.authentication.models.AppRole;
import com.booknet.apis.authentication.models.EAppRole;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAppRoleRepository extends MongoRepository<AppRole, String> {
    Optional<AppRole> findByName(EAppRole name);
}
