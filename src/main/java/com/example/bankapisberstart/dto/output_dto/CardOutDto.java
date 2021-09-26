package com.example.bankapisberstart.dto.output_dto;

import com.example.bankapisberstart.entity.Card;
import com.example.bankapisberstart.utils.BalanceConverter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CardOutDto {

    private Long id;

    private String number;

    private String balance;

    private String accountNumber;

    private CardOutDto() {
    }

    public static CardOutDto generatorCardOutDto(Card card) {
        CardOutDto cardOutDto = new CardOutDto();
        cardOutDto.setNumber(card.getNumber());
        cardOutDto.setId(card.getId());
        cardOutDto.setAccountNumber(card.getBankAccount().getNumber());
        cardOutDto.setBalance(BalanceConverter.convertBalanceFromOutDto(card.getBankAccount().getBalance()));
        return cardOutDto;
    }
}
