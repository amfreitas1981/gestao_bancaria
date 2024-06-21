package com.banking.account.transact.domain.accounts;

public record DataDetailingAccount(
        Long id,
        String numero_conta,
        Double saldo) {

    public DataDetailingAccount(Account account){
        this(
                account.getId(),
                account.getNumero_conta(),
                account.getSaldo()
        );
    }
}
