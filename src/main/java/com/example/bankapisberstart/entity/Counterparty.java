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
@Table(name = "counterparties")
public class Counterparty {

    @Version
    private Long version;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "tax_number")
    private String taxNumber;

    @ManyToMany()
    @JoinTable(name = "clients_counterparties"
            , joinColumns = @JoinColumn(name = "counterparties_id")
            , inverseJoinColumns = @JoinColumn(name = "client_id")
    )
    @JsonIgnore
    private List<Client> clientList;

    public void addClient(Client client) {
        if (clientList == null) {
            clientList = new ArrayList<>();
        }
        clientList.add(client);
    }
}
