package com.booknet.api.post.repository;

import com.booknet.api.post.model.PostModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends MongoRepository<PostModel, String> {
    Optional<PostModel> findBy_id(String id);
}
