package com.example.bankapisberstart.dto.inputdto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class AddCounterpartyDto {

    @NotBlank
    private String login;

    @NotBlank
    private String name;

    @NotBlank
    @Size(min = 20, max = 20)
    private String accountNumber;

    @NotBlank
    @Size(min = 10, max = 12)
    private String taxNumber;
}
