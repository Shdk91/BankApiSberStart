package com.example.bankapisberstart.dao;

import com.example.bankapisberstart.entity.BankAccount;
import com.example.bankapisberstart.entity.Card;
import com.example.bankapisberstart.entity.Client;
import com.example.bankapisberstart.exception_handling.NoSuchClientException;
import com.example.bankapisberstart.exception_handling.UnknownSQLException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

@Repository
@Slf4j
public class ClientDaoImpl implements ClientDao {

    @Autowired
    private EntityManager entityManager;


    @Override
    public Client findClientFromLogin(String login) {
        try {
            Client client = (Client) entityManager
                    .createQuery("SELECT client FROM Client client WHERE client.login = '" + login + "'")
                    .getSingleResult();
            return client;
        } catch (NoResultException e) {
            String message = login + " клиент с таким логином не найден в базе данных!";
            throw new NoSuchClientException(message);
        } catch (Exception exp) {
            log.warn(login + " " + exp.getMessage());
            throw new UnknownSQLException("попробуйте позже");
        }
    }

    @Override
    public List<BankAccount> getAccountListFromClient(String login) {
        Client client = findClientFromLogin(login);
        try {
            return client.getAccountList();
        } catch (NoResultException e) {
            String message = login + " у данного клиента нет счетов";
            throw new NoSuchClientException(message);
        } catch (Exception exp) {
            log.warn(login + " " + exp.getMessage());
            throw new UnknownSQLException("попробуйте позже");
        }
    }

    @Override
    public List<Card> getCardsListFromClientLogin(String login) {
        Client client = findClientFromLogin(login);
        try {
            return client.getCards();
        } catch (NoResultException e) {
            String message = login + " у данного клиента нет карт";
            throw new NoSuchClientException(message);
        } catch (Exception exp) {
            log.warn(login + " " + exp.getMessage());
            throw new UnknownSQLException("попробуйте позже");
        }
    }

}
