package com.example.bankapisberstart.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "accounts")
public class BankAccount {

    @Version
    private Long version;

    @Id
    @Column(name = "id")
    private Long Id;

    @Column(name = "account_number")
    private String number;

    @Column(name = "currency")
    @Enumerated(EnumType.STRING)
    private Currency currency;

    @Column(name = "balance")
    private Long balance;

    @Column(name = "isactive")
    private boolean isActive;

    @ManyToOne
    @JoinColumn(name = "client_id")
    @JsonIgnore
    private Client client;

    @OneToMany(mappedBy = "bankAccount")
    @JsonIgnore
    private List<Card> cards;

    @OneToMany(mappedBy = "bankAccount")
    @JsonIgnore
    private List<Transaction> transactions;

    public void addCard(Card card){
        if (cards == null){
            cards = new ArrayList<>();
        }
        cards.add(card);
        card.setBankAccount(this);
    }

    public void addTransaction(Transaction transaction) {
        if (transactions == null) {
            transactions = new ArrayList<>();
        }
        transactions.add(transaction);
        transaction.setBankAccount(this);
    }

    @Override
    public String toString() {
        return "BankAccount{" +
                "version=" + version +
                ", Id=" + Id +
                ", number='" + number + '\'' +
                ", currency=" + currency +
                ", balance=" + balance +
                ", isActive=" + isActive +
                '}';
    }
}
