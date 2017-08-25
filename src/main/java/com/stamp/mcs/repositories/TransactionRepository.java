package com.stamp.mcs.repositories;

import com.stamp.mcs.entities.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by kenneth on 7/24/17.
 */
@Repository
public interface TransactionRepository extends MongoRepository<Transaction, String>{
}
