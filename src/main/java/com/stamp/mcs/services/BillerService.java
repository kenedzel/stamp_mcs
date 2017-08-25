package com.stamp.mcs.services;

import com.stamp.mcs.entities.Biller;
import com.stamp.mcs.entities.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by kenneth on 8/14/17.
 */
@Service
public interface BillerService {

    List<Biller> findAll();
    Biller add(Biller biller);
    Biller update(Biller biller);
    Biller findOne(String id);
    Biller findByAccountNumber(String accountNumber);
    void delete(String id);
    void receivePayment(String accountNumber, double amount);
    boolean existsOnAccountNumber(String accountNumber);
    boolean exists(String id);
}
