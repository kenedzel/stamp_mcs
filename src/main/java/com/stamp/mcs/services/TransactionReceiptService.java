package com.stamp.mcs.services;

import com.stamp.mcs.entities.TransactionReceipt;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by kenneth on 8/16/17.
 */
@Service
public interface TransactionReceiptService {
    List<TransactionReceipt> findAll();
    TransactionReceipt add(TransactionReceipt transactionReceipt);
}
