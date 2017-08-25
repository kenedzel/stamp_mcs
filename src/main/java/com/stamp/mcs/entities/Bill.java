package com.stamp.mcs.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by kenneth on 8/14/17.
 */
@Document(collection = "bills")
public class Bill {

    @Id
    private String id;
    private String billerAccountNumber;
    private String subscriberAccountNumber;
    private String subscriberMsisdn;
    private double outstandingBalance;
    private boolean paid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBillerAccountNumber() {
        return billerAccountNumber;
    }

    public void setBillerAccountNumber(String billerAccountNumber) {
        this.billerAccountNumber = billerAccountNumber;
    }

    public String getSubscriberAccountNumber() {
        return subscriberAccountNumber;
    }

    public void setSubscriberAccountNumber(String subscriberAccountNumber) {
        this.subscriberAccountNumber = subscriberAccountNumber;
    }

    public String getSubscriberMsisdn() {
        return subscriberMsisdn;
    }

    public void setSubscriberMsisdn(String subscriberMsisdn) {
        this.subscriberMsisdn = subscriberMsisdn;
    }

    public double getOutstandingBalance() {
        return outstandingBalance;
    }

    public void setOutstandingBalance(double outstandingBalance) {
        this.outstandingBalance = outstandingBalance;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id='" + id + '\'' +
                ", billerAccountNumber='" + billerAccountNumber + '\'' +
                ", subscriberAccountNumber='" + subscriberAccountNumber + '\'' +
                ", subscriberMsisdn='" + subscriberMsisdn + '\'' +
                ", outstandingBalance=" + outstandingBalance +
                ", paid=" + paid +
                '}';
    }
}
