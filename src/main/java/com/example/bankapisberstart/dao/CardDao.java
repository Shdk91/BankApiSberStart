package com.example.bankapisberstart.dao;

import com.example.bankapisberstart.entity.Card;
import org.springframework.stereotype.Repository;

@Repository
public interface CardDao {

    Card findCardByNumber(String cardNumber);

    void createCard(Card card);
}
