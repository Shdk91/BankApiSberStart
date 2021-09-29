package com.example.bankapisberstart.service;

import com.example.bankapisberstart.dto.inputdto.AddCashDto;
import com.example.bankapisberstart.dto.inputdto.CreateCardDto;
import com.example.bankapisberstart.dto.inputdto.GetBalanceDto;
import com.example.bankapisberstart.dto.inputdto.DefaultGetDto;
import com.example.bankapisberstart.dto.outputdto.BankAccountOutDTO;
import com.example.bankapisberstart.dto.outputdto.CardOutDto;
import com.example.bankapisberstart.entity.Card;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClientService {

    List<BankAccountOutDTO> getAccountList(DefaultGetDto param);

    List<CardOutDto> getCardList(DefaultGetDto param);

    String getBalance(GetBalanceDto param);

    Card createCard(CreateCardDto requestBody);

    void addCash(AddCashDto requestBody);
}

