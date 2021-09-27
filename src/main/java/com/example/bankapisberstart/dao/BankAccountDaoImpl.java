package com.example.bankapisberstart.dao;

import com.example.bankapisberstart.entity.BankAccount;
import com.example.bankapisberstart.exception_handling.UnknownSQLException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@Slf4j
public class BankAccountDaoImpl implements BankAccountDao {

    @Autowired
    private EntityManager entityManager;

    @Override
    public void updateAccount(BankAccount bankAccount) {
        try {
            entityManager.persist(bankAccount);
        } catch (Exception e) {
            log.warn(e.getMessage());
            throw new UnknownSQLException("попробуйте позже");
        }
    }
}
