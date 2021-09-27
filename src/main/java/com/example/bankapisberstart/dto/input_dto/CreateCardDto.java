package com.example.bankapisberstart.dto.input_dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class CreateCardDto {

    @NotBlank
    private String login;
    @NotBlank
    private String accountNumber;
}
