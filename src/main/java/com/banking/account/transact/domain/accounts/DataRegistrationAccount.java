package com.banking.account.transact.domain.accounts;

import com.banking.account.transact.domain.transaction.DataRegistrationTransaction;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record DataRegistrationAccount(
        @NotBlank
        int numero_conta,
        @NotBlank
        Double saldo,
        @NotNull
        @Valid
        List<DataRegistrationTransaction> transactions) {
}
