package com.example.bankapisberstart.dto.outputdto;

import com.example.bankapisberstart.entity.Counterparty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CounterpartiesOutDto {

    private Long id;
    private String name;
    private String taxNumber;
    private String accountNumber;

    public static CounterpartiesOutDto getInstance(Counterparty counterparty) {
        CounterpartiesOutDto counterpartiesOutDto = new CounterpartiesOutDto();
        counterpartiesOutDto.id = counterparty.getId();
        counterpartiesOutDto.name = counterparty.getName();
        counterpartiesOutDto.taxNumber = counterparty.getTaxNumber();
        counterpartiesOutDto.accountNumber = counterparty.getAccountNumber();
        return counterpartiesOutDto;
    }
}
