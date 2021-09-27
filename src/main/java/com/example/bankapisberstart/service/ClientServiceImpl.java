package com.example.bankapisberstart.service;

import com.example.bankapisberstart.dao.BankAccountDao;
import com.example.bankapisberstart.dao.CardDao;
import com.example.bankapisberstart.dao.ClientDao;
import com.example.bankapisberstart.dto.input_dto.AddCashDto;
import com.example.bankapisberstart.dto.input_dto.CreateCardDto;
import com.example.bankapisberstart.dto.input_dto.GetBalanceDto;
import com.example.bankapisberstart.dto.input_dto.GetCardsOrAccountsDto;
import com.example.bankapisberstart.dto.output_dto.BankAccountOutDTO;
import com.example.bankapisberstart.dto.output_dto.CardOutDto;
import com.example.bankapisberstart.entity.BankAccount;
import com.example.bankapisberstart.entity.Card;
import com.example.bankapisberstart.entity.Transaction;
import com.example.bankapisberstart.entity.TransactionType;
import com.example.bankapisberstart.exception_handling.NoSuchClientException;
import com.example.bankapisberstart.utils.BalanceConverter;
import com.example.bankapisberstart.utils.NumberGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientDao clientDao;

    @Autowired
    private CardDao cardDao;

    @Autowired
    private BankAccountDao bankAccountDao;


    /**
     * Метод получает параметры Get запроса получает из параметров Login клиента, получает счета клиента из БД.
     * ПРоверяет наличие активных счетов клиента и возвращает их. В случае отсутствия кидает NoSuchClientException.
     *
     * @param requestParam - параметры передающиеся в Get запросе.
     * @return bankAccountOutDTOList
     */
    @Override
    @Transactional
    public List<BankAccountOutDTO> getAccountList(GetCardsOrAccountsDto requestParam) {
        String login = requestParam.getLogin();

        log.info(login + " запрашивает список счетов!");

        List<BankAccount> bankAccounts = clientDao.getAccountListFromClient(login);
        bankAccounts = bankAccounts.stream()
                .filter(BankAccount::isActive).collect(Collectors.toList());

        if (bankAccounts.isEmpty()) {
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
     *
     * @param requestParam - параметры передающиеся в Get запросе.
     * @return CardOutDtoList
     */
    @Override
    @Transactional
    public List<CardOutDto> getCardList(GetCardsOrAccountsDto requestParam) {
        String login = requestParam.getLogin();

        log.info(login + " запрашивает список карт");

        List<Card> cards = clientDao.getCardsListFromClientLogin(login);
        cards = cards.stream()
                .filter(Card::isActive)
                .collect(Collectors.toList());

        if (cards.isEmpty()) {
            String message = "У клиента " + login + " нет активных карт";
            throw new NoSuchClientException(message);
        }

        List<CardOutDto> cardOutDtoList = new ArrayList<>();

        for (Card card : cards) {
            cardOutDtoList.add(CardOutDto.generatorCardOutDto(card));
        }

        return cardOutDtoList;
    }

    /**
     * Метод получает параметры запроса. Проверяет валидность номера карты или счета в случае невалидности
     * кидает NoSuchClientException. В зависимости от длины номера получает список карт/счетов клиента
     * проверяет есть ли среди них активные с подходящим номером и возвращает баланс.
     *
     * @param requestParam - параметры передающиеся в Get запросе
     * @return balance
     */
    @Override
    @Transactional
    public String getBalance(GetBalanceDto requestParam) {
        String login = requestParam.getLogin();
        String number = requestParam.getNumber();

        if (number.length() == 16) {
            log.info(login + " запрашивает баланс по номеру карты " + number);
            Card card = checkCard(login, number);
            return BalanceConverter.convertBalanceFromOutDto(card.getBankAccount().getBalance());

        } else if (number.length() == 20) {
            log.info(login + " запрашивает баланс по номеру счета " + number);
            BankAccount bankAccount = checkBankAccount(login, number);
            return BalanceConverter.convertBalanceFromOutDto(bankAccount.getBalance());

        } else {
            String message = login + " запросил баланс с  не валидным номер карты или счета";
            throw new NoSuchClientException(message);
        }
    }

    /**
     * Получает параметры из тела запроса. Проверяет валидность счета. Генерирует новый номер карты,
     * проверяет его на уникальность. Создает карту.
     *
     * @param requestBody - параметры из тела Post запроса
     * @return created card
     */
    @Override
    @Transactional
    public Card createCard(CreateCardDto requestBody) {
        String login = requestBody.getLogin();
        String number = requestBody.getAccountNumber();

        log.info(login + " запрашивает создание карты к счету " + number);

        BankAccount bankAccount = checkBankAccount(login, number);

        String cardNumber = null;
        boolean isNumberUnique = true;
        while (isNumberUnique) {
            cardNumber = NumberGenerator.generateCardNumber();
            Card card = cardDao.findCardByNumber(cardNumber);
            if (card == null) {
                isNumberUnique = false;
            }
        }

        Card card = new Card();
        card.setBankAccount(bankAccount);
        card.setClient(bankAccount.getClient());
        card.setNumber(cardNumber);
        card.setActive(true);

        cardDao.createCard(card);

        return card;
    }

    /**
     * Метод принимает данные из тела запроса получает клиента. Проверяет наличие активного счета/карты.
     * Создает транзакцию, меняет баланс счета и сохраняет все изменения в бд.
     * В случае ошибки в данных запроса будет выброшен NoSuchClientException
     *  @param requestBody - параметры из тела Post запроса
     */
    @Override
    @Transactional
    public void addCash(AddCashDto requestBody) {
        String login = requestBody.getLogin();
        String number = requestBody.getNumber();
        Long sum = requestBody.getSum();

        log.info(login + number + sum);

        BankAccount bankAccount;

        if (number.length() == 16) {
            log.info(login + "хочет внести деньги на карту" + number);
            bankAccount = checkCard(login, number).getBankAccount();
        } else if (number.length() == 20) {
            log.info(login + "хочет внести деньги на счет" + number);
            bankAccount = checkBankAccount(login, number);
        } else {
            String message = login + " не валидный номер карты или счета";
            throw new NoSuchClientException(message);
        }

        log.info(login + "Создание банковской транзакции");
        Transaction transaction = new Transaction();
        transaction.setBankAccount(bankAccount);
        transaction.setTransactionType(TransactionType.CASH);
        transaction.setPlus(true);
        transaction.setTime(LocalDateTime.now());
        transaction.setSum(sum);

        bankAccount.addTransaction(transaction);

        log.info(login + " изменение баланса счета: " + number);
        Long currentBalance = bankAccount.getBalance();
        Long newBalance = currentBalance + sum;
        bankAccount.setBalance(newBalance);

        bankAccountDao.updateAccount(bankAccount);
    }

    private BankAccount checkBankAccount(String login, String number) {
        String message = login + " не имеет активных счетов с номером " + number;
        if (!(number.length() == 20)) {
            String message2 = login + " не валидный номер счета";
            throw new NoSuchClientException(message);
        }
        BankAccount bankAccount = clientDao.getAccountListFromClient(login).stream()
                .filter(BankAccount::isActive)
                .filter(x -> x.getNumber().equals(number))
                .findFirst().orElseThrow(() -> new NoSuchClientException(message));
        return bankAccount;
    }

    private Card checkCard(String login, String number) {
        String message = login + " не имеет активных карт с номером " + number;
        if (!(number.length() == 16)) {
            String message2 = login + " не валидный номер карты";
            throw new NoSuchClientException(message);
        }
        Card card = clientDao.getCardsListFromClientLogin(login).stream()
                .filter(Card::isActive)
                .filter(x -> x.getNumber().equals(number))
                .findFirst().orElseThrow(() -> new NoSuchClientException(message));
        return card;
    }
}
