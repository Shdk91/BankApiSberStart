package com.example.bankapisberstart.controller;

import com.example.bankapisberstart.dao.ClientDao;
import com.example.bankapisberstart.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClientController {

    @Autowired
    private ClientDao clientDao;


    @GetMapping("/api")
    public List<Client> getClients() {
        return clientDao.getAllClients();
    }

    @GetMapping("/api/1")
    public Client getClient() {
        return clientDao.getClientByName("dima");
    }


}
