package com.banking.account.transact.domain.accounts;

import com.banking.account.transact.domain.transaction.DataDetailingTransaction;

import java.util.List;

public record DataDetailingAccount(
        Long id,
        String numero_conta,
        Double saldo
//        List<DataDetailingTransaction> transactions
) {

    public DataDetailingAccount(Account account){
        this(
                account.getId(),
                account.getNumero_conta(),
                account.getSaldo()
//                account.getTransactions().stream().map(DataDetailingTransaction::new).toList()
        );
    }
}
