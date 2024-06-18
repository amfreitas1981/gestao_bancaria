package com.banking.account.transact.domain.transaction;

import com.banking.account.transact.domain.accounts.Account;
import com.banking.account.transact.domain.accounts.DataDetailingAccount;

public record DataDetailingTransaction(
        Long id,
        PaymentFormEnum forma_pagamento,
//        String numero_conta,
        Double valor
//        Double saldo
) {

    public DataDetailingTransaction(Transaction transaction){
        this(
                transaction.getId(),
                transaction.getForma_pagamento(),
//                transaction.getNumero_conta(),
                transaction.getValor()
//                transaction.getSaldo()
        );
    }

//    public DataDetailingTransaction(DataDetailingAccount dataDetailingAccount){
//        this(
//                null,
//                dataDetailingAccount.numero_conta(),
//                dataDetailingAccount.saldo()
//        );
//    }

//    public DataDetailingTransaction(DataRegistrationTransaction dataRegistrationTransaction){
//        this(
//                null,
////                dataRegistrationTransaction.forma_pagamento(),
//                dataRegistrationTransaction.numero_conta()
////                ,
////                dataRegistrationTransaction.valor(),
////                dataRegistrationTransaction.saldo()
//        );
//    }
}
