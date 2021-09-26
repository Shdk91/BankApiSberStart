package com.example.bankapisberstart.service;

import com.example.bankapisberstart.dao.ClientDao;
import com.example.bankapisberstart.dto.input_dto.GetCardsOrAccountsDto;
import com.example.bankapisberstart.entity.BankAccount;
import com.example.bankapisberstart.entity.Client;
import com.example.bankapisberstart.exception_handling.NoSuchClientException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ClientServiceImpl  implements ClientService{

    @Autowired
    private ClientDao clientDao;


    /**
     * Метод получает параметры Get запроса получает из параметров Login клиента, получает счета клиента из БД.
     * ПРоверяет наличие активных счетов клиента и возвращает их. В случае отсутствия кидает иключение.
     * @param param - параметры передающиеся в Get запросе.
     * @return
     */
    @Override
    @Transactional
    public List<BankAccount> getAccountList(GetCardsOrAccountsDto param) {
        String login = param.getLogin();

        log.info(login + " запрашивает список счетов!");

        List<BankAccount> bankAccounts = clientDao.getAccountListFromClient(login);

        if (!(bankAccounts == null)){
            bankAccounts = bankAccounts.stream()
                    .filter(BankAccount::isActive).collect(Collectors.toList());
        }

        if (bankAccounts == null || bankAccounts.isEmpty()) {
            String message = "У клиента нет активных счетов";
            log.info(message);
            throw new NoSuchClientException(message);
        }

        return bankAccounts;
    }
}
