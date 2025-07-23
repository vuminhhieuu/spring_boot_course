package com.example.springcore.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "transaction_history")
public class TransactionHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "from_account_id")
    private int fromAccountId;

    @Column(name = "to_account_id")
    private int toAccountId;

    private double amount;

    private Timestamp timestamp;

    @PrePersist
    public void prePersist() {
        timestamp = new Timestamp(System.currentTimeMillis());
    }

    public int getId() {
        return id;
    }
    public int getFromAccountId() {
        return fromAccountId;
    }
    public void setFromAccountId(int fromAccountId) {
        this.fromAccountId = fromAccountId;
    }
    public int getToAccountId() {
        return toAccountId;
    }
    public void setToAccountId(int toAccountId) {
        this.toAccountId = toAccountId;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public Timestamp getTimestamp() {
        return timestamp;
    }
}
