package com.banking.account.transact.domain.accounts;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DataRegistrationAccount(
        @NotBlank
        @Pattern(regexp = "\\d{3,6}")
        String numero_conta,
        @NotBlank
        Double saldo) {
}
