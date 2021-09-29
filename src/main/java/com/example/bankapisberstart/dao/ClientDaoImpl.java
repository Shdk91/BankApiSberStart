package com.example.bankapisberstart.dao;

import com.example.bankapisberstart.entity.BankAccount;
import com.example.bankapisberstart.entity.Card;
import com.example.bankapisberstart.entity.Client;
import com.example.bankapisberstart.entity.Counterparty;
import com.example.bankapisberstart.exceptionhandling.NoSuchAccountException;
import com.example.bankapisberstart.exceptionhandling.NoSuchCardException;
import com.example.bankapisberstart.exceptionhandling.NoSuchClientException;
import com.example.bankapisberstart.exceptionhandling.UnknownSQLException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

@Repository
@Slf4j
public class ClientDaoImpl implements ClientDao {

    private final EntityManager entityManager;

    public ClientDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public Client findClientByLogin(String login) {
        try {
            Client client = (Client) entityManager
                    .createQuery("SELECT client FROM " +
                            "Client client WHERE client.login = '" + login + "'")
                    .getSingleResult();
            return client;
        } catch (NoResultException e) {
            throw new NoSuchClientException(login);
        } catch (Exception exp) {
            log.debug(login + " " + exp.getMessage());
            throw new UnknownSQLException("");
        }
    }

    @Override
    public List<BankAccount> getAccountListFromClient(String login) {
        Client client = findClientByLogin(login);
        try {
            return client.getAccountList();
        } catch (NoResultException e) {
            throw new NoSuchAccountException(login);
        } catch (Exception exp) {
            log.debug(login + " " + exp.getMessage());
            throw new UnknownSQLException("");
        }
    }

    @Override
    public List<Card> getCardsListFromClientLogin(String login) {
        Client client = findClientByLogin(login);
        try {
            return client.getCards();
        } catch (NoResultException e) {
            throw new NoSuchCardException(login);
        } catch (Exception exp) {
            log.debug(login + " " + exp.getMessage());
            throw new UnknownSQLException("");
        }
    }

    @Override
    public List<Counterparty> getCounterpartiesByClientLogin(String login) {
        Client client = findClientByLogin(login);
        try {
            List<Counterparty> counterparties = client.getCounterparties();
            return counterparties;
        } catch (Exception exp) {
            log.debug(login + " " + exp.getMessage());
            throw new UnknownSQLException("");
        }
    }


}
