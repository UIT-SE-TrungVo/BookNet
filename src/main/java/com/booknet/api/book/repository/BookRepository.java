package com.booknet.api.book.repository;

import com.booknet.api.book.model.BookModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends MongoRepository<BookModel, String> {
    Optional<BookModel> findBy_id(String id);
}
