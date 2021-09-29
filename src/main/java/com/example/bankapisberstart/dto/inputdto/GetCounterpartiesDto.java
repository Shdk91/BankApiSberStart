package com.example.bankapisberstart.dto.inputdto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class GetCounterpartiesDto {

    @NotBlank
    private String login;

    @NotBlank
    private String taxNumber;

}
