package com.example.bankapisberstart.service;

import com.example.bankapisberstart.dao.ClientDao;
import com.example.bankapisberstart.dto.inputdto.DefaultGetDto;
import com.example.bankapisberstart.dto.outputdto.CounterpartiesOutDto;
import com.example.bankapisberstart.entity.Client;
import com.example.bankapisberstart.entity.Counterparty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;



@Service
public class CounterpartyServiceImpl implements CounterpartyService {

    @Autowired
    private ClientDao clientDao;

    @Override
    public List<CounterpartiesOutDto> getCounterparties(DefaultGetDto requestParam) {
        String login = requestParam.getLogin();

        List<Counterparty> counterparties = clientDao.getCounterpartiesByClientLogin(login);

        List<CounterpartiesOutDto> counterpartiesOutDtos = new ArrayList<>();

        for(Counterparty counterparty : counterparties) {
            counterpartiesOutDtos.add(CounterpartiesOutDto.getInstance(counterparty));
        }

        return counterpartiesOutDtos;
    }
}
