package com.stamp.mcs.impl;

import com.stamp.mcs.StampMcsApplication;
import com.stamp.mcs.entities.Transaction;
import com.stamp.mcs.repositories.TransactionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by kenneth on 8/4/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = StampMcsApplication.class)
public class TransactionServiceImplTest {

    @Autowired
    TransactionRepository transactionRepository;

    public Transaction getMockTransaction() {
        Transaction transaction = new Transaction();
        transaction.setId("11");
        transaction.setTransactionName("Transaction Name");
        transaction.setReferenceNumber("011BB");
        transaction.setAccountNumber("ABAB@!");

        return transaction;
    }

    @Test
    public void testSave() {
        Transaction savedTransaction = transactionRepository.save(getMockTransaction());

        assertNotNull(getMockTransaction().getId());
        assertEquals(savedTransaction.getId(), getMockTransaction().getId());
        assertEquals(savedTransaction.getTransactionName(), getMockTransaction().getTransactionName());
        assertEquals(savedTransaction.getReferenceNumber(), getMockTransaction().getReferenceNumber());
        assertEquals(savedTransaction.getAccountNumber(), getMockTransaction().getAccountNumber());
    }

    @Test
    public void testFindOne() {
        Transaction savedTransaction = transactionRepository.save(getMockTransaction());
        Transaction testTransaction = transactionRepository.findOne(savedTransaction.getId());

        assertNotNull(savedTransaction.getId());
        assertEquals(savedTransaction.getId(), testTransaction.getId());
        assertEquals(savedTransaction.getAccountNumber(), testTransaction.getAccountNumber());
        assertEquals(savedTransaction.getAccountNumber(), testTransaction.getAccountNumber());
        assertEquals(savedTransaction.getReferenceNumber(), testTransaction.getReferenceNumber());
    }

    @Test
    public void testDelete() {
        Transaction savedTransaction = transactionRepository.save(getMockTransaction());
        transactionRepository.delete(savedTransaction.getId());
        assertNull(transactionRepository.findOne(savedTransaction.getId()));
    }

    @Test
    public void testExists() {
        Transaction savedTransaction = transactionRepository.save(getMockTransaction());
        assertNotNull(transactionRepository.exists(savedTransaction.getId()));
    }
}