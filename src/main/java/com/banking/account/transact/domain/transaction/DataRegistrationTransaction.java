package com.banking.account.transact.domain.transaction;

import jakarta.validation.constraints.NotNull;

public record DataRegistrationTransaction(
        @NotNull
        PaymentFormEnum paymentFormEnum,
        @NotNull
        int numero_conta,
        @NotNull
        int valor) {
}
