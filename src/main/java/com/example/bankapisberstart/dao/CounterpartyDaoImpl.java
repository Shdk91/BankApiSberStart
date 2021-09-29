package com.example.bankapisberstart.dao;

import com.example.bankapisberstart.entity.Counterparty;
import com.example.bankapisberstart.exceptionhandling.incorrectrequestexception.DuplicateCounterpartyException;
import com.example.bankapisberstart.exceptionhandling.UnknownSQLException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

@Repository
@Slf4j
public class CounterpartyDaoImpl implements CounterpartyDao{

    private final EntityManager entityManager;

    @Autowired
    public CounterpartyDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void saveCounterparty(Counterparty counterparty) {
        boolean isDupl = checkDuplicateInDb(counterparty);
        if (!isDupl) {
            try {
                entityManager.persist(counterparty);
            } catch (Exception e) {
                log.debug(counterparty.getName()
                        + " " + counterparty.getTaxNumber()
                        + " сохранение контрагента "
                        + e.getMessage());
                throw new UnknownSQLException("");
            }
        } else {
            throw new DuplicateCounterpartyException(counterparty.getTaxNumber());
        }
    }

    private boolean checkDuplicateInDb(Counterparty counterparty) {
        try {
            Counterparty duplicate = (Counterparty) entityManager
                    .createQuery("Select counterparty from Counterparty counterparty " +
                            "where counterparty.taxNumber = '" + counterparty.getTaxNumber() + "'")
                    .getSingleResult();
            return true;
        } catch (NoResultException e) {
            return false;
        } catch (Exception e) {
            log.debug(counterparty.getTaxNumber() + e.getMessage());
            throw new UnknownSQLException("");
        }
    }
}
