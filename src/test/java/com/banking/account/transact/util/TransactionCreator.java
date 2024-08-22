package com.banking.account.transact.util;

import com.banking.account.transact.domain.accounts.Account;
import com.banking.account.transact.domain.transaction.PaymentForm;
import com.banking.account.transact.domain.transaction.Transaction;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

public class TransactionCreator {

    public static List<Transaction> transactions = Collections.singletonList(new Transaction(
            3L,
            PaymentForm.C,
            new BigDecimal(40.04),
            Account.builder().build()
    ));

    public static Account account = new Account(
            1L,
            "147",
            new BigDecimal(185.56),
            true
    );

    public static Account accountNull = new Account(
            null,
            null,
            null,
            true
    );

    public static Transaction createTransactionToBeSaved(){
        return new Transaction(
                null,
                PaymentForm.P,
                new BigDecimal(10.04),
                account
        );
    }

    public static Transaction createTransactionValid(){
        return new Transaction(
                1L,
                PaymentForm.P,
                new BigDecimal(10.04),
                account
        );
    }

    public static Transaction createTransactionValidUpdate(){
        return new Transaction(
                1L,
                PaymentForm.D,
                new BigDecimal(10.04),
                account
        );
    }
}
