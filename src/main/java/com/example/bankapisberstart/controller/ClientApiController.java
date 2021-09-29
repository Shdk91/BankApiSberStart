package com.example.bankapisberstart.controller;

import com.example.bankapisberstart.cache.AddCashRequestCacheImpl;
import com.example.bankapisberstart.cache.CreateCardRequestCacheImpl;
import com.example.bankapisberstart.dto.inputdto.*;
import com.example.bankapisberstart.dto.outputdto.BankAccountOutDTO;
import com.example.bankapisberstart.dto.outputdto.CardOutDto;
import com.example.bankapisberstart.dto.outputdto.CounterpartiesOutDto;
import com.example.bankapisberstart.entity.Card;
import com.example.bankapisberstart.exceptionhandling.IdempotencyException;
import com.example.bankapisberstart.service.ClientService;
import com.example.bankapisberstart.service.CounterpartyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Клиенты", description = "Позволяет физическим лицам работать со своими счетами")
public class ClientApiController {

    private final ClientService clientService;

    private final CounterpartyService counterpartyService;

    private final AddCashRequestCacheImpl addCashRequestCache;

    private final CreateCardRequestCacheImpl cardRequestCache;

    @Autowired
    public ClientApiController(ClientService clientService,
                               AddCashRequestCacheImpl addCashRequestCache,
                               CreateCardRequestCacheImpl cardRequestCache,
                               CounterpartyService counterpartyService) {
        this.clientService = clientService;
        this.addCashRequestCache = addCashRequestCache;
        this.cardRequestCache = cardRequestCache;
        this.counterpartyService = counterpartyService;
    }

    @GetMapping("/getAccounts")
    @Operation(
            summary = "Получение списка счетов",
            description = "Позволяет клиентам получить список счетов")
    public List<BankAccountOutDTO> getAccountList(@Valid @ModelAttribute DefaultGetDto param) {

        return clientService.getAccountList(param);
    }

    @GetMapping("/getCards")
    @Operation(
            summary = "Получение списка карт",
            description = "Позволяет клиентам получить список карт")
    public List<CardOutDto> getCardList(@Valid @ModelAttribute DefaultGetDto param) {

        return clientService.getCardList(param);
    }

    @GetMapping("/getBalance")
    @Operation(
            summary = "Получение баланса по карте/счету",
            description = "Позволяет клиентам баланс по номеру карты/счета")
    public String getBalance(@Valid @ModelAttribute GetBalanceDto param) {

        return clientService.getBalance(param);
    }

    @GetMapping("/getCounterparties")
    @Operation(
            summary = "Получение списка контрагентов",
            description = "Позволяет вывести список контрагентов клиента")
    public List<CounterpartiesOutDto> getCounterparties(@Valid @ModelAttribute DefaultGetDto param) {
        return counterpartyService.getCounterparties(param);
    }

    @PostMapping("/addCash")
    @Operation(
            summary = "Внесение средств",
            description = "Позволяет клиентам внести средства на счет или карту по номеру")
    public ResponseEntity<String> addCash(@Valid @RequestBody AddCashDto requestBody) {
        if (addCashRequestCache.checkRequest(requestBody)) {
            throw new IdempotencyException("Слишком много однотипных запросов");
        }

        clientService.addCash(requestBody);
        addCashRequestCache.addRequest(requestBody);

        StringBuilder message = new StringBuilder()
                .append(requestBody.getLogin()).append(" ")
                .append(requestBody.getSum().toString())
                .append(" зачислено на ").append(requestBody.getNumber());

        return new ResponseEntity<>(message.toString(), HttpStatus.OK);
    }

    @PostMapping("/createCard")
    @Operation(
            summary = "Создание новой карты",
            description = "Создание новой карты привязанной к определенному счету")
    public ResponseEntity<String> createCard(@Valid @RequestBody CreateCardDto requestBody) {
        if (cardRequestCache.checkRequest(requestBody)) {
            throw new IdempotencyException("Слишком много однотипных запросов");
        }

        Card card = clientService.createCard(requestBody);
        cardRequestCache.addRequest(requestBody);
        String message = "карта №: " + card.getNumber() + " создана";

        return new ResponseEntity<>(message, HttpStatus.OK);
    }

//    @PostMapping("/addCouterparty")
//    @Operation(
//            summary = "Добавление контрагента",
//            description = "Позволяет клиенту добавлять контрагента")
//    public


}
