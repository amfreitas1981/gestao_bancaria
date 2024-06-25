package com.banking.account.transact.domain.transaction;

public record DataDetailingTransaction(
        Long id,
        String numero_conta,
        Double saldo) {

//    public DataDetailingTransaction(Account account){
//        this(
//                account.getId(),
//                account.getNumero_conta(),
//                account.getSaldo()
//        );
//    }

    public DataDetailingTransaction(Transaction transaction) {
        this(
                transaction.getId(),
                transaction.getNumero_conta(),
                transaction.getSaldo()
        );
    }
}
