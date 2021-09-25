package com.example.bankapisberstart.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "accounts")
public class BankAccount {

    @Version
    private Long version;

    @Id
    @Column
    private Long Id;

    @Column(name = "account_number")
    private String number;

    @Column
    @Enumerated(EnumType.STRING)
    private Currency currency;

    @Column
    private Long balance;

    @Column
    private boolean isActive;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

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
