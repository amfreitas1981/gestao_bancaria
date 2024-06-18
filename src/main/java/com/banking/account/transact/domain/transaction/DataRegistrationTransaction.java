package com.banking.account.transact.domain.transaction;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DataRegistrationTransaction(
        @NotNull
        PaymentFormEnum forma_pagamento,
//        @NotNull
//        @Pattern(regexp = "\\d{3,6}")
        String numero_conta,
        @NotNull
        Double valor
//        ,
//        Double saldo
) {
}
