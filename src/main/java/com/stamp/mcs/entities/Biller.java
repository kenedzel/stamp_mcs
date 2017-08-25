package com.stamp.mcs.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by kenneth on 8/14/17.
 */
@Document(collection = "biller")
public class Biller {

    @Id
    private String id;
    private String billerName;
    private String accountNumber;
    private double wallet;

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

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getWallet() {
        return wallet;
    }

    public void setWallet(double wallet) {
        this.wallet = wallet;
    }

    @Override
    public String toString() {
        return "Biller{" +
                "id='" + id + '\'' +
                ", billerName='" + billerName + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", wallet=" + wallet +
                '}';
    }
}
