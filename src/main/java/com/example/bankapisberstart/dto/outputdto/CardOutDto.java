package com.example.bankapisberstart.dto.outputdto;

import com.example.bankapisberstart.entity.Card;
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

    public static CardOutDto generateCardOutDto(Card card) {
        CardOutDto cardOutDto = new CardOutDto();
        cardOutDto.setNumber(card.getNumber());
        cardOutDto.setId(card.getId());
        cardOutDto.setAccountNumber(card.getBankAccount().getNumber());
        cardOutDto.setBalance(card.getBankAccount().getBalance().toString());
        return cardOutDto;
    }
}
