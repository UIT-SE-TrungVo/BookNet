package com.booknet.api.feed.repository;

import com.booknet.api.feed.model.BaseNewsModel;
import com.booknet.api.feed.model.PostNewsModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostNewsRepository extends MongoRepository<PostNewsModel, String> {
    Optional<PostNewsModel> findBy_id(String id);

    List<?> findPostNewsBy_id(String userId);

}
