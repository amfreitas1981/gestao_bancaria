package com.banking.account.transact.domain.accounts;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.math.BigDecimal;

public record DataRegistrationAccount(
        @NotBlank
        @Pattern(regexp = "\\d{3,6}")
        @JsonProperty("numero_conta")
        String numeroConta,
        @NotBlank
        @JsonProperty("saldo")
        BigDecimal saldo) {
}
