package com.example.bankapisberstart.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Clients")
public class Client {

    @Version
    private Long version;

    @Id()
    private Long id;

    @Column
    private String login;

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private String patronymic;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column
    private LocalDate birthdate;

    @Column
    private String email;

    @Column(name = "passport_id")
    private Long passportId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "client")
    @JsonIgnore
    private List<BankAccount> accountList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "client")
    @JsonIgnore
    private List<Card> cards;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.DETACH,
            CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(name = "clients_counterparties"
            , joinColumns = @JoinColumn (name ="client_id")
            , inverseJoinColumns = @JoinColumn (name = "counterparties_id")
    )
    @JsonIgnore
    private List<Counterparty> counterparties;

    public void addCounterParty(Counterparty counterparty) {
        if (counterparties == null) {
            counterparties = new ArrayList<>();
        }
        counterparties.add(counterparty);
    }

    public void addAccount(BankAccount account) {
        if (accountList == null) {
            accountList = new ArrayList<>();
        }
        accountList.add(account);
        account.setClient(this);
    }

    public void addCard(Card card) {
        if (cards == null) {
            cards = new ArrayList<>();
        }
        cards.add(card);
        card.setClient(this);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", birthdate=" + birthdate +
                ", email='" + email + '\'' +
                ", passportId=" + passportId +
                '}';
    }
}
