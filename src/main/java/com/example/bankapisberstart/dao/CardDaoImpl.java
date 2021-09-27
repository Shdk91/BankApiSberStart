package com.example.bankapisberstart.dao;

import com.example.bankapisberstart.entity.Card;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;

@Repository
@Slf4j
public class CardDaoImpl implements CardDao{

    @Autowired
    private EntityManager entityManager;

    @Override
    public Card findCardByNumber(String cardNumber) {
        try {
            Card card = (Card) entityManager
                    .createQuery("SELECT card from Card card where card.number ='" + cardNumber + "'")
                    .getSingleResult();
            return card;
        } catch (Exception e){
            log.warn(e.getMessage() + " проверка уникальности номера карты");
            return null;
        }
    }

    @Override
    public void createCard(Card card) {
        entityManager.persist(card);
    }
}
