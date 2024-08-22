package com.banking.account.transact.domain.accounts;

import java.math.BigDecimal;

public record DataDetailingAccount(
        Long id,
        String numeroConta,
        BigDecimal saldo) {

    public DataDetailingAccount(Account account){
        this(
                account.getId(),
                account.getNumeroConta(),
                account.getSaldo()
        );
    }
}
