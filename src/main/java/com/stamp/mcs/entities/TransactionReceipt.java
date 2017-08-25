package com.stamp.mcs.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by kenneth on 8/16/17.
 */
@Document(collection = "transaction_receipt")
public class TransactionReceipt {

    @Id
    private String id;
    private String billerName;
    private String billerAccountNumber;
    private String subscriberName;
    private String subscriberAccountNumber;
    private double amount;
    private boolean isSuccess;
    private long datePaid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBillerName() {
        return billerName;
    }

    public void setBillerName(String billerName) {
        this.billerName = billerName;
    }

    public String getBillerAccountNumber() {
        return billerAccountNumber;
    }

    public void setBillerAccountNumber(String billerAccountNumber) {
        this.billerAccountNumber = billerAccountNumber;
    }

    public String getSubscriberName() {
        return subscriberName;
    }

    public void setSubscriberName(String subscriberName) {
        this.subscriberName = subscriberName;
    }

    public String getSubscriberAccountNumber() {
        return subscriberAccountNumber;
    }

    public void setSubscriberAccountNumber(String subscriberAccountNumber) {
        this.subscriberAccountNumber = subscriberAccountNumber;
    }

    public long getDatePaid() {
        return datePaid;
    }

    public void setDatePaid(long datePaid) {
        this.datePaid = datePaid;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }
}
