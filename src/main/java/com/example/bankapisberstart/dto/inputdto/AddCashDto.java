package com.example.bankapisberstart.dto.inputdto;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class AddCashDto {

    @NotBlank
    private String login;
    @NotBlank
    private String number;
    @NotNull
    private BigDecimal sum;
}
