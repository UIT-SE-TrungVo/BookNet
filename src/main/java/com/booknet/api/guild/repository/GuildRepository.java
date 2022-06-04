package com.booknet.api.guild.repository;

import com.booknet.api.guild.model.GuildModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GuildRepository extends MongoRepository<GuildModel, String> {
    Optional<GuildModel> findBy_id(GuildModel id);
}