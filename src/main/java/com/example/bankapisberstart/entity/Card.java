package com.example.bankapisberstart.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.GenerationType.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "cards")
public class Card {

    @Version
    private Long version;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @Column(name = "number")
    private String number;

    @Column(name = "isactive")
    private boolean isActive;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private BankAccount bankAccount;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
}
