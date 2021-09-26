package com.example.bankapisberstart.service;

import com.example.bankapisberstart.dto.input_dto.GetCardsOrAccountsDto;
import com.example.bankapisberstart.entity.BankAccount;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClientService {

    List<BankAccount> getAccountList(GetCardsOrAccountsDto param);
}

