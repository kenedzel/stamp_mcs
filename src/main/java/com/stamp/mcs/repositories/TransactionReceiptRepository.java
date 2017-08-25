package com.stamp.mcs.repositories;

import com.stamp.mcs.entities.TransactionReceipt;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by kenneth on 8/16/17.
 */
@Repository
public interface TransactionReceiptRepository extends MongoRepository<TransactionReceipt, String> {
}
