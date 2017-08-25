package com.stamp.mcs.services;

import com.stamp.mcs.entities.Biller;
import com.stamp.mcs.entities.Subscriber;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by kenneth on 8/14/17.
 */
@Service
public interface SubscriberService {

    List<Subscriber> findAll();
    Subscriber add(Subscriber subscriber);
    Subscriber update(Subscriber subscriber);
    Subscriber findOne(String id);
    Subscriber findByAccountNumber(String accountNumber);
    void delete(String id);
    void payment(String accountNumber, double amount);
    boolean exists(String id);
}
