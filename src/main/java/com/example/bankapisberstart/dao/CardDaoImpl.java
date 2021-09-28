package com.example.bankapisberstart.dao;

import com.example.bankapisberstart.entity.Card;
import com.example.bankapisberstart.exceptionhandling.UnknownSQLException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@Slf4j
public class CardDaoImpl implements CardDao {

    private final EntityManager entityManager;

    public CardDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Card findCardByNumber(String cardNumber) {
        try {
            Card card = (Card) entityManager
                    .createQuery("SELECT card from Card card where card.number ='" + cardNumber + "'")
                    .getSingleResult();
            return card;
        } catch (Exception e) {
            log.warn(e.getMessage() + " проверка уникальности номера карты");
            return null;
        }
    }

    @Override
    public void createCard(Card card) {
        try {
            entityManager.persist(card);
        } catch (Exception e) {
            log.warn(e.getMessage());
            throw new UnknownSQLException("попробуйте позже");
        }
    }
}
