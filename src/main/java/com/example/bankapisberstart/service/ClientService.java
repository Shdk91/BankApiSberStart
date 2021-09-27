package com.example.bankapisberstart.service;

import com.example.bankapisberstart.dto.input_dto.AddCashDto;
import com.example.bankapisberstart.dto.input_dto.CreateCardDto;
import com.example.bankapisberstart.dto.input_dto.GetBalanceDto;
import com.example.bankapisberstart.dto.input_dto.GetCardsOrAccountsDto;
import com.example.bankapisberstart.dto.output_dto.BankAccountOutDTO;
import com.example.bankapisberstart.dto.output_dto.CardOutDto;
import com.example.bankapisberstart.entity.Card;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClientService {

    List<BankAccountOutDTO> getAccountList(GetCardsOrAccountsDto param);

    List<CardOutDto> getCardList(GetCardsOrAccountsDto param);

    String getBalance(GetBalanceDto param);

    Card createCard(CreateCardDto requestBody);

    void addCash(AddCashDto requestBody);
}

