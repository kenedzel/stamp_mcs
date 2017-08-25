package com.stamp.mcs.services;

import com.stamp.mcs.entities.Bill;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by kenneth on 8/14/17.
 */
@Service
public interface BillService {

    List<Bill> findAll();
    Bill add(Bill bill);
    Bill update(Bill bill);
    Bill findOne(String id);
    Bill addBalance(String subscriberAccountNumber, String billerAccountNumber, double amount);
    Bill findBySubscriberAccountNumber(String subscriberAccountNumber);
    Bill findBySubscriberMsisdn(String msisdn);
    Bill findBySubscriberAndBiller(String subscriberAccountNumber, String billerAccountNumber);
    void settlePayment(String subscriberAccountNumber, double amount);
    void delete(String id);
    boolean exists(String id);

}
