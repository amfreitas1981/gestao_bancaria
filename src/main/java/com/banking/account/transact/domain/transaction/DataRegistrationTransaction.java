package com.banking.account.transact.domain.transaction;

import jakarta.validation.constraints.NotNull;

public record DataRegistrationTransaction(
        Long id,
        @NotNull
        PaymentForm forma_pagamento,
        String numero_conta,
        @NotNull
        Double valor) {
}
