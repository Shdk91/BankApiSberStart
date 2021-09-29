package com.example.bankapisberstart.service;

import com.example.bankapisberstart.dao.ClientDao;
import com.example.bankapisberstart.dao.CounterpartyDao;
import com.example.bankapisberstart.dto.inputdto.AddCounterpartyDto;
import com.example.bankapisberstart.dto.inputdto.DefaultGetDto;
import com.example.bankapisberstart.dto.inputdto.TranslationDto;
import com.example.bankapisberstart.dto.outputdto.CounterpartiesOutDto;
import com.example.bankapisberstart.entity.Client;
import com.example.bankapisberstart.entity.Counterparty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;



@Service
@Slf4j
public class CounterpartyServiceImpl implements CounterpartyService {

    private final ClientDao clientDao;
    private final CounterpartyDao counterpartyDao;

    @Autowired
    public CounterpartyServiceImpl(ClientDao clientDao, CounterpartyDao counterpartyDao) {
        this.clientDao = clientDao;
        this.counterpartyDao = counterpartyDao;
    }

    /**
     * Метод возвращает список контрагентов клиента. В случае отсутствия улиента в бд
     * выбрасывает NoSuchClientException. Если у клиента нет контрагентов возвращает пустое тело.
     * @param requestParam
     * @return CounterpartiesOutDto
     */
    @Override
    @Transactional(readOnly = true)
    public List<CounterpartiesOutDto> getCounterparties(DefaultGetDto requestParam) {
        String login = requestParam.getLogin();

        log.debug(login + " запрашивает список окнтрагентов");

        List<Counterparty> counterparties = clientDao.getCounterpartiesByClientLogin(login);

        List<CounterpartiesOutDto> counterpartiesOutDtos = new ArrayList<>();

        for(Counterparty counterparty : counterparties) {
            counterpartiesOutDtos.add(CounterpartiesOutDto.getInstance(counterparty));
        }
        log.debug(login + " передаемм список контрагентов");
        return counterpartiesOutDtos;
    }

    @Override
    @Transactional
    public void addCounterparty(AddCounterpartyDto requestParam) {
        String login = requestParam.getLogin();

        log.debug(login + "добавляет контрагента " + requestParam);

        Client client = clientDao.findClientByLogin(login);
        Counterparty counterparty = new Counterparty();
        counterparty.setAccountNumber(requestParam.getAccountNumber());
        counterparty.setName(requestParam.getName());
        counterparty.setTaxNumber(requestParam.getTaxNumber());
        counterparty.addClient(client);

        counterpartyDao.saveCounterparty(counterparty);

        log.debug(login + "контрагент добавлен" + requestParam);
    }

}
