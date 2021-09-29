package com.example.bankapisberstart.service;

import com.example.bankapisberstart.dto.inputdto.AddCounterpartyDto;
import com.example.bankapisberstart.dto.inputdto.DefaultGetDto;
import com.example.bankapisberstart.dto.outputdto.CounterpartiesOutDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CounterpartyService {
    List<CounterpartiesOutDto> getCounterparties(DefaultGetDto requestParam);

    void addCounterparty(AddCounterpartyDto requestParam);

}
