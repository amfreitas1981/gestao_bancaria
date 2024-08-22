package com.banking.account.transact.domain.transaction;

import java.math.BigDecimal;

public record DataDetailingTransaction(
        Long id,
        String numeroConta,
        BigDecimal saldo) {

    public DataDetailingTransaction(Transaction transaction) {
        this(
                transaction.getId(),
                transaction.getAccount().getNumeroConta(),
                transaction.getAccount().getSaldo()
        );
    }
}
