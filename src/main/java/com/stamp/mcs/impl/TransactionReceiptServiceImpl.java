package com.stamp.mcs.impl;

import com.stamp.mcs.entities.TransactionReceipt;
import com.stamp.mcs.repositories.TransactionReceiptRepository;
import com.stamp.mcs.services.TransactionReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by kenneth on 8/16/17.
 */
@Service
@Primary
public class TransactionReceiptServiceImpl implements TransactionReceiptService {

    @Autowired
    TransactionReceiptRepository transactionReceiptRepository;

    @Override
    public List<TransactionReceipt> findAll() {
        return transactionReceiptRepository.findAll();
    }

    @Override
    public TransactionReceipt add(TransactionReceipt transactionReceipt) {
        return transactionReceiptRepository.save(transactionReceipt);
    }
}
