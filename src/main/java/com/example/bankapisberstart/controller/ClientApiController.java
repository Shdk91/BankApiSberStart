package com.example.bankapisberstart.controller;

import com.example.bankapisberstart.dao.ClientDaoImpl;
import com.example.bankapisberstart.dto.input_dto.GetCardsOrAccountsDto;
import com.example.bankapisberstart.dto.output_dto.BankAccountOutDTO;
import com.example.bankapisberstart.dto.output_dto.CardOutDto;
import com.example.bankapisberstart.entity.BankAccount;
import com.example.bankapisberstart.entity.Card;
import com.example.bankapisberstart.entity.Client;
import com.example.bankapisberstart.service.ClientService;
import com.example.bankapisberstart.service.ClientServiceImpl;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/clientApi")
@Slf4j
public class ClientApiController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/getAccounts")
    public List<BankAccountOutDTO> getAccountList(@Valid @ModelAttribute GetCardsOrAccountsDto param){
        return clientService.getAccountList(param);
    }

    @GetMapping("/getCards")
    public List<CardOutDto> getCardList(@Valid @ModelAttribute GetCardsOrAccountsDto param){
        return clientService.getCardList(param);
    }

    @GetMapping("/getBalance")
    public Long getBalance(){
        return null;
    }

    @PostMapping("/addCash")
    public void addCash(){

    }


}
