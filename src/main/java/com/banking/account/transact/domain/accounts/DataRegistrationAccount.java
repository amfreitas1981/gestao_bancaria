package com.banking.account.transact.domain.accounts;

import com.banking.account.transact.domain.transaction.DataRegistrationTransaction;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.List;

public record DataRegistrationAccount(
//        @NotBlank
//        Long id,
        @NotBlank
        @Pattern(regexp = "\\d{3,6}")
        String numero_conta,
        @NotBlank
        Double saldo
//        Double saldo,
//        @NotNull
//        @Valid
//        List<DataRegistrationTransaction> transactions
) {
}
