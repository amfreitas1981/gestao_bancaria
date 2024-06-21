package com.banking.account.transact.util;

import com.banking.account.transact.domain.accounts.Account;
import com.banking.account.transact.domain.transaction.PaymentForm;
import com.banking.account.transact.domain.transaction.Transaction;

import java.util.Collections;
import java.util.List;

public class TransactionCreator {

    public static List<Transaction> transactions = Collections.singletonList(new Transaction(
            3L,
            PaymentForm.C,
            "233",
            40.04,
            147.86,
            Account.builder().build()
    ));

    public static Account account = new Account(
            1L,
            "147",
            185.56,
            true
    );

    public static Transaction createTransactionToBeSaved(){
        return new Transaction(
                null,
                PaymentForm.P,
                "254",
                10.04,
                1478.63,
                Account.builder().build()
        );
    }

    public static Transaction createTransactionValid(){
        return new Transaction(
                1L,
                PaymentForm.P,
                "254",
                10.04,
                1478.63,
                Account.builder().build()
        );
    }

    public static Transaction createTransactionValidUpdate(){
        return new Transaction(
                1L,
                PaymentForm.D,
                "254",
                10.04,
                1478.63,
                Account.builder().build()
        );
    }
}
