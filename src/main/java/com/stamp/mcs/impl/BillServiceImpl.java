package com.stamp.mcs.impl;

import com.stamp.mcs.entities.Bill;
import com.stamp.mcs.repositories.BillRepository;
import com.stamp.mcs.services.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by kenneth on 8/14/17.
 */
@Primary
@Service
public class BillServiceImpl implements BillService {

    @Autowired
    BillRepository billRepository;

    @Override
    public List<Bill> findAll() {
        return billRepository.findAll();
    }

    @Override
    public Bill add(Bill bill) {
        return billRepository.save(bill);
    }

    @Override
    public Bill update(Bill bill) {
        return billRepository.save(bill);
    }

    @Override
    public Bill findOne(String id) {
        return billRepository.findOne(id);
    }

    @Override
    public Bill findBySubscriberMsisdn(String msisdn) {
        return billRepository.findBySubscriberMsisdn(msisdn);
    }

    @Override
    public Bill findBySubscriberAndBiller(String subscriberAccountNumber, String billerAccountNumber) {
        return billRepository.findBySubscriberAndBiller(subscriberAccountNumber, billerAccountNumber);
    }

    @Override
    public void settlePayment(String subscriberAccountNumber, double amount) {
        Bill bill = billRepository.findBySubscriberAccountNumber(subscriberAccountNumber);
        bill.setOutstandingBalance(bill.getOutstandingBalance() - amount);
        if (0 <= bill.getOutstandingBalance()) {
            bill.setPaid(false);
        } else {
            bill.setPaid(true);
        }
        billRepository.save(bill);
    }

    @Override
    public void delete(String id) {
        billRepository.delete(id);
    }

    @Override
    public boolean exists(String id) {
        return billRepository.exists(id);
    }

    @Override
    public Bill addBalance(String subscriberAccountNumber, String billerAccountNumber, double amount) {
        Bill bill = billRepository.findBySubscriberAndBiller(subscriberAccountNumber, billerAccountNumber);
        bill.setOutstandingBalance(bill.getOutstandingBalance() + amount);
        if (0 <= bill.getOutstandingBalance()) {
            bill.setPaid(false);
        } else {
            bill.setPaid(true);
        }
        return billRepository.save(bill);
    }

    @Override
    public Bill findBySubscriberAccountNumber(String subscriberAccountNumber) {
        return billRepository.findBySubscriberAccountNumber(subscriberAccountNumber);
    }
}
