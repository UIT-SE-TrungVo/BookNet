package com.booknet.api.sample_module.repository;

import com.booknet.api.sample_module.model.SampleModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SampleRepository extends MongoRepository<SampleModel, String> {
    Optional<SampleModel> findBy_id(String id);
}
