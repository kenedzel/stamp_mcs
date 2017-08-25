package com.stamp.mcs.repositories;

import com.stamp.mcs.entities.Subscriber;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by kenneth on 8/14/17.
 */
@Repository
public interface SubscriberRepository extends MongoRepository<Subscriber, String> {
    Subscriber findByAccountNumber(String accountNumber);
}
