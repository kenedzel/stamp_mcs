package com.stamp.mcs.repositories;

import com.stamp.mcs.entities.Stamp;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by kenneth on 7/19/17.
 */
@Repository
public interface StampRepository extends MongoRepository<Stamp, String> {
}
