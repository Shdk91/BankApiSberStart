package com.example.bankapisberstart.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
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
    private Client client;

    @OneToMany(mappedBy = "bankAccount")
    private List<Card> cards;

    @OneToMany(mappedBy = "bankAccount")
    private List<Transaction> transactions;

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
