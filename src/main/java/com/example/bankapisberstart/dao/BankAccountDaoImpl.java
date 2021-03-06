package com.example.bankapisberstart.dao;

import com.example.bankapisberstart.entity.BankAccount;
import com.example.bankapisberstart.exceptionhandling.UnknownSQLException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@Slf4j
public class BankAccountDaoImpl implements BankAccountDao {

    private final EntityManager entityManager;

    public BankAccountDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void updateAccount(BankAccount bankAccount) {
        try {
            entityManager.persist(bankAccount);
        } catch (Exception e) {
            log.debug(e.getMessage());
            throw new UnknownSQLException("");
        }
    }
}
