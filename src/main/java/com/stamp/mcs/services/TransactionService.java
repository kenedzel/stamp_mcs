package com.stamp.mcs.services;

import com.stamp.mcs.entities.Transaction;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by kenneth on 7/24/17.
 */
@Primary
@Service
public interface TransactionService {
    Transaction add(Transaction transaction);
    List<Transaction> findAll();
    Page<Transaction> findAllPageable(Pageable pageable);
    Transaction findOne(String id);
    Transaction update(Transaction transaction);
    boolean exists(String id);
    void delete(String id);
}
