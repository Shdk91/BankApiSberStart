package com.example.bankapisberstart.controller;

import com.example.bankapisberstart.dto.input_dto.AddCashDto;
import com.example.bankapisberstart.dto.input_dto.CreateCardDto;
import com.example.bankapisberstart.dto.input_dto.GetBalanceDto;
import com.example.bankapisberstart.dto.input_dto.GetCardsOrAccountsDto;
import com.example.bankapisberstart.dto.output_dto.BankAccountOutDTO;
import com.example.bankapisberstart.dto.output_dto.CardOutDto;
import com.example.bankapisberstart.entity.Card;
import com.example.bankapisberstart.service.ClientService;
import com.example.bankapisberstart.utils.BalanceConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public List<BankAccountOutDTO> getAccountList(@Valid @ModelAttribute GetCardsOrAccountsDto param) {
        return clientService.getAccountList(param);
    }

    @GetMapping("/getCards")
    public List<CardOutDto> getCardList(@Valid @ModelAttribute GetCardsOrAccountsDto param) {
        return clientService.getCardList(param);
    }

    @GetMapping("/getBalance")
    public String getBalance(@Valid @ModelAttribute GetBalanceDto param) {
        return clientService.getBalance(param);
    }

    @PostMapping("/addCash")
    public ResponseEntity<String> addCash(@Valid @RequestBody AddCashDto requestBody) {
        clientService.addCash(requestBody);
        String message = BalanceConverter.convertBalanceFromOutDto(requestBody.getSum()) + " зачисленно";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @PostMapping("/createCard")
    public ResponseEntity<String> createCard(@Valid @RequestBody CreateCardDto requestBody) {
        Card card = clientService.createCard(requestBody);
        String message = "карта №: " + card.getNumber() + " создана";
        return new ResponseEntity<String>(message, HttpStatus.OK);
    }


}
