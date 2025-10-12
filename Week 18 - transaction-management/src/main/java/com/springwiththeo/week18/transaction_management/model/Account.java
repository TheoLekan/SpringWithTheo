package com.springwiththeo.week18.transaction_management.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity@Data
@NoArgsConstructor
public class Account {
    @Id@GeneratedValue
    private Long id;
    private String owner;
    private double balance;

    @Version
    private Integer version;

    public Account(String owner, double balance) {
        this.owner = owner;
        this.balance = balance;
    }

    public void withdraw(double amount) {
        this.balance -= amount;
    }

    public void deposit(double amount) {
        this.balance += amount;
    }
}
