package com.stamp.mcs.impl;

import com.stamp.mcs.entities.Transaction;
import com.stamp.mcs.repositories.TransactionRepository;
import com.stamp.mcs.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by kenneth on 7/24/17.
 */
@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public Transaction add(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public Transaction update(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    @Override
    public Page<Transaction> findAllPageable(Pageable pageable) {
        return transactionRepository.findAll(pageable);
    }

    @Override
    public Transaction findOne(String id) {
        return transactionRepository.findOne(id);
    }

    @Override
    public boolean exists(String id) {
        return transactionRepository.exists(id);
    }

    @Override
    public void delete(String id) {
        transactionRepository.delete(id);
    }
}
