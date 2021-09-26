package com.example.bankapisberstart.dao;

import com.example.bankapisberstart.entity.BankAccount;
import com.example.bankapisberstart.entity.Card;
import com.example.bankapisberstart.entity.Client;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientDao {
    Client findClientFromLogin(String login);

    List<BankAccount> getAccountListFromClient(String login);

    List<Card> getCardsListFromClientLogin(String login);
}

