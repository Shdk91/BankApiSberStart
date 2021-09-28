package com.example.bankapisberstart.dto.inputdto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Getter
@Setter
public class GetCardsOrAccountsDto {

    @NotBlank
    private String login;

}
