package com.example.bankapisberstart.dto.output_dto;

import com.example.bankapisberstart.entity.BankAccount;
import com.example.bankapisberstart.entity.Currency;
import com.example.bankapisberstart.utils.BalanceConverter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BankAccountOutDTO {

    private Long id;

    private String number;

    private Currency currency;

    private String balance;

    private BankAccountOutDTO() {
    }

    public static BankAccountOutDTO generateBankAccountOutDTO(BankAccount bankAccount) {
        BankAccountOutDTO bankAccountOutDTO = new BankAccountOutDTO();
        bankAccountOutDTO.setId(bankAccount.getId());
        bankAccountOutDTO.setNumber(bankAccount.getNumber());
        bankAccountOutDTO.setCurrency(bankAccount.getCurrency());
        bankAccountOutDTO.setBalance(BalanceConverter.convertBalanceFromOutDto(bankAccount.getBalance()));
        return bankAccountOutDTO;
    }


}
