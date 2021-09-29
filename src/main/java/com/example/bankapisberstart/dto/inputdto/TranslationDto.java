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
public class TranslationDto {

    @NotBlank
    private String login;

    @NotNull
    private Long counterpartyId;

    @NotNull
    private Long accountId;

    @NotNull
    private BigDecimal sum;
}
