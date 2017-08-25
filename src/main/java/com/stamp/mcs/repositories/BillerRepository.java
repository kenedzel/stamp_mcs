package com.stamp.mcs.repositories;

import com.stamp.mcs.entities.Biller;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by kenneth on 8/14/17.
 */
@Repository
public interface BillerRepository extends MongoRepository<Biller, String> {
    Biller findByAccountNumber(String accountNumber);
}
