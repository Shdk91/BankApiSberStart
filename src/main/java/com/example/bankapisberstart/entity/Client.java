package com.example.bankapisberstart.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

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
    private char[] password;

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

    @OneToMany(mappedBy = "client")
    @JsonIgnore
    private List<BankAccount> accountList;

    @OneToMany(mappedBy = "client")
    @JsonIgnore
    private List<Card> cards;

    public void addAccount(BankAccount account){
        if (accountList == null) {
            accountList = new ArrayList<>();
        }
        accountList.add(account);
        account.setClient(this);
    }

    public void addCard(Card card){
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
