package com.stamp.mcs.impl;

import com.stamp.mcs.entities.Biller;
import com.stamp.mcs.entities.Transaction;
import com.stamp.mcs.repositories.BillerRepository;
import com.stamp.mcs.services.BillerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by kenneth on 8/14/17.
 */
@Primary
@Service
public class BillerServiceImpl implements BillerService {

    @Autowired
    BillerRepository billerRepository;

    @Override
    public List<Biller> findAll() {
        return billerRepository.findAll();
    }

    @Override
    public Biller add(Biller biller) {
        return billerRepository.save(biller);
    }

    @Override
    public Biller update(Biller biller) {
        return billerRepository.save(biller);
    }

    @Override
    public Biller findOne(String id) {
        return billerRepository.findOne(id);
    }

    @Override
    public Biller findByAccountNumber(String accountNumber) {
        return billerRepository.findByAccountNumber(accountNumber);
    }

    @Override
    public void delete(String id) {
        billerRepository.delete(id);
    }

    @Override
    public boolean exists(String id) {
        return billerRepository.exists(id);
    }

    @Override
    public void receivePayment(String accountNumber, double amount) {
        Biller biller = billerRepository.findByAccountNumber(accountNumber);
        biller.setWallet(biller.getWallet() + amount);
        billerRepository.save(biller);
    }

    @Override
    public boolean existsOnAccountNumber(String accountNumber) {
        Biller biller = billerRepository.findByAccountNumber(accountNumber);
        return billerRepository.exists(biller.getId());
    }
}
