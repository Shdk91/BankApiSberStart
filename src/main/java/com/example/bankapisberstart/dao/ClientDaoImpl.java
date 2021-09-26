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


    public void saveClient(Client client) {
        entityManager.persist(client);
    }


    public List<Client> getAllClients() {
        return (List<Client>) entityManager
                .createQuery("SELECT client from Client client").getResultList();
    }

    public Client getClientByName(String name) {
        System.out.println(1);
        List<Client> client = entityManager
                .createQuery("from Client client where client.name = '" + name + "'").getResultList();
        System.out.println(client);
        return client.get(0);
    }

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
        } catch (Exception exp){
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
        } catch (Exception exp){
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
            throw new UnknownSQLException("попробуйте позже");
        }
    }

}
