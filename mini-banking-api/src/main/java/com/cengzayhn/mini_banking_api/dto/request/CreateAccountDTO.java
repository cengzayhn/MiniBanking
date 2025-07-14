package com.cengzayhn.mini_banking_api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CreateAccountDTO {
    @NotBlank(message = "Account number must not be blank")
    @Size(min = 1, max = 30, message = "Account number must be between 1 and 30 characters")
    private String number;

    @NotBlank(message = "Account name must not be blank")
    @Size(min = 3, max = 30, message = "Account name must be between 3 and 30 characters")
    private String name;

    @NotNull(message = "Transfer amount cannot be null.")
    private BigDecimal balance;
}
