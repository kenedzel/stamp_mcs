package com.stamp.mcs.impl;

import com.stamp.mcs.entities.Subscriber;
import com.stamp.mcs.repositories.SubscriberRepository;
import com.stamp.mcs.services.SubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by kenneth on 8/14/17.
 */
@Primary
@Service
public class SubscriberServiceImpl implements SubscriberService{

    @Autowired
    SubscriberRepository subscriberRepository;

    @Override
    public List<Subscriber> findAll() {
        return subscriberRepository.findAll();
    }

    @Override
    public Subscriber add(Subscriber subscriber) {
        return subscriberRepository.save(subscriber);
    }

    @Override
    public Subscriber update(Subscriber subscriber) {
        return subscriberRepository.save(subscriber);
    }

    @Override
    public Subscriber findOne(String id) {
        return subscriberRepository.findOne(id);
    }

    @Override
    public void delete(String id) {
        subscriberRepository.delete(id);
    }

    @Override
    public boolean exists(String id) {
        return subscriberRepository.exists(id);
    }

    @Override
    public Subscriber findByAccountNumber(String accountNumber) {
        return subscriberRepository.findByAccountNumber(accountNumber);
    }

    @Override
    public void payment(String accountNumber, double amount) {
        Subscriber subscriber = subscriberRepository.findByAccountNumber(accountNumber);
        subscriber.setWallet(subscriber.getWallet() - amount);
        subscriberRepository.save(subscriber);
    }
}
