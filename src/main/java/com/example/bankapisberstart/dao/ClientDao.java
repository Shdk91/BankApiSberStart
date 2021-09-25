package com.example.bankapisberstart.dao;

import com.example.bankapisberstart.entity.Client;
import com.example.bankapisberstart.utils.NumberGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@Transactional
public class ClientDao {

    @Autowired
    private EntityManager entityManager;
    @Autowired
    private NumberGenerator numberGenerator;

    public void saveClient(Client client) {
        entityManager.persist(client);
    }


    public List<Client> getAllClients() {
        return (List<Client>) entityManager
                .createQuery("SELECT client from Client client").getResultList();
    }

    public Client getClientByName(String name) {
        System.out.println(1);
        List<Client> client = entityManager
                .createQuery("from Client client where client.name = '" + name + "'").getResultList();
        System.out.println(client);
        return client.get(0);
    }

//    @PostConstruct
//    private void init(){
//        Client client = new Client();
//        client.setName("dima");
//        client.setId(numberGenerator.clientIdGenerator());
//        client.setSurname("kozlov");
//        client.setPatronymic("andreevich");
//        client.setPhoneNumber("+79214023012");
//        client.setEmail("some@mail.ru");
//        client.setPassportId(1111689689L);
//        client.setBirthdate(LocalDate.of(1991, 02, 25));
//        saveClient(client);
//    }
}
