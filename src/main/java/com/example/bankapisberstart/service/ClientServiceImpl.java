package com.example.bankapisberstart.service;

import com.example.bankapisberstart.dao.ClientDao;
import com.example.bankapisberstart.dto.input_dto.GetCardsOrAccountsDto;
import com.example.bankapisberstart.dto.output_dto.BankAccountOutDTO;
import com.example.bankapisberstart.dto.output_dto.CardOutDto;
import com.example.bankapisberstart.entity.BankAccount;
import com.example.bankapisberstart.entity.Card;
import com.example.bankapisberstart.entity.Client;
import com.example.bankapisberstart.exception_handling.NoSuchClientException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ClientServiceImpl  implements ClientService{

    @Autowired
    private ClientDao clientDao;


    /**
     * Метод получает параметры Get запроса получает из параметров Login клиента, получает счета клиента из БД.
     * ПРоверяет наличие активных счетов клиента и возвращает их. В случае отсутствия кидает NoSuchClientException.
     * @param requestParam  - параметры передающиеся в Get запросе.
     * @return bankAccountOutDTOList
     */
    @Override
    @Transactional
    public List<BankAccountOutDTO> getAccountList(GetCardsOrAccountsDto requestParam) {
        String login = requestParam.getLogin();

        log.info(login + " запрашивает список счетов!");

        List<BankAccount> bankAccounts = clientDao.getAccountListFromClient(login);

        if (!(bankAccounts == null)){
            bankAccounts = bankAccounts.stream()
                    .filter(BankAccount::isActive).collect(Collectors.toList());
        }

        if (bankAccounts == null || bankAccounts.isEmpty()) {
            String message = "У клиента " + login + " нет активных счетов";
            throw new NoSuchClientException(message);
        }

        List<BankAccountOutDTO> bankAccountOutDTOList = new ArrayList<>();

        for (BankAccount bankAccount : bankAccounts) {
            bankAccountOutDTOList.add(BankAccountOutDTO.generateBankAccountOutDTO(bankAccount));
        }

        log.info(login + " отправлен список счетов");

        return bankAccountOutDTOList;
    }

    /**
     * Метод получает параметры Get запроса получает из параметров Login клиента, получает карты клиента из БД.
     * ПРоверяет наличие активных карт клиента и возвращает их. В случае отсутствия NoSuchClientException иключение.
     * @param requestParam  - параметры передающиеся в Get запросе.
     * @return CardOutDtoList
     */
    @Override
    @Transactional
    public List<CardOutDto> getCardList(GetCardsOrAccountsDto requestParam) {
        String login = requestParam.getLogin();

        log.info(login + " запрашивает список карт");

        List <Card> cards = clientDao.getCardsListFromClientLogin(login);

        if (!(cards == null)){
            cards = cards.stream()
                    .filter(Card::isActive)
                    .collect(Collectors.toList());
        }

        if (cards == null || cards.isEmpty()) {
            String message = "У клиента " + login + " нет активных карт";
            throw new NoSuchClientException(message);
        }

        List<CardOutDto> cardOutDtoList = new ArrayList<>();

        for (Card card : cards) {
            cardOutDtoList.add(CardOutDto.generatorCardOutDto(card));
        }

        return cardOutDtoList;
    }
}
