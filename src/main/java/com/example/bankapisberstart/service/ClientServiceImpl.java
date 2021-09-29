package com.example.bankapisberstart.service;

import com.example.bankapisberstart.dao.BankAccountDao;
import com.example.bankapisberstart.dao.CardDao;
import com.example.bankapisberstart.dao.ClientDao;
import com.example.bankapisberstart.dto.inputdto.*;
import com.example.bankapisberstart.dto.outputdto.BankAccountOutDTO;
import com.example.bankapisberstart.dto.outputdto.CardOutDto;
import com.example.bankapisberstart.entity.*;
import com.example.bankapisberstart.exceptionhandling.incorrectrequestexception.*;
import com.example.bankapisberstart.utils.NumberGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ClientServiceImpl implements ClientService {

    private final ClientDao clientDao;

    private final CardDao cardDao;

    private final BankAccountDao bankAccountDao;

    @Autowired
    public ClientServiceImpl(ClientDao clientDao, CardDao cardDao, BankAccountDao bankAccountDao) {
        this.clientDao = clientDao;
        this.cardDao = cardDao;
        this.bankAccountDao = bankAccountDao;
    }


    /**
     * Метод получает параметры Get запроса получает из параметров Login клиента, получает счета клиента из БД.
     * ПРоверяет наличие активных счетов клиента и возвращает их. В случае отсутствия кидает NoSuchClientException.
     *
     * @param requestParam - параметры передающиеся в Get запросе.
     * @return bankAccountOutDTOList
     */
    @Override
    @Transactional(readOnly = true)
    public List<BankAccountOutDTO> getAccountList(DefaultGetDto requestParam) {
        String login = requestParam.getLogin();

        log.debug(login + " запрашивает список счетов!");

        List<BankAccount> bankAccounts = clientDao.getAccountListFromClient(login);
        bankAccounts = bankAccounts.stream()
                .filter(BankAccount::isActive).collect(Collectors.toList());

        if (bankAccounts.isEmpty()) {
            throw new NoSuchAccountException(login);
        }

        List<BankAccountOutDTO> bankAccountOutDTOList = new ArrayList<>();

        for (BankAccount bankAccount : bankAccounts) {
            bankAccountOutDTOList.add(BankAccountOutDTO.generateBankAccountOutDTO(bankAccount));
        }

        log.debug(login + " отправлен список счетов");

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
    @Transactional(readOnly = true)
    public List<CardOutDto> getCardList(DefaultGetDto requestParam) {
        String login = requestParam.getLogin();

        log.debug(login + " запрашивает список карт");

        List<Card> cards = clientDao.getCardsListFromClientLogin(login);
        cards = cards.stream()
                .filter(Card::isActive)
                .collect(Collectors.toList());

        if (cards.isEmpty()) {
            throw new NoSuchCardException(login);
        }

        List<CardOutDto> cardOutDtoList = new ArrayList<>();

        for (Card card : cards) {
            cardOutDtoList.add(CardOutDto.generateCardOutDto(card));
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
    @Transactional(readOnly = true)
    public String getBalance(GetBalanceDto requestParam) {
        String login = requestParam.getLogin();
        String number = requestParam.getNumber();

        if (number.length() == 16) {
            log.debug(login + " запрашивает баланс по номеру карты " + number);
            Card card = checkCard(login, number);
            return card.getBankAccount().getBalance().toString();

        } else if (number.length() == 20) {
            log.debug(login + " запрашивает баланс по номеру счета " + number);
            BankAccount bankAccount = checkBankAccount(login, number);
            return bankAccount.getBalance().toString();

        } else {
            throw new IncorrectNumberException(login + ":" + number);
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

        log.debug(login + " запрашивает создание карты к счету " + number);

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
     *
     * @param requestBody - параметры из тела Post запроса
     */
    @Override
    @Transactional
    public void addCash(AddCashDto requestBody) {
        String login = requestBody.getLogin();
        String number = requestBody.getNumber();
        BigDecimal sum = requestBody.getSum();

        BankAccount bankAccount;

        if (number.length() == 16) {
            log.debug(login + "хочет внести деньги на карту" + number);
            bankAccount = checkCard(login, number).getBankAccount();
        } else if (number.length() == 20) {
            log.debug(login + "хочет внести деньги на счет" + number);
            bankAccount = checkBankAccount(login, number);
        } else {
            throw new IncorrectNumberException(login + ":" + number);
        }

        log.debug(login + "Создание банковской транзакции");
        Transaction transaction = new Transaction();
        transaction.setTransactionType(TransactionType.CASH);
        transaction.setPlus(true);
        transaction.setTime(LocalDateTime.now());
        transaction.setSum(sum);

        bankAccount.addTransaction(transaction);

        log.debug(login + " изменение баланса счета: " + number);
        BigDecimal currentBalance = bankAccount.getBalance();
        BigDecimal newBalance = currentBalance.add(sum);
        bankAccount.setBalance(newBalance);

        bankAccountDao.updateAccount(bankAccount);
    }

    @Override
    @Transactional
    public void translationMoney(TranslationDto requestParam) {
        String login = requestParam.getLogin();
        Long accountId = requestParam.getAccountId();
        Long counterpartyId = requestParam.getCounterpartyId();
        BigDecimal sum = requestParam.getSum();

        log.debug("Клиент" + login + "хочет перевести деньги контрагенту: "
                + counterpartyId + "  со счета с Id: " + accountId );


        BankAccount bankAccount = clientDao.getAccountListFromClient(login)
                .stream().filter(BankAccount::isActive)
                .filter(x -> x.getId().equals(accountId))
                .findFirst().orElseThrow(() -> new NoSuchAccountException(login + ":" + accountId));

        Counterparty counterparty = clientDao.getCounterpartiesByClientLogin(login)
                .stream().filter(x -> x.getId().equals(counterpartyId))
                .findFirst().orElseThrow(() -> new NoSuchCounterpartyException(counterpartyId.toString()));

        BigDecimal balance = bankAccount.getBalance();
        if (!(balance.compareTo(sum) >= 0)){
            throw new SmallBalanceException(bankAccount.getNumber());
        }

        log.debug(login + "создание банковской транзации");
        Transaction transaction = new Transaction();
        transaction.setTransactionType(TransactionType.TRANSLATION);
        transaction.setPlus(false);
        transaction.setTime(LocalDateTime.now());
        transaction.setSum(sum);
        transaction.setCounterpartyAccount(counterparty.getAccountNumber());

        bankAccount.addTransaction(transaction);

        log.debug(login + " изменение баланса счета: " + bankAccount.getNumber());
        BigDecimal newBalance = balance.subtract(sum);
        bankAccount.setBalance(newBalance);

        bankAccountDao.updateAccount(bankAccount);
    }

    private BankAccount checkBankAccount(String login, String number) {

        if (!(number.length() == 20)) {
            throw new IncorrectNumberException(login + ":" + number);
        }
        BankAccount bankAccount = clientDao.getAccountListFromClient(login).stream()
                .filter(BankAccount::isActive)
                .filter(x -> x.getNumber().equals(number))
                .findFirst().orElseThrow(() -> new NoSuchAccountException(login + ":" + number));
        return bankAccount;
    }

    private Card checkCard(String login, String number) {

        if (!(number.length() == 16)) {
            throw new IncorrectNumberException(login + ":" + number);
        }
        Card card = clientDao.getCardsListFromClientLogin(login).stream()
                .filter(Card::isActive)
                .filter(x -> x.getNumber().equals(number))
                .findFirst().orElseThrow(() -> new NoSuchCardException(login + ":" + number));
        return card;
    }
}
