package com.stamp.mcs.repositories;

import com.stamp.mcs.entities.Bill;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by kenneth on 8/14/17.
 */
@Repository
public interface BillRepository extends MongoRepository<Bill, String>{
    Bill findBySubscriberAccountNumber(String subscriberAccountNumber);
    Bill findBySubscriberMsisdn(String msisdn);
    @Query("{ $and: [" +
                "{'subscriberAccountNumber' : ?0}," +
                "{'billerAccountNumber' : ?1}" +
            "]}")
    Bill findBySubscriberAndBiller(String subscriberAccountNumber, String billerAccountNumber);
}

