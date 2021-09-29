package com.example.bankapisberstart.dao;

import com.example.bankapisberstart.entity.BankAccount;
import com.example.bankapisberstart.entity.Card;
import com.example.bankapisberstart.entity.Client;
import com.example.bankapisberstart.entity.Counterparty;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientDao {
    Client findClientByLogin(String login);

    List<BankAccount> getAccountListFromClient(String login);

    List<Card> getCardsListFromClientLogin(String login);

    List<Counterparty> getCounterpartiesByClientLogin(String login);

}

