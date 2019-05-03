package com.paul.farm.repositories;

import com.paul.farm.models.Farm;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FarmRepository extends MongoRepository<Farm, String> {

    List<Farm> findByOnline(boolean online);
}
