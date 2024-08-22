package com.banking.account.transact.domain.transaction;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record DataRegistrationTransaction(
        @NotNull
        @JsonProperty("forma_pagamento") // Usar essa anotação quando o campo no json tiver um nome diferente do campo no DTO
        PaymentForm formaPagamento,

        @NotNull
        BigDecimal valor,

        @NotNull
        @JsonProperty("numero_conta")
        String numeroConta) {
        public DataRegistrationTransaction(DataRegistrationTransaction request) {
                this(
                        request.formaPagamento(),
                        request.valor(),
                        request.numeroConta()
                );
        }
}
