package com.example.bankapisberstart.dto.input_dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddCashDto {

    private String login;

    private String number;

    private Long sum;
}
