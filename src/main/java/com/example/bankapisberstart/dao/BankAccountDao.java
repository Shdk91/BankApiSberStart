package com.example.bankapisberstart.dao;

import com.example.bankapisberstart.entity.BankAccount;
import org.springframework.stereotype.Repository;

@Repository
public interface BankAccountDao {
    void updateAccount(BankAccount bankAccount);
}
